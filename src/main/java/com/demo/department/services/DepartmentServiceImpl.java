package com.demo.department.services;

import com.demo.department.dtos.DepartmentRequestDto;
import com.demo.department.dtos.DepartmentResponseDto;
import com.demo.department.models.Department;
import com.demo.department.repositories.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDto getDepartment(Long departmentId) {
        try{
            Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new EntityNotFoundException("Department not found"));

            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto().builder()
                    .departmentId(department.getDepartmentId())
                    .departmentName(department.getDepartmentName())
                    .departmentAddress(department.getDepartmentAddress())
                    .departmentCode(department.getDepartmentCode())
                    .build();
            return departmentResponseDto;
        } catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto) {
        try{
            if(departmentRepository.existsByDepartmentName(departmentRequestDto.getDepartmentName())){
                throw new BadRequestException("Department name already exists");
            }

            Department department = new Department().builder()
                    .departmentName(departmentRequestDto.getDepartmentName())
                    .departmentAddress(departmentRequestDto.getDepartmentAddress())
                    .departmentCode(departmentRequestDto.getDepartmentCode())
                    .build();
            Department savedDepartment = departmentRepository.save(department);

            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto().builder()
                    .departmentId(savedDepartment.getDepartmentId())
                    .departmentName(savedDepartment.getDepartmentName())
                    .departmentAddress(savedDepartment.getDepartmentAddress())
                    .departmentCode(savedDepartment.getDepartmentCode())
                    .build();

            return departmentResponseDto;
        } catch (BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
