package com.hngy.attendance.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleRightDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int roleId;
    private List<Integer> rightIds;
}
