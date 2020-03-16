package com.energygrid.user_service.controllers;

import com.energygrid.common.dto.ProfileDTO;
import com.energygrid.common.dto.RegisterDTO;
import com.energygrid.common.dto.StatusDTO;
import com.energygrid.common.exceptions.BadRequestException;
import com.energygrid.common.models.User;
import com.energygrid.user_service.repositories.UserRepository;
import com.energygrid.user_service.services.StatusService;
import com.energygrid.user_service.services.UserService;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final StatusService statusService;
    private final UserRepository userRepository;

    public UserController(UserService userService, StatusService statusService, UserRepository userRepository) {
        this.userService = userService;
        this.statusService = statusService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = RestURIConstant.newUser, method = RequestMethod.POST)
    public @ResponseBody
    User newUser(@RequestBody User user) {
        return userService.newUser(user);
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    @RequestMapping(value = RestURIConstant.deleteUser, method = RequestMethod.DELETE)
    public void delete(@RequestBody User user) {
        userService.DeleteUser(user);
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    @RequestMapping(value = RestURIConstant.allUsers, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<User> allUsers() {
        return userService.alluser();
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = RestURIConstant.currentUser, method = RequestMethod.GET)
    public @ResponseBody
    User current() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String email = (String) auth.getPrincipal();
        return userRepository.findUserByCustomerCode(email);
    }

    @RequestMapping(value = RestURIConstant.getUserStatus, method = RequestMethod.GET)
    public @ResponseBody
    List<StatusDTO> getUserStatus(@RequestParam("id") Long id) {
        //  User user = (User) getCurrentAuthorizedUser(User.class);
        return statusService.getStatusById(id);
    }

    @RequestMapping(value = RestURIConstant.getUserProfile, method = RequestMethod.GET)
    public @ResponseBody
    ProfileDTO getProfile(@RequestParam("customerCode") String customerCode){
        return userService.getUserByCustomerCode(customerCode);
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
}
