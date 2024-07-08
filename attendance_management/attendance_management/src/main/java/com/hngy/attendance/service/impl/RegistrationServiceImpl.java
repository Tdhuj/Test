package com.hngy.attendance.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hngy.attendance.entity.Registration;
import com.hngy.attendance.entity.dto.RegistrationTotalDTO;
import com.hngy.attendance.mapper.RegistrationMapper;
import com.hngy.attendance.service.IRegistrationService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration> implements IRegistrationService {
    @Resource
    RegistrationMapper mapper;

    @Override
    public List<RegistrationTotalDTO> getSemesterTotal(Integer groupId,String startTime, String stopTime){

        return mapper.semesterTotal(groupId,startTime,stopTime);
    }

    @Override
    public List<RegistrationTotalDTO> getCertainTotal(Integer groupId,String name, String startTime, String stopTime){
        return mapper.certainTotal(groupId,name,startTime,stopTime);
    }

    @Override
    public void getAdd(String userName, Integer status){
        mapper.add(userName,status);
    }

    @Override
    public void getEdit(Integer id,String userName,Integer status){
        mapper.edit(id,userName,status);
    }

    @Override
    public List<RegistrationTotalDTO> getStatistics(Integer groupId){
         return mapper.statistics(groupId);
    }

    @Override
    public List<RegistrationTotalDTO> getNameTotal(Integer groupId,String name){
        return mapper.name(groupId,name);
    }

    @Override
    public Integer getLate(){
        return mapper.late();
    }

    @Override
    public Integer getTruancy(){
        return mapper.truancy();
    }
}
