package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCustomerDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllEmployeeDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.EmployeeLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.EmployeeResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.EmployeeUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import com.ConstructorHelper.ConstructorHeper.entity.Customer;
import com.ConstructorHelper.ConstructorHeper.entity.Employee;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.exception.NotFoundedException;
import com.ConstructorHelper.ConstructorHeper.repo.EmployeeRepo;
import com.ConstructorHelper.ConstructorHeper.service.EmployeeSrevice;
import com.ConstructorHelper.ConstructorHeper.util.ImageUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceIMPL implements EmployeeSrevice {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;



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
    public long loginEmployee(EmployeeLoginDTO employeeLoginDTO)
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
                    Employee employee2 = employeeRepo.findByEmployeeEmailEqualsAndEmployeePasswordEquals(employeeLoginDTO.getEmployeeEmail(),password);
                    return employee2.getEmployeeNIC();
                }
                else
                {
                    return 7;
                }
            }else
            {
                return 7;
            }

        }else
        {
            return 7;
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
    // ----------------------- Update Employee ---------------------------------

    @Override
    public String updateEmployee(EmployeeUpdateDTO employeeUpdateDTO) {
        if(employeeRepo.existsById(employeeUpdateDTO.getEmployeeNIC()))
        {
            Employee employee = employeeRepo.getReferenceById(employeeUpdateDTO.getEmployeeNIC());
            employee.setEmployeeNIC(employeeUpdateDTO.getEmployeeNIC());
            employee.setEmployeeFname(employeeUpdateDTO.getEmployeeFname());
            employee.setEmployeeSname(employeeUpdateDTO.getEmployeeSname());
            employee.setEmployeeEmail(employeeUpdateDTO.getEmployeeEmail());
            employee.setEmployeePhoneNumber(employeeUpdateDTO.getEmployeePhoneNumber());
            employee.setEmployeeAddress(employeeUpdateDTO.getEmployeeAddress());
            employee.setEmployeeWorkExperiance(employeeUpdateDTO.getEmployeeWorkExperiance());
            employee.setEmployeeGender(employeeUpdateDTO.getEmployeeGender());
            employee.setEmployeeAge(employeeUpdateDTO.getEmployeeAge());
            employee.setEmployeeDescription(employeeUpdateDTO.getEmployeeDescription());

            employeeRepo.save(employee);
            return "save";
        }
        else {
            throw new NotFoundedException("Can not found NIC");
        }
    }


//------------------- Employee logo update -------------------------------------

    @Override
    public String uploadImage(MultipartFile file, long id) throws IOException {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            // Update the existing employee with the new image
            employee.setEmployeePic(ImageUtil.compressImage(file.getBytes()));

            // Save the updated employee
            employeeRepo.save(employee);

            return "Image uploaded successfully for company with id: " + id;
        } else {
            throw new NotFoundedException("Company with id " + id + " not found");
//            return "Employee with id " + id + " not found";
        }
    }

    //-----------------------  get employee pic ----------------------------------

    @Override
    public byte[] getImage(long id) {
        Optional<Employee> dbDocument = employeeRepo.findById(id);
        byte[] image = ImageUtil.decompressImage(dbDocument.get().getEmployeePic());
        return image;
    }




    //-------------------- -Employee document update --------------------------------------

    @Override
    public String uploadDocument(MultipartFile file, long id) throws IOException {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            // Update the existing employee with the new image
            employee.setEmployeeCV(ImageUtil.compressImage(file.getBytes()));

            // Save the updated employee
            employeeRepo.save(employee);

            return "Image uploaded successfully for company with id: " + id;
        } else {
            throw new NotFoundedException("Company with id " + id + " not found");
//            return "Employee with id " + id + " not found";
        }
    }

    //---------------------  get employee document ----------------------------------

    @Override
    public byte[] getDocument(long id) {
        Optional<Employee> dbDocument = employeeRepo.findById(id);
        byte[] image = ImageUtil.decompressImage(dbDocument.get().getEmployeeCV());
        return image;
    }


    //  ------------------- get all company by active state true  -------------------------

    @Override
    public List<GetAllEmployeeDTO> getAllEmployeeByActiveState(boolean activeState)
    {
        List<Employee> employees = employeeRepo.findByemployeeActiveStateEquals(activeState);
        if(employees.size()>0)
        {
            List<GetAllEmployeeDTO> getAllEmployeeDTOS =modelMapper.map(employees,new TypeToken<List<GetAllEmployeeDTO>>(){}.getType());
            return getAllEmployeeDTOS;
        }else {
            throw new NotFoundedException("Companies are not found");
        }
    }


}
