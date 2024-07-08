package com.hngy.attendance.entity.dto;

import com.hngy.attendance.entity.Registration;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegistrationDTO extends Registration {
    private String userName;
}
