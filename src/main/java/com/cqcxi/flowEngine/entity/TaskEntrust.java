package com.cqcxi.flowEngine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 任务委托
 * @TableName task_entrust
 */
@TableName(value ="task_entrust")
@Data
public class TaskEntrust implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 原代办人Id
     */
    private String originalAssigneeId;

    /**
     * 代办人Id
     */
    private String assigneeId;

    /**
     * 星期中的第几天
     */
    private String dayOfWeek;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 开始时间
     */
    private Date startHour;

    /**
     * 结束时间
     */
    private Date endHour;

    /**
     * CRON表达式
     */
    private String cron;

    /**
     * CRON表达式说明
     */
    private String cronNote;

    /**
     * 任务模式： 1.每天任务 2.周任务 3.月任务
     */
    private Integer type;

    /**
     * 状态 1:启用  0:废除
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskEntrust other = (TaskEntrust) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOriginalAssigneeId() == null ? other.getOriginalAssigneeId() == null : this.getOriginalAssigneeId().equals(other.getOriginalAssigneeId()))
            && (this.getAssigneeId() == null ? other.getAssigneeId() == null : this.getAssigneeId().equals(other.getAssigneeId()))
            && (this.getDayOfWeek() == null ? other.getDayOfWeek() == null : this.getDayOfWeek().equals(other.getDayOfWeek()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getStartHour() == null ? other.getStartHour() == null : this.getStartHour().equals(other.getStartHour()))
            && (this.getEndHour() == null ? other.getEndHour() == null : this.getEndHour().equals(other.getEndHour()))
            && (this.getCron() == null ? other.getCron() == null : this.getCron().equals(other.getCron()))
            && (this.getCronNote() == null ? other.getCronNote() == null : this.getCronNote().equals(other.getCronNote()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOriginalAssigneeId() == null) ? 0 : getOriginalAssigneeId().hashCode());
        result = prime * result + ((getAssigneeId() == null) ? 0 : getAssigneeId().hashCode());
        result = prime * result + ((getDayOfWeek() == null) ? 0 : getDayOfWeek().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getStartHour() == null) ? 0 : getStartHour().hashCode());
        result = prime * result + ((getEndHour() == null) ? 0 : getEndHour().hashCode());
        result = prime * result + ((getCron() == null) ? 0 : getCron().hashCode());
        result = prime * result + ((getCronNote() == null) ? 0 : getCronNote().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", originalAssigneeId=").append(originalAssigneeId);
        sb.append(", assigneeId=").append(assigneeId);
        sb.append(", dayOfWeek=").append(dayOfWeek);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", startHour=").append(startHour);
        sb.append(", endHour=").append(endHour);
        sb.append(", cron=").append(cron);
        sb.append(", cronNote=").append(cronNote);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}