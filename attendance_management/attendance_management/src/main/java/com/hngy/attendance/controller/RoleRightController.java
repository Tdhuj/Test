package com.hngy.attendance.controller;

import com.hngy.attendance.common.lang.ResponseFormat;
import com.hngy.attendance.entity.dto.RoleRightDTO;
import com.hngy.attendance.service.IRoleRightService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/roleRight")
public class RoleRightController {
    @Resource
    IRoleRightService service;

    @GetMapping("/checkedRight/{id}")
    public ResponseFormat checkedRight(@PathVariable Integer id) {
        List<Integer> list = service.getRightList(id);
        return ResponseFormat.successful(list);
    }

    @PostMapping("/setRoleRight")
    public ResponseFormat setRoleRight(@RequestBody RoleRightDTO dto) {
        if (dto.getRoleId() == 1) {
            return ResponseFormat.operate(200, "不能编辑管理员权限", null);
        }
        service.syncRight(dto);
        return ResponseFormat.operate(200, "权限设置成功", null);
    }
}
