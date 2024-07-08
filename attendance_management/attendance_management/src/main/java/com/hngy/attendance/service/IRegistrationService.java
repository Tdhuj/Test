package com.hngy.attendance.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hngy.attendance.entity.Registration;
import com.hngy.attendance.entity.dto.RegistrationTotalDTO;

import java.util.List;

public interface IRegistrationService extends IService<Registration>{
    List<RegistrationTotalDTO> getSemesterTotal(Integer groupId,String startTime, String stopTime);
    List<RegistrationTotalDTO> getCertainTotal(Integer groupId,String name, String startTime, String stoptime);

    void getAdd(String userName, Integer status);
    void getEdit(Integer id,String userName, Integer status);

    List<RegistrationTotalDTO> getStatistics(Integer groupId);

    List<RegistrationTotalDTO> getNameTotal(Integer groupId, String name);

    Integer getLate();

    Integer getTruancy();
}
