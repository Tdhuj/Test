package com.hngy.attendance.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hngy.attendance.common.lang.ResponseFormat;
import com.hngy.attendance.entity.Registration;
import com.hngy.attendance.entity.UserStatus;
import com.hngy.attendance.entity.dto.RegistrationDTO;
import com.hngy.attendance.entity.dto.RegistrationTotalDTO;
import com.hngy.attendance.mapper.RegistrationMapper;
import com.hngy.attendance.service.IRegistrationService;
import com.hngy.attendance.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Resource
    private IRegistrationService service;
    @Resource
    IUserService userService;
    @Resource
    RegistrationMapper mapper;

    private RegistrationDTO registrationDTO;
    /*
     *//*
     *分页查询
     *//*
    @GetMapping("/list/{id}")
    public ResponseFormat findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stopTime,
                                  @PathVariable Integer id) {

        IPage<Registration> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Registration> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Registration::getGroupId,id);
        if (startTime!= null&&stopTime != null) {
            lambdaQueryWrapper.between(Registration::getCreateTime, startTime, stopTime);
        }
        IPage<Registration> pageData = service.page(page, lambdaQueryWrapper);

        return ResponseFormat.successful(pageData);
    }*/

    /*
     * 添加要登记的考勤信息
     */
    @PostMapping("/add")
    public ResponseFormat addRegistration(@RequestBody UserStatus userStatus) {
        service.getAdd(userStatus.getUserName(), userStatus.getStatus());
        return ResponseFormat.successful();
    }

    /*
     *   编辑考勤信息
     */
    @PostMapping("/edit")
    public ResponseFormat editRegistration(@RequestBody UserStatus userStatus) {
        service.getEdit(userStatus.getId(), userStatus.getUserName(), userStatus.getStatus());
        return ResponseFormat.successful();
    }

    /*
     * 删除考勤信息
     */
    @DeleteMapping("/delete/{id}")
    public ResponseFormat deleteRegistration(@PathVariable Integer id) {
        return ResponseFormat.successful(mapper.deleteById(id));
    }

    /*
     *  批量删除考勤信息
     */
    @PostMapping("/delete/batch/")
    public ResponseFormat deleteBatchRegistration(@RequestBody List<Integer> ids) {
        if (ids.size() == 0) {
            return ResponseFormat.fail("请选择需要删除的数据");
        }
        return ResponseFormat.successful(mapper.deleteBatchIds(ids));
    }

    /*
     *  获得班级登记历史
     */
    @GetMapping("/RegistrationHistory/{id}")
    public ResponseFormat groupHistory(@PathVariable int id) {
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", id);
        wrapper.orderByDesc("id");
        List<Registration> registrations = mapper.selectList(wrapper);

        List<RegistrationDTO> dos = getRegistrationDTOS(registrations);
        return ResponseFormat.successful(dos);
    }

    private List<RegistrationDTO> getRegistrationDTOS(List<Registration> registrations) {
        List<RegistrationDTO> dos = new ArrayList<>();
        registrations.forEach(registration -> {
            registrationDTO = new RegistrationDTO();
            BeanUtil.copyProperties(registration, registrationDTO);
            registrationDTO.setUserName(userService.getById(registration.getUserId()).getName());
            dos.add(registrationDTO);
        });
        return dos;
    }

    /*
     *   某班学生的相关非正常出勤统计信息
     */
    @GetMapping("/statistics/{groupId}")
    public ResponseFormat statistics(@PathVariable Integer groupId) {
        List<RegistrationTotalDTO> statistics = service.getStatistics(groupId);
        return ResponseFormat.successful(statistics);
    }


    /*
     * 某个时间段每位学生的非正常考勤统计信息
     */
    @GetMapping("/total/{groupId}")
    public ResponseFormat semesterTotal(@PathVariable Integer groupId,
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") String startTime,
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") String stopTime
    ) {

        log.info("startTime stopTime{},{}", startTime, stopTime);
        List<RegistrationTotalDTO> semester = service.getSemesterTotal(groupId,startTime, stopTime);
        return ResponseFormat.successful(semester);
    }

    /*
     * 某位学生某个时间段的非正常考勤统计信息
     */
    @GetMapping("/certain/{groupId}")
    public ResponseFormat certainTotal(@RequestParam String name,
                                       @PathVariable Integer groupId,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd") String startTime,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd") String stopTime) {

        log.info("startTime stopTime{},{}", startTime, stopTime);
        List<RegistrationTotalDTO> certain = service.getCertainTotal(groupId,name, startTime, stopTime);
        return ResponseFormat.successful(certain);
    }

    /*
     * 某位学生的非正常考勤统计信息
     */
    @GetMapping("/name/{groupId}")
    public ResponseFormat nameTotal(@PathVariable Integer groupId,@RequestParam String name) {
        List<RegistrationTotalDTO> nameT = service.getNameTotal(groupId,name);
        return ResponseFormat.successful(nameT);
    }

    /*
     *   统计今日迟到人数
     */
    @GetMapping("/late")
    public ResponseFormat late(){
        Integer num=service.getLate();
        return ResponseFormat.successful(num);
    }

    /*
    *   统计今日旷课人数
    */
    @GetMapping("/truancy")
    public ResponseFormat truancy(){
        Integer num=service.getTruancy();
        return ResponseFormat.successful(num);
    }
}
