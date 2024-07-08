package com.hngy.attendance.entity;

import lombok.Data;

@Data
public class ClassInfo {
    private Integer id;
    private String name;
    private String telephone;
    private String mail;
    private Integer groupId;
}
