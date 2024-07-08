package com.hngy.attendance.entity.dto;

import com.hngy.attendance.entity.Check;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CheckDTO extends Check {
    private String userName;
}
