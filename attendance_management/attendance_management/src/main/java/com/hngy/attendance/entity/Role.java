package com.hngy.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
     * 角色名
     */
    private String name;
    /*
     * 角色类型。0管理员，1普通用户，2其他用户
     */
    private Integer type;
    /*
     * 角色状态。1正常，0冻结
     */
    private Integer status;

}
