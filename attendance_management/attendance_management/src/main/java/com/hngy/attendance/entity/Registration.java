package com.hngy.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_registration")
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
     * 登记的学生ID
     */
    private Integer userId;
    /*
     *登记的学生的班级
     */
    private Integer groupId;
    /*
     *登记时间
     */
    private ZonedDateTime createTime;
    /*
     *登记的考勤状态：0：请假，1：迟到，2：旷课
     */
    private Integer status;
}
