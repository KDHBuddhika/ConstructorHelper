package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCustomerDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CustomerUpdateDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Customer;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.exception.NotFoundedException;
import com.ConstructorHelper.ConstructorHeper.repo.CustomerRepo;
import com.ConstructorHelper.ConstructorHeper.service.CustomerService;
import com.ConstructorHelper.ConstructorHeper.util.ImageUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

     @Autowired
     private CustomerRepo customerRepo;

     @Autowired
     private ModelMapper modelMapper;


    /* ******************* Customer Register ******************** */

    @Override
    public String registerCustomer(CustomerRegisterDTO customerRegisterDTO) {
        if(!(customerRepo.existsById(customerRegisterDTO.getCoustomerNIC())))
        {
            Customer customer = new Customer();
            customer.setCoustomerNIC(customerRegisterDTO.getCoustomerNIC());
            customer.setCustomerFname(customerRegisterDTO.getCustomerFname());
            customer.setCustomerSname(customerRegisterDTO.getCustomerSname());
            customer.setEmail(customerRegisterDTO.getEmail());
            customer.setPhoneNumber(customerRegisterDTO.getPhoneNumber());
            customer.setPassword(customerRegisterDTO.getPassword());
            customer.setCustomerActiveState(false);

            customerRepo.save(customer);
            return "saved";
        }else{
            throw new AlreadyReportedException("Already have account");
        }
    }


    //----------------------------customer login---------------------------
    @Override
    public int loginCompany(CustomerLoginDTO customerLoginDTO) {

        Customer customer = customerRepo.findByEmail(customerLoginDTO.getCustomerEmail());

        if(customer != null){
            String password = customerLoginDTO.getCustomerPassword();
            String encodedPassword = customer.getPassword();
            //           boolean isPwsRight = passwordEncoder.match(password,encodedPassword);
            boolean isPwsRight= password.equals(encodedPassword);
            if(isPwsRight)
            {
                Optional<Customer> customer1 = customerRepo.findByEmailAndPassword(customerLoginDTO.getCustomerEmail(),password);

                if(customer1.isPresent())
                {
                    Customer customer2 = customerRepo.findByEmailEqualsAndPasswordEquals(customerLoginDTO.getCustomerEmail(),password);

                    return customer2.getCoustomerNIC() ;
                }
                else
                {
                    return 404 ;
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

    //------------------get customer details--------------------------

    @Override
    public CustomerResponseAllDTO getCustomerById(int customerId) {
        if(customerRepo.existsById(customerId)){
           Customer customer = customerRepo.getReferenceById(customerId);

            Optional<Customer> dbDocument = customerRepo.findById(customerId);
            byte[] image = ImageUtil.decompressImage(dbDocument.get().getCustomerPic());


            String imageBase64 = Base64.getEncoder().encodeToString(image);
            CustomerResponseAllDTO customerResponseAllDTO = new CustomerResponseAllDTO(
                    customer.getCoustomerNIC(),
                    customer.getCustomerFname(),
                    customer.getCustomerSname(),
                    customer.getPhoneNumber(),
                    customer.getEmail(),
                    customer.getDescription(),
                    customer.getCustomerAddress(),
                    image,
                    customer.getCustomerCV(),
                    customer.isCustomerActiveState()
            );

            return customerResponseAllDTO;
        }
        else {
            throw new NotFoundedException("Not found Details");
        }
    }




    //----------------------------customer Update---------------------------
    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCoustomerNIC()))
        {
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCoustomerNIC());
              customer.setCoustomerNIC(customerUpdateDTO.getCoustomerNIC());
              customer.setCustomerFname(customerUpdateDTO.getCustomerFname());
              customer.setCustomerSname(customerUpdateDTO.getCustomerSname());
              customer.setPhoneNumber(customerUpdateDTO.getPhoneNumber());
              customer.setEmail(customerUpdateDTO.getEmail());
              customer.setDescription(customerUpdateDTO.getDescription());
              customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());

            customerRepo.save(customer);
            return "save";
        }
        else {
            throw new NotFoundedException("Can not found Id");
        }
    }


    //------------------- company logo update -------------------------------------
    @Override
    public String uploadImage(MultipartFile file,int id) throws IOException {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Update the existing employee with the new image
            customer.setCustomerPic(ImageUtil.compressImage(file.getBytes()));

            // Save the updated employee
            customerRepo.save(customer);

            return "Image uploaded successfully for company with id: " + id;
        } else {
            throw new NotFoundedException("Company with id " + id + " not found");
//            return "Employee with id " + id + " not found";
        }
    }
    //---------------------get customer logo ----------------------------------

    @Override
    public byte[] getImage(int id) {
        Optional<Customer> dbDocument = customerRepo.findById(id);
        byte[] image = ImageUtil.decompressImage(dbDocument.get().getCustomerPic());
        return image;
    }



    //------------------- customer Document update -------------------------------------

    @Override
    public String uploadDocument(MultipartFile file, int id) throws IOException {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Update the existing employee with the new image
            customer.setCustomerCV(ImageUtil.compressImage(file.getBytes()));

            // Save the updated employee
            customerRepo.save(customer);

            return "Image uploaded successfully for company with id: " + id;
        } else {
            throw new NotFoundedException("Company with id " + id + " not found");
//            return "Employee with id " + id + " not found";
        }
    }



    //---------------------get customer document ----------------------------------
    @Override
    public byte[] getDocument(int id) {
        Optional<Customer> dbDocument = customerRepo.findById(id);
        byte[] image = ImageUtil.decompressImage(dbDocument.get().getCustomerCV());

        return image;
    }




    //--------- get all company by active state true -------

    @Override
    public List<GetAllCustomerDTO> getAllCustomerByActiveState(boolean activeState) {
        List<Customer> customers = customerRepo.findByCustomerActiveStateEquals(activeState);
        if(customers.size()>0)
        {
            List<GetAllCustomerDTO> getAllCustomerDTOS =modelMapper.map(customers,new TypeToken<List<GetAllCustomerDTO>>(){}.getType());
            return getAllCustomerDTOS;
        }else {
            throw new NotFoundedException("Companies are not found");
        }
    }


}
