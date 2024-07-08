package com.hngy.attendance.service.impl;

import com.hngy.attendance.entity.Check;
import com.hngy.attendance.mapper.CheckMapper;
import com.hngy.attendance.service.ICheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements ICheckService {
    @Resource
    CheckMapper mapper;

    @Override
    public Integer getLeave() {
        return mapper.leave();
    }
}
