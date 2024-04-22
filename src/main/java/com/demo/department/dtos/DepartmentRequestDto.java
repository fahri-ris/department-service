package com.demo.department.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDto {
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
