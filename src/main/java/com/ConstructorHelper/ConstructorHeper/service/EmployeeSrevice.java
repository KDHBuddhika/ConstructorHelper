package com.ConstructorHelper.ConstructorHeper.service;

import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllEmployeeDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.EmployeeLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.EmployeeResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.EmployeeUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeSrevice {
    String registerEmployee(EmployeeRegisterDTO employeeRegisterDTO);

    long loginEmployee(EmployeeLoginDTO employeeLoginDTO);

    EmployeeResponseAllDTO getEmployeeById(long employeeId);

    String updateEmployee(EmployeeUpdateDTO employeeUpdateDTO);

    String uploadImage(MultipartFile file, long id) throws IOException;

    byte[] getImage(long id);

    String uploadDocument(MultipartFile file, long id) throws IOException;

    byte[] getDocument(long id);

    List<GetAllEmployeeDTO> getAllEmployeeByActiveState(boolean activeState);
}
