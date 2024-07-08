package com.hngy.attendance.service.impl;

import com.hngy.attendance.entity.UserRole;
import com.hngy.attendance.mapper.UserRoleMapper;
import com.hngy.attendance.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
