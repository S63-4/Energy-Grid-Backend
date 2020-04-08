package com.energygrid.user_service.controllers;

import com.energygrid.common.models.User;
import com.energygrid.user_service.mail.EmailService;
import com.energygrid.user_service.repositories.UserRepository;
import com.energygrid.user_service.services.UserService;
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
        return userRepository.findUserByEmail(email);
    }

    @RequestMapping(value = RestURIConstant.getUserByCode, method = RequestMethod.GET)
    public @ResponseBody
    User getCustomerByCode(@RequestParam("email") String code) {
        return userService.getByEmail(code);
    }

    @RequestMapping(value = RestURIConstant.test, method = RequestMethod.GET)
    public String test() {
        return "Test works";
    }

}
