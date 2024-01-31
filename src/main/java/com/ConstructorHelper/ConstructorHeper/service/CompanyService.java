package com.ConstructorHelper.ConstructorHeper.service;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCompanyDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CompanyUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface CompanyService {
    String registerCompany(CompanyRegisterDTO companyRegisterDTO);


   long loginCompany(CompanyLoginDTO companyLoginDTO);

    CompanyResponseAllDTO getCompanyById(long companyId);

    String updateCompany(CompanyUpdateDTO companyUpdateDTO);

    String uploadImage(MultipartFile file, long id) throws IOException;

    String uploadDocument(MultipartFile file, long id) throws IOException;

    byte[] getImage(long id);

    byte[] getDocument(long id);

    List<GetAllCompanyDTO> getAllCompanyByActiveState(boolean activeState);


    List<GetAllCompanyDTO> getAllCompanyByConstructionType(String contype);

    List<GetAllCompanyDTO> getAllCompanyByDestrict(String destrict);
}
