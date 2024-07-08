package com.hngy.attendance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hngy.attendance.entity.RoleRight;
import com.hngy.attendance.entity.dto.RoleRightDTO;
import com.hngy.attendance.mapper.RoleRightMapper;
import com.hngy.attendance.service.IRoleRightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleRightServiceImpl extends ServiceImpl<RoleRightMapper, RoleRight> implements IRoleRightService {

    @Resource
    RoleRightMapper mapper;

    //开启事务，失败时回滚
    @Transactional
    public void syncRight(RoleRightDTO dto) {
        QueryWrapper<RoleRight> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", dto.getRoleId());
        mapper.delete(wrapper);        //先删除当前用户的所有权限
        List<Integer> rightIds = dto.getRightIds();
        rightIds.forEach(i -> {
            RoleRight roleRight = new RoleRight();
            roleRight.setRoleId(dto.getRoleId());
            roleRight.setRightId(i);
            mapper.insert(roleRight);
        });
    }

    @Override
    public List<Integer> getRightList(Integer id) {
        List<Integer> list = new ArrayList<>();
        QueryWrapper<RoleRight> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", id);
        List<RoleRight> roleRights = mapper.selectList(wrapper);
        roleRights.forEach(i -> list.add(i.getRightId()));
        return list;
    }
}
