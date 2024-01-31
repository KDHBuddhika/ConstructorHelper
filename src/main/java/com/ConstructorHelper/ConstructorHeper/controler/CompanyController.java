package com.ConstructorHelper.ConstructorHeper.controler;


import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCompanyDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CompanyUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
import com.ConstructorHelper.ConstructorHeper.util.LoginResponse;
import com.ConstructorHelper.ConstructorHeper.util.StandedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;




    //-------Company Register --------------------
    @PostMapping("/register")
    public ResponseEntity<StandedResponse> RegisterCompany(@RequestBody CompanyRegisterDTO companyRegisterDTO)
    {
       String message = companyService.registerCompany(companyRegisterDTO);
       return new ResponseEntity<StandedResponse>(
               new StandedResponse(201,"Sucess",message), HttpStatus.CREATED
       );
    }

    //-------Company Login --------------------

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginCompany(@RequestBody CompanyLoginDTO companyLoginDTO)
    {

        long message =  companyService.loginCompany(companyLoginDTO);
        System.out.println(message);
       if(message == 404)
       {
       ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
               new LoginResponse(message,false),HttpStatus.NOT_FOUND);
        return response;
       }
       else
       {
           ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
                   new LoginResponse(message,true),HttpStatus.ACCEPTED);
           return response;
       }


    }


    //----------Company get all by id -------------------//

    @GetMapping(
            path = "/get-company-by-id",params = "id"
    )
    public ResponseEntity<StandedResponse> getCompanyById(@RequestParam(value = "id") long companyId){
//      System.out.println("print "+ customerId);
        CompanyResponseAllDTO companyResponseAllDTO= companyService.getCompanyById(companyId);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",companyResponseAllDTO),HttpStatus.OK);

        return response;
    }


    // ------------------ Company Update ----------------------//

    @PutMapping("/update")
    public ResponseEntity<StandedResponse> updateCompany(@RequestBody CompanyUpdateDTO companyUpdateDTO)
    {
        String message = companyService.updateCompany(companyUpdateDTO);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-------------Update Company photo------------//

    @PutMapping("/update_image")
    public ResponseEntity<StandedResponse> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("id") long id) throws IOException {
        String message = companyService.uploadImage(file,id);

        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-----------------get company photo id by id-----------------------
    @GetMapping("get_image_By_id/{id}")
    public byte[]  getImageById(@PathVariable("id") int id){
        byte[] image = companyService.getImage(id);

        return image;
    }

    //-------------Update Company document------------//

    @PutMapping("/update_document")
    public ResponseEntity<StandedResponse> uploadDocument(@RequestParam("image") MultipartFile file, @RequestParam("id") long id) throws IOException {
        String message = companyService.uploadDocument(file,id);

        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",message),HttpStatus.OK);

        return response;
    }

    //-----------------get company document id by id-----------------------

    @GetMapping("get_document_By_id/{id}")
    public byte[]  getDocumentById(@PathVariable("id") int id){
        byte[] image = companyService.getDocument(id);

        return image;
    }


    //--------- get all company by active state true -------

    @GetMapping(
            path = "/get-all-company-by-active-state/{status}"
    )
    public ResponseEntity<StandedResponse>  getAllCompanyByActiveState(@PathVariable(value = "status") boolean activeState){
        List<GetAllCompanyDTO> allCompany = companyService.getAllCompanyByActiveState(activeState);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(
                        201,"sususs",allCompany
                ),HttpStatus.ACCEPTED
        );
        return response;
    }


    //--------- get all companys by ConstructionType -------

    @GetMapping(
            path = "/get-all-company-by-constructionType/{contype}"
    )
    public ResponseEntity<StandedResponse>  getAllCompanyByConstructionType(@PathVariable(value = "contype") String contype){
        List<GetAllCompanyDTO> allCompany = companyService.getAllCompanyByConstructionType(contype);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(
                        201,"sususs",allCompany
                ),HttpStatus.ACCEPTED
        );
        return response;
    }

    //--------- get all companys by Destrict -------

    @GetMapping(
            path = "/get-all-company-by-destrict/{destrict}"
    )
    public ResponseEntity<StandedResponse>  getAllCompanyBydestrict(@PathVariable(value = "contype") String destrict){
        List<GetAllCompanyDTO> allCompany = companyService.getAllCompanyByDestrict(destrict);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(
                        201,"sususs",allCompany
                ),HttpStatus.ACCEPTED
        );
        return response;
    }



}
