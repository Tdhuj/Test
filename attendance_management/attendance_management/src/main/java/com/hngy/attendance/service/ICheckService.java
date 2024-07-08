package com.hngy.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hngy.attendance.entity.Check;

public interface ICheckService extends IService<Check> {
    Integer getLeave();
}
