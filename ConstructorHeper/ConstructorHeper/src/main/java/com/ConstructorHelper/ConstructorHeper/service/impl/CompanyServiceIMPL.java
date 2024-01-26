package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.repo.CompanyRepo;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CompanyServiceIMPL implements CompanyService {

     @Autowired
     private CompanyRepo companyRepo;




    //******************company register*******************
    @Override
    public String registerCompany(CompanyRegisterDTO companyRegisterDTO) {
       if(!(companyRepo.existsById(companyRegisterDTO.getCompanyId())))
        {
            Companies companies = new Companies();
            companies.setCompanyId(companyRegisterDTO.getCompanyId());
            companies.setCompanyName(companyRegisterDTO.getCompanyName());
            companies.setCompanyEmail(companyRegisterDTO.getCompanyEmail());
            companies.setCompanyPhoneNumber(companyRegisterDTO.getCompanyPhoneNumber());
//            companies.setCompanyPassword(passwordEncoder.encode(companyRegisterDTO.getCompanyPassword()));
            companies.setCompanyPassword(companyRegisterDTO.getCompanyPassword());
            companies.setActiveState(false);

            companyRepo.save(companies);
            return "saved";
        }else{
           throw new AlreadyReportedException("Already have account");
        }

    }

    @Override
    public String LoginCompany(CompanyLoginDTO companyLoginDTO) {
        Companies companies = companyRepo.findByCompanyEmail(companyLoginDTO.getCompanyEmail());
        if(companies != null){
           String password = companyLoginDTO.getCompanyPassword();
           String encodedPassword = companies.getCompanyPassword();
//           boolean isPwsRight = passwordEncoder.match(password,encodedPassword);
            boolean isPwsRight= password.equals(encodedPassword);
           if(isPwsRight)
           {
               Optional<Companies> companies1 = companyRepo.findByCompanyEmailAndCompanyPassword(companyLoginDTO.getCompanyEmail(),encodedPassword);
               if(companies1.isPresent())
               {
                   return"Login Successfully";
               }
               else {
                   return"Login Fail";
               }
           }else {
               return "Password is Not match";
           }
       }else {
           return "Email not Exist";
       }
    }
}

//    @Override
//    public LoginResponse LoginCompany(CompanyLoginDTO companyLoginDTO) {
//       String msg = "";
//       Companies companies1 = companyRepo.findByCompanyEmail(companyLoginDTO.getCompanyEmail());
//       if(companies1 != null){
//           String password = companyLoginDTO.getCompanyPassword();
//           String encodedPassword = companies1.getCompanyPassword();
////           boolean isPwsRight = passwordEncoder.match(password,encodedPassword);
//           if(password == encodedPassword)
//           {
//               Optional<Companies> companies = companyRepo.findByCompanyEmailAndCompanyPassword(companyLoginDTO,encodedPassword);
//               if(companies.isPresent())
//               {
//                   return new LoginResponse("Login Successfully",true);
//               }
//               else {
//                   return new LoginResponse("Login Fail",false);
//               }
//           }else {
//               return new LoginResponse("Password is Not match",false);
//           }
//       }else {
//           return new LoginResponse("Email not Exist",false);
//       }
//    }

