package com.datacenter.datateam.infrastructure.adapters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentTypeResponse {
    private Long id;
    private String name;
}
