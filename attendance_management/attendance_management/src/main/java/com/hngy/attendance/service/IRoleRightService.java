package com.hngy.attendance.service;

import com.hngy.attendance.entity.RoleRight;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hngy.attendance.entity.dto.RoleRightDTO;

import java.util.List;

public interface IRoleRightService extends IService<RoleRight> {
    void syncRight(RoleRightDTO dto);

    List<Integer> getRightList(Integer id);
}
