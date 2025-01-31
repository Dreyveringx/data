package com.datacenter.datateam.infrastructure.adapters.web.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private Long documentTypeId;
    private String documentNumber;
    private String password;
}
