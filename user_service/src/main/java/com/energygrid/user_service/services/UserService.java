package com.energygrid.user_service.services;


import com.energygrid.common.models.User;
import com.energygrid.user_service.mail.EmailService;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, EmailService emailService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
    }

    public User newUser(User user) {
        userRepository.save(user);
        User newuser = userRepository.findUserByEmail(user.getEmail());
        return newuser;
    }

    public Iterable<User> alluser (){
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void DeleteUser(User user) {
        userRepository.delete(user);
    }
}
