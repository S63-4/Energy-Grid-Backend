package com.energygrid.user_service.controllers;


import com.energygrid.user_service.common.dto.CustomerDTO;
import com.energygrid.user_service.common.dto.CustomerRegisterDTO;
import com.energygrid.user_service.common.dto.ProfileDTO;
import com.energygrid.user_service.common.exceptions.BadRequestException;
import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.controllers.RestURIConstant;
import com.energygrid.user_service.services.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = RestURIConstant.updateProfile)
    public @ResponseBody
    String updateProfileDetails(@RequestBody String customer) {
        try {
            //TODO: Get current customer
            Gson gson = new Gson();
            var customerObject = gson.fromJson(customer, ProfileDTO.class);
            return customerService.updateProfile(customerObject);
        } catch (Exception e) {
            throw new BadRequestException("Failed to update profile");
        }
    }

    @RequestMapping(value = RestURIConstant.getCustomerByCode, method = RequestMethod.GET)
    public @ResponseBody
    Customer getCustomerByCode(@RequestParam("code") String code) {
        return customerService.getByCustomerCode(code);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = RestURIConstant.getCustomerProfile, method = RequestMethod.GET)
    public @ResponseBody
    ProfileDTO getProfile() {
        var customerCode = "123456";
//        var customerCode = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customerService.getCustomerByCustomerCode(customerCode != null ? customerCode : "0");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = RestURIConstant.newCustomer)
    public @ResponseBody
    String newCustomer(@RequestBody String user){

        try {
            Gson gson = new Gson();
            var userObject = gson.fromJson(user, CustomerDTO.class);

            customerService.newCustomer(userObject);

            return userObject.getFirstName();
        } catch (Exception e) {
            throw new BadRequestException("error");

        }
    }

    @PostMapping(value = RestURIConstant.customerRegistration)
    public @ResponseBody
    String customerRegister(@RequestBody String customer) {
        try {

            Gson gson = new Gson();
            var customerObject = gson.fromJson(customer, CustomerRegisterDTO.class);

            return gson.toJson(customerService.register(customerObject));
        } catch (Exception e) {
            throw new BadRequestException("Failed to register, check your email/code combination");

        }
    }


}
