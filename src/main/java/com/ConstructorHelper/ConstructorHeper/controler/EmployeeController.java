package com.ConstructorHelper.ConstructorHeper.controler;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCustomerDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllEmployeeDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.EmployeeLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.EmployeeResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CustomerUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.EmployeeUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.service.EmployeeSrevice;
import com.ConstructorHelper.ConstructorHeper.util.LoginResponse;
import com.ConstructorHelper.ConstructorHeper.util.StandedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin
public class EmployeeController {


    @Autowired
    private EmployeeSrevice employeeSrevice;


    //-------employee Register --------------------
    @PostMapping("/register")
    public ResponseEntity<StandedResponse> RegisterEmployee(@RequestBody EmployeeRegisterDTO employeeRegisterDTO)
    {
        String message = employeeSrevice.registerEmployee(employeeRegisterDTO);
        return new ResponseEntity<StandedResponse>(
                new StandedResponse(201,"Sucess",message), HttpStatus.CREATED
        );
    }


    //----------------Employee  Login-----------------------------------------

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginEmployee(@RequestBody EmployeeLoginDTO employeeLoginDTO)
    {

        long message =  employeeSrevice.loginEmployee(employeeLoginDTO);

        if(message == 7)
        {
            ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
                    new LoginResponse(404,false),HttpStatus.ACCEPTED);
            return response;
        }
        else
        {
            ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
                    new LoginResponse(message,true),HttpStatus.ACCEPTED);
            return response;
        }


    }


    //----------Employee get all by id -------------------//

    @GetMapping(
            path = "/get-employee-by-id",params = "id"
    )
    public ResponseEntity<StandedResponse> getEmployeeById(@RequestParam(value = "id") long employeeId){
//      System.out.println("print "+ customerId);
        EmployeeResponseAllDTO employeeResponseAllDTO= employeeSrevice.getEmployeeById(employeeId);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",employeeResponseAllDTO),HttpStatus.OK);

        return response;
    }

    // ------------------ Employee Update ----------------------//

    @PutMapping("/update-employee")
    public ResponseEntity<StandedResponse> updateEmployee(@RequestBody EmployeeUpdateDTO employeeUpdateDTO)
    {
        String message = employeeSrevice.updateEmployee(employeeUpdateDTO);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }




    //-------------Update Employee photo------------//

    @PutMapping("/update_image")
    public ResponseEntity<StandedResponse> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("id") long id) throws IOException {
        String message = employeeSrevice.uploadImage(file,id);

        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-----------------get Employee photo id by id-----------------------
    @GetMapping("get_image_By_id/{id}")
    public  ResponseEntity<?>  getImageById(@PathVariable("id") long id){
        byte[] image = employeeSrevice.getImage(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    //-------------Update Employee document------------//

    @PutMapping("/update_document")
    public ResponseEntity<StandedResponse> uploadDocument(@RequestParam("image") MultipartFile file, @RequestParam("id") long id) throws IOException {
        String message = employeeSrevice.uploadDocument(file,id);

        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-----------------get Employee document id by id-----------------------

    @GetMapping("get_document_By_id/{id}")
    public byte[]  getDocumentById(@PathVariable("id") long id){
        byte[] image = employeeSrevice.getDocument(id);

        return image;
    }

    //--------- get all employee by active state true -------

    @GetMapping(
            path = "/get-all-employee-by-active-state/{status}"
    )
    public ResponseEntity<StandedResponse>  getAllEmployeeByActiveState(@PathVariable(value = "status") boolean activeState){
        List<GetAllEmployeeDTO> allEmployee = employeeSrevice.getAllEmployeeByActiveState(activeState);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(
                        201,"success",allEmployee
                ),HttpStatus.ACCEPTED
        );
        return response;
    }

}
