package com.demo.department.controllers;

import com.demo.department.dtos.DepartmentRequestDto;
import com.demo.department.dtos.DepartmentResponseDto;
import com.demo.department.models.Department;
import com.demo.department.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDto> getDepartment(@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.getDepartment(departmentId));
    }

    @PostMapping()
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto){
        return ResponseEntity.ok(departmentService.createDepartment(departmentRequestDto));
    }
}
