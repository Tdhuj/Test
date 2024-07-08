package com.hngy.attendance.service.impl;

import com.hngy.attendance.entity.Group;
import com.hngy.attendance.mapper.GroupMapper;
import com.hngy.attendance.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

}
