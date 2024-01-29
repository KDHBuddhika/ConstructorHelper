package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.EmployeeLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.EmployeeResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import com.ConstructorHelper.ConstructorHeper.entity.Employee;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.exception.NotFoundedException;
import com.ConstructorHelper.ConstructorHeper.repo.EmployeeRepo;
import com.ConstructorHelper.ConstructorHeper.service.EmployeeSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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



    /* ******************* Employee Login ******************** */

    @Override
    public String loginEmployee(EmployeeLoginDTO employeeLoginDTO)
    {
        Employee employee = employeeRepo.findByEmployeeEmail(employeeLoginDTO.getEmployeeEmail());
        if(employee != null){
            String password = employeeLoginDTO.getEmployeePassword();
            String encodedPassword = employee.getEmployeePassword();
            //           boolean isPwsRight = passwordEncoder.match(password,encodedPassword);
            boolean isPwsRight= password.equals(encodedPassword);
            if(isPwsRight)
            {
                Optional<Employee> employee1 = employeeRepo.findByEmployeeEmailAndEmployeePassword(employeeLoginDTO.getEmployeeEmail(),password);
                if(employee1.isPresent())
                {
                    return"Login Successfully";
                }
                else
                {
                    return"Login Fail";
                }
            }else
            {
                return "Password is Not match";
            }

        }else
        {
            return "Email not Exist";
        }
    }

    @Override
    public EmployeeResponseAllDTO getEmployeeById(long employeeId) {
        if(employeeRepo.existsById(employeeId)){
            Employee employee = employeeRepo.getReferenceById(employeeId);

            EmployeeResponseAllDTO employeeResponseAllDTO = new EmployeeResponseAllDTO(
                    employee.getEmployeeNIC(),
                    employee.getEmployeeFname(),
                    employee.getEmployeeSname(),
                    employee.getEmployeeEmail(),
                    employee.getEmployeePhoneNumber(),
                    employee.getEmployeeAddress(),
                    employee.getEmployeeWorkExperiance(),
                    employee.getEmployeeGender(),
                    employee.getEmployeeAge(),
                    employee.getEmployeeDescription(),
                    employee.getEmployeePic(),
                    employee.getEmployeeCV(),
                    employee.isEmployeeActiveState()
            );

            return employeeResponseAllDTO;
        }
        else {
            throw new NotFoundedException("Not found Details");
        }
    }




}
