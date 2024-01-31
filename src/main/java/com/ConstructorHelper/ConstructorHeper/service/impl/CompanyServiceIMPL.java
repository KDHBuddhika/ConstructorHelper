package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCompanyDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CompanyUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.exception.NotFoundedException;
import com.ConstructorHelper.ConstructorHeper.repo.CompanyRepo;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
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
public class CompanyServiceIMPL implements CompanyService {

     @Autowired
     private CompanyRepo companyRepo;

     @Autowired
     private ModelMapper modelMapper;




    // ******** ********** company register ******** *********** //
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

    // ******** ********** company Login ******** *********** //

    @Override
    public long loginCompany(CompanyLoginDTO companyLoginDTO) {
        Companies companies = companyRepo.findByCompanyEmail(companyLoginDTO.getCompanyEmail());
        if(companies != null){
           String password = companyLoginDTO.getCompanyPassword();
           String encodedPassword = companies.getCompanyPassword();
 //           boolean isPwsRight = passwordEncoder.match(password,encodedPassword);
            boolean isPwsRight= password.equals(encodedPassword);
           if(isPwsRight)
           {
               Optional<Companies> companies1 = companyRepo.findByCompanyEmailAndCompanyPassword(companyLoginDTO.getCompanyEmail(),password);

                   if(companies1.isPresent())
                   {
                       Companies authenticatedCompany = companyRepo.findByCompanyEmailEqualsAndCompanyPasswordEquals(companyLoginDTO.getCompanyEmail(), password);
                       return authenticatedCompany.getCompanyId();
                   }
                   else
                   {
                       return 404;
                   }
           }else
           {
               return 404;
           }

        }else
        {
           return 404;
        }


    }

    //------------------get company details--------------------------

    @Override
    public CompanyResponseAllDTO getCompanyById(long companyId) {
        if(companyRepo.existsById(companyId)){
             Companies companies = companyRepo.getReferenceById(companyId);

             CompanyResponseAllDTO companyResponseAllDTO = new CompanyResponseAllDTO(
                     companies.getCompanyId(),
                     companies.getCompanyName(),
                     companies.getCompanyEmail(),
                     companies.getCompanyPhoneNumber(),
                     companies.getCompanyAddress(),
                     companies.getCompanyDescription(),
                     companies.getCompanyLogo(),
                     companies.getCompanyCV(),
                     companies.isActiveState(),
                     companies.getConstructiontype(),
                     companies.getDistrct()
             );

             return companyResponseAllDTO;
        }
        else {
            throw new NotFoundedException("Not found Details");
        }
    }



    //----------------- company update ------------------

    @Override
    public String updateCompany(CompanyUpdateDTO companyUpdateDTO) {
        if(companyRepo.existsById(companyUpdateDTO.getCompanyId()))
        {
            Companies companies = companyRepo.getReferenceById(companyUpdateDTO.getCompanyId());
            companies.setCompanyName(companyUpdateDTO.getCompanyName());
            companies.setCompanyEmail(companyUpdateDTO.getCompanyEmail());
            companies.setCompanyPhoneNumber(companyUpdateDTO.getCompanyPhoneNumber());
            companies.setCompanyAddress(companyUpdateDTO.getCompanyAddress());
            companies.setCompanyDescription(companyUpdateDTO.getCompanyDescription());
            companies.setConstructiontype(companyUpdateDTO.getConstructiontype());
            companies.setDistrct(companyUpdateDTO.getDistrct());

            companyRepo.save(companies);
            return "save";
        }
        else {
            throw new NotFoundedException("Can not found Id");
        }

    }


    //------------------- company logo update -------------------------------------

    @Override
    public String uploadImage(MultipartFile file, long id) throws IOException {
        Optional<Companies> optionalCompanies = companyRepo.findById(id);

        if (optionalCompanies.isPresent()) {
            Companies companies = optionalCompanies.get();

            // Update the existing employee with the new image
            companies.setCompanyLogo(ImageUtil.compressImage(file.getBytes()));

            // Save the updated employee
            companyRepo.save(companies);

            return "Image uploaded successfully for company with id: " + id;
        } else {
            throw new NotFoundedException("Company with id " + id + " not found");
//            return "Employee with id " + id + " not found";
        }
    }

    //---------------------get company logo ----------------------------------

    @Override
    public byte[] getImage(long id) {
        Optional<Companies> dbDocument = companyRepo.findById(id);
        byte[] image = ImageUtil.decompressImage(dbDocument.get().getCompanyLogo());
        return image;
    }



    //------------------- company Document update -------------------------------------

    @Override
    public String uploadDocument(MultipartFile file, long id) throws IOException {
        Optional<Companies> optionalCompanies1 = companyRepo.findById(id);

        if (optionalCompanies1.isPresent()) {
            Companies companies2 = optionalCompanies1.get();

            // Update the existing employee with the new image
            companies2.setCompanyCV(ImageUtil.compressImage(file.getBytes()));

            // Save the updated employee
            companyRepo.save(companies2);

            return "Document uploaded successfully for company with id: " + id;
        } else {
            throw new NotFoundedException("Company with id " + id + " not found");
//            return "Employee with id " + id + " not found";
        }
    }

    //---------------------get company document ----------------------------------

    @Override
    public byte[] getDocument(long id) {
        Optional<Companies> dbDocument = companyRepo.findById(id);
        byte[] image = ImageUtil.decompressImage(dbDocument.get().getCompanyCV());
        return image;
    }



    //--------- get all company by active state true -------

    @Override
    public List<GetAllCompanyDTO> getAllCompanyByActiveState(boolean b) {
        List<Companies> companies = companyRepo.findByActiveStateEquals(b);
        if(companies.size()>0)
        {
            List<GetAllCompanyDTO> getAllCompanyDTOS =modelMapper.map(companies,new TypeToken<List<GetAllCompanyDTO>>(){}.getType());
            return getAllCompanyDTOS;
        }else {
               throw new NotFoundedException("Companies are not found");
        }
    }

    @Override
    public List<GetAllCompanyDTO> getAllCompanyByConstructionType(String contype) {
        List<Companies> companies = companyRepo.findByConstructiontypeEquals(contype);
        if(companies.size()>0)
        {
            List<GetAllCompanyDTO> getAllCompanyDTOS =modelMapper.map(companies,new TypeToken<List<GetAllCompanyDTO>>(){}.getType());
            return getAllCompanyDTOS;
        }else {
            throw new NotFoundedException("Companies are not found");
        }
    }

    @Override
    public List<GetAllCompanyDTO> getAllCompanyByDestrict(String destrict) {
        List<Companies> companies = companyRepo.findByDistrctEquals(destrict);
        if(companies.size()>0)
        {
            List<GetAllCompanyDTO> getAllCompanyDTOS =modelMapper.map(companies,new TypeToken<List<GetAllCompanyDTO>>(){}.getType());
            return getAllCompanyDTOS;
        }else {
            throw new NotFoundedException("Companies are not found");
        }
    }


}

