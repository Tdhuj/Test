package com.hngy.attendance.mapper;

import com.hngy.attendance.entity.Registration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hngy.attendance.entity.dto.RegistrationTotalDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistrationMapper extends BaseMapper<Registration> {
    List<RegistrationTotalDTO> semesterTotal(@Param("groupId") Integer groupId,@Param("start_time") String startTime, @Param("stop_time") String stopTime);
    List<RegistrationTotalDTO> certainTotal(@Param("groupId") Integer groupId,@Param("name") String name, @Param("start_time") String startTime, @Param("stop_time") String stopTime);
    void add(@Param("name") String userName, @Param("status") Integer status);
    void edit(@Param("id") Integer id,@Param("name") String userName,@Param("status") Integer status);

    List<RegistrationTotalDTO> statistics(@Param("groupId") Integer groupId);

    List<RegistrationTotalDTO> name(@Param("groupId") Integer groupId, @Param("name") String name);

    Integer late();

    Integer truancy();
}
