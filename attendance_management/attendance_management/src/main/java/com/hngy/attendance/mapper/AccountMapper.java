package com.hngy.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hngy.attendance.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper extends BaseMapper<Account> {
    //    通过用户ID 查询 用户所有信息
    List<Account> selectInfoByUserId(@Param("id") int id);
}
