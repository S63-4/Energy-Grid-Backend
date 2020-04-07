package com.energygrid.user_service.controllers;

import com.energygrid.common.dto.CustomerDTO;
import com.energygrid.common.dto.ProfileDTO;
import com.energygrid.common.dto.RegisterDTO;
import com.energygrid.common.exceptions.BadRequestException;
import com.energygrid.common.models.User;
import com.energygrid.user_service.repositories.UserRepository;
import com.energygrid.user_service.services.EmailService;
import com.energygrid.user_service.services.UserService;
import com.google.gson.Gson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private final UserRepository userRepository;

    public UserController(UserService userService, EmailService emailService, UserRepository userRepository) {
        this.userService = userService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = RestURIConstant.deleteUser, method = RequestMethod.DELETE)
    public void delete(@RequestBody User user) {
        userService.DeleteUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = RestURIConstant.allUsers, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<User> allUsers() {
        return userService.alluser();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = RestURIConstant.currentUser, method = RequestMethod.GET)
    public @ResponseBody
    User current() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String email = (String) auth.getPrincipal();
        return userRepository.findUserByCustomerCode(email);
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = RestURIConstant.getUserProfile, method = RequestMethod.GET)
    public @ResponseBody
    ProfileDTO getProfile() {
        var customerCode = "123456";
//        var customerCode = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByCustomerCode(customerCode != null ? customerCode : "0");
    }

    @PostMapping(value = RestURIConstant.userRegistration)
    public @ResponseBody
    String userRegister(@RequestBody String user) {
        try {

            Gson gson = new Gson();
            var userObject = gson.fromJson(user, RegisterDTO.class);

            return gson.toJson(userService.registerUser(userObject));
        } catch (Exception e) {
            throw new BadRequestException("Failed to register, check your email/code combination");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = RestURIConstant.newCustomer)
    public @ResponseBody
    String newCustomer(@RequestBody String user){

        try {
            Gson gson = new Gson();
            var userObject = gson.fromJson(user, CustomerDTO.class);

            userService.newCustomer(userObject);

            return userObject.getFirstName();
        } catch (Exception e) {
            throw new BadRequestException("error");

        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = RestURIConstant.updateProfile)
    public @ResponseBody
    String updateProfileDetails(@RequestBody String user) {
        try {
            //TODO: Get current user
            Gson gson = new Gson();
            var userObject = gson.fromJson(user, ProfileDTO.class);
            return userService.updateProfile(userObject);
        } catch (Exception e) {
            throw new BadRequestException("Failed to update profile");
        }
    }

    @RequestMapping(value = RestURIConstant.getUserByCode, method = RequestMethod.GET)
    public @ResponseBody User getUserByCode(@RequestParam("code") String code){
        return userService.getByCustomerCode(code);
    }

    @RequestMapping(value = RestURIConstant.test, method = RequestMethod.GET)
    public String test() {
        return "Test works";
    }

}
