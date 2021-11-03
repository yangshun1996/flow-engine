package com.cqcxi.flowEngine.util;

public class TaskScheduleModel {
	
	
	/**
        * 所选作业类型: 
        * 1  -> 每天 
        * 2  -> 每周
	 	* 3  -> 时间段
	 	* 4  -> 每月
        */
	Integer jobType;
	
	/**一周的哪几天*/
	Integer[] dayOfWeeks;
	
	/**一个月的哪几天*/
	Integer[] dayOfMonths;
	
	/**秒  */
	Integer second = 00;
	
	/**分  */
	Integer minute = 00;
	
	/**时  */
	Integer hour = 00;

	/**月 */
	Integer month = 00;
 
	public Integer getJobType() {
		return jobType;
	}
 
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}
	
	public Integer[] getDayOfWeeks() {
		return dayOfWeeks;
	}
 
	public void setDayOfWeeks(Integer[] dayOfWeeks) {
		this.dayOfWeeks = dayOfWeeks;
	}	
 
	public Integer[] getDayOfMonths() {
		return dayOfMonths;
	}
 
	public void setDayOfMonths(Integer[] dayOfMonths) {
		this.dayOfMonths = dayOfMonths;
	}
 
	public Integer getSecond() {
		return second;
	}
 
	public void setSecond(Integer second) {
		this.second = second;
	}
 
	public Integer getMinute() {
		return minute;
	}
 
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
 
	public Integer getHour() {
		return hour;
	}
 
	public void setHour(Integer hour) {
		this.hour = hour;
	}


	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public enum JobType{
		DAY(1,"每天"),
		WEEK(2,"每周"),
		DATE(3,"日期"),
		MONTH(4,"每月")
		;

		public int type;

		public String message;

		JobType(int type, String message) {
			this.type = type;
			this.message = message;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}