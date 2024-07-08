package com.hngy.attendance.service.impl;

import com.hngy.attendance.entity.Role;
import com.hngy.attendance.mapper.RoleMapper;
import com.hngy.attendance.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
