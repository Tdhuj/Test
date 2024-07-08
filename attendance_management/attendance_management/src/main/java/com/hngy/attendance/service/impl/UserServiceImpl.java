package com.hngy.attendance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hngy.attendance.entity.Account;
import com.hngy.attendance.entity.ClassInfo;
import com.hngy.attendance.entity.Right;
import com.hngy.attendance.entity.User;
import com.hngy.attendance.entity.dto.LoginDTO;
import com.hngy.attendance.entity.dto.UserDTO;
import com.hngy.attendance.mapper.UserMapper;
import com.hngy.attendance.service.IUserService;
import com.hngy.attendance.shiro.JwtToken;
import com.hngy.attendance.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper mapper;
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public UserDTO getUserInfo(Integer id) {
        UserDTO userDTO = new UserDTO();

//        把查到的结果包装成UserDTO
        List<Account> temp = mapper.getUserDTO(id);
//        temp.get(0)
        ArrayList<Right> list = new ArrayList<>();
        BeanUtil.copyProperties(temp.get(0), userDTO, "userUrl", "userStatus", "userLastTime", "rolId", "rightId", "rightPid", "rightIcon", "rightPagePath");
        for (Account account : temp) {
            Right right = new Right();
            right.setUrl(account.getRightUrl());
            right.setName(account.getRightName());
            right.setId(account.getRightId());
            right.setPid(account.getRightPid());
            right.setIcon(account.getRightIcon());
            right.setPagePath(account.getRightPagePath());
            list.add(right);
        }
        userDTO.setRights(list);

        return userDTO;
    }

    @Override
    public UserDTO userLogin(LoginDTO dto, HttpServletResponse response) {
        User user = getOne(new QueryWrapper<User>().eq("name", dto.getName()));
//        如果查不出来用户,或密码不正确返回null
        if (user == null || !user.getPassword().equals(dto.getPassword())) {
            return null;
        }
        //用户名密码正确 token不存在或者失效 生成Token返回给前端
        String jwt = jwtUtil.generateToken(user);
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        //执行登录
        SecurityUtils.getSubject().login(new JwtToken(jwt));
        return getUserInfo(user.getId());
    }

    @Override
    public List<ClassInfo> getClassInfo(Integer groupId){
        return mapper.getClassInfo(groupId);
    }

}
