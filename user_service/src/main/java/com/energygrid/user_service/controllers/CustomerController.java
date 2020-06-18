package com.energygrid.user_service.controllers;

import com.energygrid.user_service.common.dto.CustomerDTO;
import com.energygrid.user_service.common.dto.CustomerRegisterDTO;
import com.energygrid.user_service.common.dto.ProfileDTO;
import com.energygrid.user_service.common.exceptions.BadRequestException;
import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.common.models.User;
import com.energygrid.user_service.services.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            return customerService.updateProfile(customerObject,SecurityContextHolder.getContext().getAuthentication());
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

    @PreAuthorize("hasRole('Employee')")
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

    @PreAuthorize("hasRole('Employee')")
    @RequestMapping(value = RestURIConstant.getCustomerByCustomerCode, method = RequestMethod.GET)
    public @ResponseBody
    ProfileDTO getCustomerByCustomerCode(@RequestParam("customercode") String code) {
        return customerService.getCustomerByCustomerCode(code);
    }

    @PreAuthorize("hasRole('Employee')")
    @RequestMapping(value = RestURIConstant.deleteCustomerByCustomerCode, method = RequestMethod.GET)
    public @ResponseBody
    String deleteCustomerByCustomerCode(@RequestParam("customercode") String code) {
        Gson gson = new Gson();
        return gson.toJson(customerService.deleteCustomerByCustomerCode(code));
    }

    @PreAuthorize("hasRole('Employee')")
    @RequestMapping(value = RestURIConstant.allCustomers, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Customer> allUsers() {
        return customerService.allCustomers();
    }

    @PreAuthorize("hasRole('Employee')")
    @RequestMapping(value = RestURIConstant.allCustomerProfiles, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<ProfileDTO> allCustomerProfiles() {
        return customerService.allCustomerProfiles();
    }
}
