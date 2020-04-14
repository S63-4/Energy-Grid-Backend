package com.energygrid.user_service.services;


import com.energygrid.user_service.common.models.User;
import com.energygrid.user_service.mail.EmailService;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
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

    public User newUser(User user) {
        userRepository.save(user);
        User newuser = userRepository.findUserByEmail(user.getEmail());
        return newuser;
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

    public boolean changePassword(User user, String oldPass, String newPass) {
        if (passwordEncoder.encode(oldPass) != user.getPassword())
            return false;
        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);
        return true;
    }
}
