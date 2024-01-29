package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import com.ConstructorHelper.ConstructorHeper.entity.Customer;
import com.ConstructorHelper.ConstructorHeper.entity.Employee;
import com.ConstructorHelper.ConstructorHeper.exception.NotFoundedException;
import com.ConstructorHelper.ConstructorHeper.repo.CompanyRepo;
import com.ConstructorHelper.ConstructorHeper.repo.CustomerRepo;
import com.ConstructorHelper.ConstructorHeper.repo.EmployeeRepo;
import com.ConstructorHelper.ConstructorHeper.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceIMPL implements AdminService {

  @Autowired
  private CompanyRepo companyRepo;

  @Autowired
  private CustomerRepo customerRepo;

  @Autowired
  private EmployeeRepo employeeRepo;


  // ------------------ Update Active State of Company -------------------

    @Override
    public void updateCompanyActiveStateById(long id,boolean state) {
        if(companyRepo.existsById(id))
        {
            Companies companies = companyRepo.getReferenceById(id);
            companies.setActiveState(state);
            companyRepo.save(companies);
        }
    }


    // ------------------ Update Active State of Customer -------------------
    @Override
    public void updateCustomerActiveStateById(int id, boolean state) {
        if(customerRepo.existsById(id))
        {
            Customer customer = customerRepo.getReferenceById(id);
            customer.setCustomerActiveState(state);
            customerRepo.save(customer);
        }
    }

    // ------------------ Update Active State of Employee -------------------

    @Override
    public void updateEmployeeActiveStateById(long id, boolean state) {
        if(employeeRepo.existsById(id))
        {
            Employee employee = employeeRepo.getReferenceById(id);
            employee.setEmployeeActiveState(state);
            employeeRepo.save(employee);
        }
    }




    // ------------------- Delete company --------------
    @Override
    public String deleteCompany(long id) {
        if(companyRepo.existsById(id)){
            companyRepo.deleteById(id);
            return "deleted successfully"+id;
        }else{
            throw new NotFoundedException("No company found in that id");
        }
    }

    // ------------------- Delete customer --------------

    @Override
    public String deleteCustomer(int id) {
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id);
            return "deleted successfully"+id;
        }else{
            throw new NotFoundedException("No company found in that id");
        }
    }

    // ------------------- Delete employee --------------

    @Override
    public String deleteEmployee(long id) {
        if(employeeRepo.existsById(id)){
            employeeRepo.deleteById(id);
            return "deleted successfully"+id;
        }else{
            throw new NotFoundedException("No company found in that id");
        }
    }
}
