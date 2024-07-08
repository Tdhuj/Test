package com.hngy.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hngy.attendance.entity.Right;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightMapper extends BaseMapper<Right> {
    //    通过角色ID 查询 权限Url
    List<Right> selectRightUrlByRoleID(@Param("id") int id);
}
