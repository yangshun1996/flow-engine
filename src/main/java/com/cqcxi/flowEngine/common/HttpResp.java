package com.cqcxi.flowEngine.common;

import lombok.Data;
import org.springframework.context.support.StaticApplicationContext;

/**
 * @Title HTTP数据响应体
 * @Author Lskj
 * @Date 2018年10月11日 下午2:26:16
 * @Description 
 **/
@Data
public class HttpResp {
	/**
	 * 响应状态
	 * 0.系统错误
	 * 1.成功
	 * 2.参数错误
	 * 3.签名
	 */
	private int status = 0;
	/**
	 * 响应数据
	 */
	private Object data;
	/**
	 * 通知消息
	 */
	private String message;
	/**
	 * 详细信息
	 */
	private String explain;

	private String token;


	HttpResp(){}


	public HttpResp(int status, Object data, String message, String explain) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
		this.explain = explain;
	}
	public HttpResp(int status, Object data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	public HttpResp(int status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	public HttpResp(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public static HttpResp success(){
		return new HttpResp(1, "操作成功");
	}
	public static HttpResp success( String message){
		return new HttpResp(1, message);
	}
	public static HttpResp success( Object object){
		return new HttpResp(1,object, "操作成功");
	}
	public static HttpResp success( Object data, String message){
		return new HttpResp(1, data, message);
	}

	public static HttpResp error(){
		return new HttpResp(0, "操作失败");
	}
	public static HttpResp error( String message){
		return new HttpResp(0, message);
	}

	public static HttpResp param(){
		return new HttpResp(2, "参数错误");
	}
	public static HttpResp param(String message){
		return new HttpResp(2, message);
	}

}
