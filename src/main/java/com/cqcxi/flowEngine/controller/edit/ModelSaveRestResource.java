package com.cqcxi.flowEngine.controller.edit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cqcxi.flowEngine.enety.ModelStatus;
import com.cqcxi.flowEngine.service.ModelStatusService;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * 流程信息入库
 */
@RestController
@RequestMapping("service")
public class ModelSaveRestResource implements ModelDataJsonConstants {
  
  protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ModelStatusService iProcdefStatusService;

  /**
   * 保存流程
   * @param modelId 模型ID
   * @param name 流程模型名称
   * @param description
   * @param json_xml 流程文件
   * @param svg_xml 图片
   */
  @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.OK)
  public void saveModel(@PathVariable String modelId
          , String name, String description
          , String json_xml, String svg_xml ) {
    try {

      Model model = repositoryService.getModel(modelId);
      String metaInfo = model.getMetaInfo();
      if (metaInfo == null){
        metaInfo = "{\"name\":\"" + model.getName() + "\",\"description\":\"\",\"revision\":" + model.getVersion() +"}";
      }
      ObjectNode modelJson = (ObjectNode) objectMapper.readTree(metaInfo);
      JSONObject jsonObject = JSONUtil.parseObj(json_xml);
      JSONObject properties = jsonObject.getJSONObject("properties");
      Object process_version = properties.get("process_version");

      //查询流程主键是否存在， 如果没有就创建新的
      ModelStatus modelStatus = iProcdefStatusService.getById(modelId);
      if (modelStatus == null){
        String uuid = "key_" +  UUID.randomUUID().toString();
        jsonObject.getJSONObject("properties").set("process_id",uuid);
        json_xml = jsonObject.toString();
        modelStatus = new ModelStatus();
        modelStatus.setModelId(modelId);
        modelStatus.setProcessId(uuid);
        iProcdefStatusService.save(modelStatus);
      }
      modelJson.put(MODEL_NAME, name);
      modelJson.put(MODEL_DESCRIPTION, description);
      model.setMetaInfo(modelJson.toString());
      model.setName(name);
      if (!"".equals(process_version)) {
        model.setVersion(Integer.parseInt(process_version.toString()));
      }
      repositoryService.saveModel(model);
      
      repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));
      
      InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
      TranscoderInput input = new TranscoderInput(svgStream);
      
//      PNGTranscoder transcoder = new PNGTranscoder();
      // Setup output
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      TranscoderOutput output = new TranscoderOutput(outStream);
      
      // Do the transformation
//      transcoder.transcode(input, output);
      final byte[] result = outStream.toByteArray();
      repositoryService.addModelEditorSourceExtra(model.getId(), result);
      outStream.close();
    } catch (Exception e) {
      LOGGER.error("Error saving model", e);
      throw new ActivitiException("Error saving model", e);
    }
  }

}
