package com.hngy.attendance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hngy.attendance.common.lang.ResponseFormat;
import com.hngy.attendance.entity.UserRole;
import com.hngy.attendance.mapper.UserRoleMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private UserRoleMapper userRoleMapper;

    @PostMapping("/setRole")
    public ResponseFormat setRole(@RequestBody UserRole userRole) {
        if (userRole.getUserId() == 1) {
            return ResponseFormat.fail("不能修改管理员角色");
        }
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", userRole.getUserId());
        Long existUser = userRoleMapper.selectCount(userRoleQueryWrapper);
        if (existUser == 0) {
//            添加
            userRoleMapper.insert(userRole);
            return ResponseFormat.operate(200, "添加成功", null);
        } else if (existUser == 1) {
//            更新
            userRoleMapper.update(userRole, userRoleQueryWrapper);
            return ResponseFormat.operate(200, "修改成功", null);
        }
        return ResponseFormat.fail("操作失败");
    }
}
