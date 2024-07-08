package com.hngy.attendance.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationTotalDTO {
    private Integer groupId;
    private String name;
    private Integer leaveCount;
    private Integer lateCount;
    private Integer truancyCount;
}