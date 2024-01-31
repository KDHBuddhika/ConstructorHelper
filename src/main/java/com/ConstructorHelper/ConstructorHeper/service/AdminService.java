package com.ConstructorHelper.ConstructorHeper.service;

import com.ConstructorHelper.ConstructorHeper.dto.constructionType.PostConstructionTypeDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {
    void updateCompanyActiveStateById(long id,boolean state);

    void updateCustomerActiveStateById(int id, boolean state);

    void updateEmployeeActiveStateById(long id, boolean state);

    String deleteCompany(long id);

    String deleteCustomer(int id);

    String deleteEmployee(long id);

//    String uploadImage(MultipartFile file);

//    String addConstructionType(PostConstructionTypeDTO postConstructionTypeDTO);
}
