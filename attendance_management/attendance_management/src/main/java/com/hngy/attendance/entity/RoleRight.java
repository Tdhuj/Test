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
@TableName("t_role_right")
public class RoleRight implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 角色权限ID
     */
    private Integer id;
    /*
     * 角色ID
     */
    private Integer roleId;
    /*
     * 权限ID
     */
    private Integer rightId;

}
