package com.hngy.attendance.entity;

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
@TableName("t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * 用户角色ID
     */
    private Integer id;
    /*
     * 用户ID
     */
    private Integer userId;
    /*
     * 角色ID
     */
    private Integer roleId;
}
