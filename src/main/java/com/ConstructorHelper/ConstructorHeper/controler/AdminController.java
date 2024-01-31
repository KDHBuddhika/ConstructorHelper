package com.ConstructorHelper.ConstructorHeper.controler;

import com.ConstructorHelper.ConstructorHeper.dto.constructionType.PostConstructionTypeDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCompanyDTO;
import com.ConstructorHelper.ConstructorHeper.repo.CompanyRepo;
import com.ConstructorHelper.ConstructorHeper.service.AdminService;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
import com.ConstructorHelper.ConstructorHeper.service.CustomerService;
import com.ConstructorHelper.ConstructorHeper.service.EmployeeSrevice;
import com.ConstructorHelper.ConstructorHeper.util.StandedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AdminService adminService;


    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeSrevice employeeSrevice;


    //  ------------ get all cutomer by active state false ------- //

    @GetMapping(
            path = "/get-all-company-by-active-state/{status}"
    )
    public ResponseEntity<StandedResponse> getAllCompanyByActiveState(@PathVariable(value = "status") boolean activeState){
        List<GetAllCompanyDTO> allCustomer = companyService.getAllCompanyByActiveState(activeState);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(
                        201,"sususs",allCustomer
                ), HttpStatus.ACCEPTED
        );
        return response;
    }




    //----------------     update company active state --------------

    @PutMapping(
            path = ("update_company_active_state/{id}/{state}")
    )
    public void updateCompanyActiveStateById(@PathVariable(value = "id") long id,@PathVariable(value = "state") boolean state)
    {
        adminService.updateCompanyActiveStateById(id,state);

    }



    //----------------     update customer active state --------------

    @PutMapping(
            path = ("update_customer_active_state/{id}/{state}")
    )
    public void updateCustomerActiveStateById(@PathVariable(value = "id") int id,@PathVariable(value = "state") boolean state)
    {
        adminService.updateCustomerActiveStateById(id,state);

    }


    //----------------     update employee active state --------------

    @PutMapping(
            path = ("update_employee_active_state/{id}/{state}")
    )
    public void updateEmployeeActiveStateById(@PathVariable(value = "id") long id,@PathVariable(value = "state") boolean state)
    {
        adminService.updateEmployeeActiveStateById(id,state);

    }


    // ------------------ delete company by id -------------------
    @DeleteMapping(
            path = "delete-company/{id}"
    )
    public String deleteCompany(@PathVariable(value = "id") long Id ){
        String deleted = adminService.deleteCompany(Id);
        return  deleted;
    }


    // ------------------ delete customer by id -------------------
    @DeleteMapping(
            path = "delete-customer/{id}"
    )
    public String deleteCustomer(@PathVariable(value = "id") int Id ){
        String deleted = adminService.deleteCustomer(Id);
        return  deleted;
    }

    // ------------------ delete employee by id -------------------
    @DeleteMapping(
            path = "delete-employee/{id}"
    )
    public String deleteEmployee(@PathVariable(value = "id") long Id ){
        String deleted = adminService.deleteEmployee(Id);
        return  deleted;
    }

    //-------------------------------------------------------------------------//
    //----------------------------------------------------------------------------------//

    // --------------------add_construction company type--------------------//


//    @PostMapping
//    public ResponseEntity<StandedResponse> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
//        String response = adminService.uploadImage(file);
//
//        ResponseEntity<StandedResponse> response1 = new ResponseEntity<StandedResponse>(
//                new StandedResponse(200,"sucsess",response),HttpStatus.OK
//        );
//        return response1;
//    }

//    @PostMapping
//    public ResponseEntity<StandedResponse> addConstructionType(PostConstructionTypeDTO postConstructionTypeDTO)  {
//        String response = adminService.addConstructionType(postConstructionTypeDTO);
//
//        ResponseEntity<StandedResponse> response1 = new ResponseEntity<StandedResponse>(
//                new StandedResponse(200,"sucsess",response),HttpStatus.OK
//        );
//        return response1;
//    }


}
