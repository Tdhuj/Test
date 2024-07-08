package com.hngy.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hngy.attendance.entity.ClassInfo;
import com.hngy.attendance.entity.User;
import com.hngy.attendance.entity.dto.LoginDTO;
import com.hngy.attendance.entity.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserService extends IService<User> {

    UserDTO getUserInfo(Integer id);

    UserDTO userLogin(LoginDTO dto, HttpServletResponse response);

    List<ClassInfo> getClassInfo(Integer groupId);

}
