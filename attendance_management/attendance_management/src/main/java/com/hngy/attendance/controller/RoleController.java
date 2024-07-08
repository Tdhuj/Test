package com.hngy.attendance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hngy.attendance.common.lang.ResponseFormat;
import com.hngy.attendance.entity.Role;
import com.hngy.attendance.mapper.RoleMapper;
import com.hngy.attendance.service.IRoleService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService service;
    @Resource
    private RoleMapper mapper;

    @GetMapping("/all")
    public ResponseFormat findAll() {
        return ResponseFormat.successful(service.list());
    }

    //    查询用户列表
    @RequiresRoles("admin")
    @GetMapping("/list")
    public ResponseFormat list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String roleName, @RequestParam(defaultValue = "") String roleType, @RequestParam(defaultValue = "") String roleStatus) {

        IPage<Role> page = new Page<>(currentPage, pageSize);   // myBatisPlus自带的分页方法,参数一：起始页，参数二：多少条数据
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.like(Strings.isNotEmpty(roleName), Role::getName, roleName);
        lambdaQueryWrapper.like(Strings.isNotEmpty(roleType), Role::getType, roleType);
        lambdaQueryWrapper.like(Strings.isNotEmpty(roleStatus), Role::getStatus, roleStatus);

        IPage<Role> pageData = service.page(page, lambdaQueryWrapper);

        return ResponseFormat.successful(pageData);
    }


    @RequiresRoles("admin")
    @PostMapping("/edit")
    public ResponseFormat edit(@RequestBody Role role) {
        boolean b;
        QueryWrapper<Role> name = new QueryWrapper<Role>().eq("name", role.getName());
        List<Role> roles = mapper.selectList(name);

//        前端传来的ID为空提示未登录
//        前端传来的ID查询后存在 提示权限名存在的
        if (role.getId() == null) {
            return ResponseFormat.fail("未登录");
        } else if (!roles.isEmpty()) {
            return ResponseFormat.fail("用户已存在");
        } else {
            b = service.updateById(role);
        }
        return b ? ResponseFormat.successful(null) : ResponseFormat.fail("失败");
    }


    @RequiresRoles("admin")
    @PostMapping("/add")
    public ResponseFormat add(@RequestBody Role role) {
//      同一用户
        if (role.getId() != null) {
            Role temp = mapper.selectOne(new QueryWrapper<Role>().eq("name", role.getName()));
            if (temp == null || role.getId().equals(temp.getId())) {
                return ResponseFormat.successful(service.saveOrUpdate(role));
            }
            return ResponseFormat.fail("角色名已存在");
        }
        if (mapper.selectCount(new QueryWrapper<Role>().eq("name", role.getName())) > 0) {
            return ResponseFormat.fail("角色名已存在");
        }
        return ResponseFormat.successful(service.saveOrUpdate(role));
    }


    @DeleteMapping("/delete/{id}")
    @RequiresRoles("admin")
    public ResponseFormat delete(@PathVariable Integer id) {
        return ResponseFormat.successful(mapper.deleteById(id));
    }

    @RequiresRoles("admin")
    @PostMapping("/delete/batch/")
    public ResponseFormat deleteBatch(@RequestBody List<Integer> ids) {
        if (ids.size() == 0) {
            return ResponseFormat.fail("请选择需要删除的数据");
        }
        return ResponseFormat.successful(mapper.deleteBatchIds(ids));
    }


}
