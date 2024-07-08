package com.hngy.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hngy.attendance.entity.Account;
import com.hngy.attendance.entity.ClassInfo;
import com.hngy.attendance.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from view_user_info where user_id = #{id}")
    List<Account> getUserDTO(@Param("id") Integer id);

    List<ClassInfo> getClassInfo( @Param("groupId") Integer groupId);
}
