package com.energygrid.user_service.services;


import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.common.models.User;
import com.energygrid.user_service.mail.EmailService;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    public Long getId(String email){
        return userRepository.findIdByEmail(email);
    }


    public Iterable<User> alluser() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void DeleteUser(User user) {
        userRepository.delete(user);
    }

    public boolean changePassword(User user, String oldPass, String newPass, Authentication auth) {

        if(hasAuthority(user.getId(),auth)){
            if (passwordEncoder.encode(oldPass) != user.getPassword())
                return false;
            user.setPassword(passwordEncoder.encode(newPass));
            userRepository.save(user);
            return true;
        }
        else
            return false;


    }

    private Boolean hasAuthority(Long id, Authentication authentication){
        // id is the user id you expect.

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();
        User user = userRepository.findUserByEmail(email);


        if(id.equals(user.getId()) || user.getAuthorities().contains("employee:read")){
            return true;
        }
        else
        {
            return false;
        }
    }
}
