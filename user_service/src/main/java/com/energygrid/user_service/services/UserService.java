package com.energygrid.user_service.services;


import com.energygrid.common.models.User;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public User newUser(User user){
        userRepository.save(user);
        User newuser = userRepository.findUserByEmail(user.getEmail());
        return newuser;
    }
    public Iterable<User> alluser (){
        return userRepository.findAll();
    }

    public void DeleteUser(User user){
        userRepository.delete(user);
    }


}
