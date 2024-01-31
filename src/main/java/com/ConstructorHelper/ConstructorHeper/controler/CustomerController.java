package com.ConstructorHelper.ConstructorHeper.controler;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCompanyDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCustomerDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CompanyUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CustomerUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
import com.ConstructorHelper.ConstructorHeper.service.CustomerService;
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
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    //-------customer Register --------------------
    @PostMapping("/register")
    public ResponseEntity<StandedResponse> RegisterCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO)
    {
        String message = customerService.registerCustomer(customerRegisterDTO);
        return new ResponseEntity<StandedResponse>(
                new StandedResponse(201,"Sucess",message), HttpStatus.CREATED
        );
    }

    //-----------------customer login------------------------

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginCustomer(@RequestBody CustomerLoginDTO customerLoginDTO)
    {

        int message =  customerService.loginCompany(customerLoginDTO);

        if(message == 404)
        {

            ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
                    new LoginResponse(message,false),HttpStatus.ACCEPTED);
            return response;
        }
        else
        {
            ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
                    new LoginResponse(message,true),HttpStatus.ACCEPTED);
            return response;
        }


    }

    //----------Customer get all by id -------------------//

    @GetMapping(
            path = "/get-customer-by-id",params = "id"
    )
    public ResponseEntity<StandedResponse> getCustomerById(@RequestParam(value = "id") int customerId){
//      System.out.println("print "+ customerId);
        CustomerResponseAllDTO customerResponseAllDTO= customerService.getCustomerById(customerId);
        byte[] m = customerResponseAllDTO.getCustomerPic();
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",customerResponseAllDTO),HttpStatus.OK);

        return response;
    }

    // ------------------ Customer Update ----------------------//

    @PutMapping("/update-customer")
    public ResponseEntity<StandedResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO)
    {
        String message = customerService.updateCustomer(customerUpdateDTO);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-------------Update Customer photo------------//

    @PutMapping("/update_image")
    public ResponseEntity<StandedResponse> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("id") int id) throws IOException {
        String message = customerService.uploadImage(file,id);

        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-------------Update Customer photo 2------------//
//    public ResponseEntity<StandedResponse> uploadImage(@RequestParam("image") String file, @RequestParam("id") int id) {
//        String message = customerService.uploadImage2(file,id);
//
//        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
//                new StandedResponse(200,"sucess",message),HttpStatus.OK);
//
//        return response;
//    }


    //-----------------get customer photo id by id-----------------------
    @GetMapping("get_image_By_id/{id}")
    public ResponseEntity<?> getImageById(@PathVariable("id") int id){
        byte[] image = customerService.getImage(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    //-------------Update Customer document------------//

    @PutMapping("/update_document")
    public ResponseEntity<StandedResponse> uploadDocument(@RequestParam("image") MultipartFile file, @RequestParam("id") int id) throws IOException {
        String message = customerService.uploadDocument(file,id);

        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-----------------get customer document id by id-----------------------

    @GetMapping("get_document_By_id/{id}")
    public byte[]  getDocumentById(@PathVariable("id") int id){
        byte[] image = customerService.getDocument(id);

        return image;
    }

    //--------- get all customer by active state true -------

    @GetMapping(
            path = "/get-all-customer-by-active-state/{status}"
    )
    public ResponseEntity<StandedResponse>  getAllCustomerByActiveState(@PathVariable(value = "status") boolean activeState){
        List<GetAllCustomerDTO> allCustomer = customerService.getAllCustomerByActiveState(activeState);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(
                        201,"success",allCustomer
                ),HttpStatus.ACCEPTED
        );
        return response;
    }




}
