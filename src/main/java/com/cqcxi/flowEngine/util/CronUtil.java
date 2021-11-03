package com.cqcxi.flowEngine.util;


/**
 * @ClassName: CronUtil 
 * @Description: Cron表达式工具类 
 * 目前支持三种常用的cron表达式
 * 1.每天的某个时间点执行 例:12 12 12 * * ?表示每天12时12分12秒执行
 * 2.每周的哪几天执行         例:12 12 12 ? * 1,2,3表示每周的周1周2周3 ,12时12分12秒执行
 * 3.每月的哪几天执行         例:12 12 12 1,21,13 * ?表示每月的1号21号13号 12时12分12秒执行
 * @author 
 * @date 
 * 
 */  
public class CronUtil {



    /** 
     *  
     *方法摘要：构建Cron表达式 
     *@param  taskScheduleModel 
     *@return String 
     */  
    public static String createCronExpression(TaskScheduleModel taskScheduleModel){
        StringBuffer cronExp = new StringBuffer("");  
        
        if(null == taskScheduleModel.getJobType()) {
        	System.out.println("执行周期未配置" );//执行周期未配置
        }
        
		if (null != taskScheduleModel.getSecond()
				&& null != taskScheduleModel.getMinute()
				&& null != taskScheduleModel.getHour()) {  
            //秒  
            cronExp.append(taskScheduleModel.getSecond()).append(" ");  
            //分  
            cronExp.append(taskScheduleModel.getMinute()).append(" ");  
            //小时  
            cronExp.append(taskScheduleModel.getHour()).append(" ");  
              
            //每天  
            if(taskScheduleModel.getJobType().intValue() == TaskScheduleModel.JobType.DAY.getType()){
            	cronExp.append("* ");//日
            	cronExp.append("* ");//月
            	cronExp.append("?");//周
            }
              
            //按每周  
            else if(taskScheduleModel.getJobType().intValue() == TaskScheduleModel.JobType.WEEK.getType()){
                //一个月中第几天  
                cronExp.append("? ");  
                //月份  
                cronExp.append("* ");  
                //周  
                Integer[] weeks = taskScheduleModel.getDayOfWeeks();  
                for(int i = 0; i < weeks.length; i++){
                	int day = 0 ;
                	if (weeks[i] == 7){
						day = 1;
					}else {
						day = weeks[i] + 1;
					}
                    if(i == 0){  
                        cronExp.append(day);
                    } else{  
                        cronExp.append(",").append(day);
                    }  
                }  
                  
            }  
              
            //按每月  
            else if(taskScheduleModel.getJobType().intValue() == TaskScheduleModel.JobType.MONTH.getType()){
            	//一个月中的哪几天  
            	Integer[] days = taskScheduleModel.getDayOfMonths();  
            	for(int i = 0; i < days.length; i++){  
            		if(i == 0){  
            			cronExp.append(days[i]);  
            		} else{  
            			cronExp.append(",").append(days[i]);  
            		}  
            	}  
                //月份  
                cronExp.append(" * ");  
                //周  
                cronExp.append("?");  
            }
            //时间段
            else if(taskScheduleModel.getJobType().intValue() == TaskScheduleModel.JobType.DATE.getType()){
				//一个月中的哪几天
				Integer[] days = taskScheduleModel.getDayOfMonths();
				for(int i = 0; i < days.length; i++){
					if(i == 0){
						cronExp.append(days[i]);
					} else{
						cronExp.append(",").append(days[i]);
					}
				}
				//月份
				cronExp.append(" " + taskScheduleModel.getMonth().toString() + " ?");
			}
        }
		else {
			System.out.println("时或分或秒参数未配置" );//时或分或秒参数未配置
		}
        return cronExp.toString();  
    }  
      
    /** 
     *  
     *方法摘要：生成计划的详细描述 
     *@param  taskScheduleModel 
     *@return String 
     */  
    public static String createDescription(TaskScheduleModel taskScheduleModel){  
        StringBuffer description = new StringBuffer("");  
        //计划执行开始时间  
//      Date startTime = taskScheduleModel.getScheduleStartTime();  
        if (null != taskScheduleModel.getSecond()
				&& null != taskScheduleModel.getMinute()
				&& null != taskScheduleModel.getHour()) { 
            //按每天  
            if(taskScheduleModel.getJobType().intValue() == TaskScheduleModel.JobType.DAY.getType()){
            	description.append("每天");  
            	description.append(taskScheduleModel.getHour()).append("时");  
            	description.append(taskScheduleModel.getMinute()).append("分");  
            	description.append(taskScheduleModel.getSecond()).append("秒");  
            	description.append("执行");  
            }  
              
            //按每周  
            else if(taskScheduleModel.getJobType().intValue() == TaskScheduleModel.JobType.WEEK.getType()){
                if(taskScheduleModel.getDayOfWeeks() != null && taskScheduleModel.getDayOfWeeks().length > 0) {  
                	String days = "";
                	for(int i : taskScheduleModel.getDayOfWeeks()) {
                		days += "周" + i;
                	}
                    description.append("每周的").append(days).append(" ");  
                }  
                if (null != taskScheduleModel.getSecond()
        				&& null != taskScheduleModel.getMinute()
        				&& null != taskScheduleModel.getHour()) {   
                    description.append(",");   
                    description.append(taskScheduleModel.getHour()).append("时");  
                	description.append(taskScheduleModel.getMinute()).append("分");  
                	description.append(taskScheduleModel.getSecond()).append("秒"); 
                }  
                description.append("执行");  
            }  
              
            //按每月  
            else if(taskScheduleModel.getJobType().intValue() == TaskScheduleModel.JobType.MONTH.getType()){
                //选择月份  
            	if(taskScheduleModel.getDayOfMonths() != null && taskScheduleModel.getDayOfMonths().length > 0) {  
                	String days = "";
                	for(int i : taskScheduleModel.getDayOfMonths()) {
                		days += i + "号";
                	}
                    description.append("每月的").append(days).append(" ");  
                }    
            	description.append(taskScheduleModel.getHour()).append("时");  
            	description.append(taskScheduleModel.getMinute()).append("分");  
            	description.append(taskScheduleModel.getSecond()).append("秒"); 
                description.append("执行");  
            }  
              
        }  
        return description.toString();  
    }

	/**
	 *
	 * @param rate 频率 0秒；1分；2小时；3日；4月
	 * @param  cycle 周期
	 * @return
	 */
	public static String createLoopCronExpression(int rate, int cycle) {
        String cron = "";
         switch (rate) {
        case 0:// 每cycle秒执行一次
             cron = "0/" + cycle + " * * * * ?";
             break;
         case 1:// 每cycle分钟执行一次
             cron = "0 0/" + cycle + " * * * ?";
             break;
         case 2:// 每cycle小时执行一次
            cron = "0 0 0/" + cycle + " * * ?";
             break;
        case 3:// 每cycle天的0点执行一次
            cron = "0 0 0 1/" + cycle + " * ?";
             break;
        case 4:// 每cycle月的1号0点执行一次
             cron = "0 0 0 1 1/" + cycle + " ? ";
            break;
        case 5://  每天cycle点执行一次
             cron = "0 0 " + cycle+ "  * * ?";
            break;
      default:// 默认每cycle秒执行一次
             cron = "0/1 * * * * ?";
            break;
         }
         return cron;
    }

	public static void main(String[] args) {
        TaskScheduleModel taskScheduleModel = new TaskScheduleModel();
        taskScheduleModel.setJobType(4);
    }
}