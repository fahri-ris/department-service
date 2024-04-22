package com.demo.department.services;

import com.demo.department.dtos.DepartmentRequestDto;
import com.demo.department.dtos.DepartmentResponseDto;
import com.demo.department.models.Department;

public interface DepartmentService {
    DepartmentResponseDto getDepartment(Long departmentId);
    DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);
}
