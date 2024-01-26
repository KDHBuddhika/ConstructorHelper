package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Employee;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.repo.EmployeeRepo;
import com.ConstructorHelper.ConstructorHeper.service.EmployeeSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceIMPL implements EmployeeSrevice {

    @Autowired
    private EmployeeRepo employeeRepo;



    /* *******************Employee Register******************** */
    @Override
    public String registerEmployee(EmployeeRegisterDTO employeeRegisterDTO) {
        if(!(employeeRepo.existsById(employeeRegisterDTO.getEmployeeNIC())))
        {
            Employee employee = new Employee();
            employee.setEmployeeNIC(employeeRegisterDTO.getEmployeeNIC());
            employee.setEmployeeFname(employeeRegisterDTO.getEmployeeFName());
            employee.setEmployeeSname(employeeRegisterDTO.getEmployeeSname());
            employee.setEmployeeEmail(employeeRegisterDTO.getEmployeeEmail());
            employee.setEmployeePhoneNumber(employeeRegisterDTO.getEmployeePhoneNumber());
            employee.setEmployeePassword(employeeRegisterDTO.getEmployeePassword());
            employee.setEmployeeActiveState(false);

            employeeRepo.save(employee);
            return "saved";
        }else{
            throw new AlreadyReportedException("Already have account");
        }
    }
}
