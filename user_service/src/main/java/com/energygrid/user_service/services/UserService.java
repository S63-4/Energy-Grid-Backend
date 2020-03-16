package com.energygrid.user_service.services;


import com.energygrid.common.dto.ProfileDTO;
import com.energygrid.common.dto.RegisterDTO;
import com.energygrid.common.exceptions.BadRequestException;
import com.energygrid.common.models.User;
import com.energygrid.common.utils.AuthenticationUtils;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

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

    public String registerUser(RegisterDTO user) {
        final Pattern Email = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        System.out.println("Received: " + user.getCustomerCode());

        if (user == null) throw new IllegalArgumentException("The user object is not allowed to be null.");

        if(user.getEmail().isEmpty() || user.getEmail() ==null){
            throw new IllegalArgumentException("Email can`t be empty or null");
        }

        if (user.getPassword().isEmpty() || user.getPassword() == null){
            throw new IllegalArgumentException("Password can`t be empty or null");
        }
        if (user.getPassword().length() < 8){
            throw new BadRequestException("Password must be at least 8 characters");
        }
        if (!Email.matcher(user.getEmail()).find()) {
            throw new IllegalArgumentException("The email should be a valid email address.");
        }
        if (userRepository.existsByEmail(user.getEmail()) && userRepository.existsByCustomerCode(user.getCustomerCode())){

                User userEntity = userRepository.findUserByCustomerCode(user.getCustomerCode());

                User updateUser = modelMapper.map(userEntity, User.class);

                updateUser.setPassword(new AuthenticationUtils().encode(user.getPassword()));
                userRepository.save(updateUser);
                updateUser.setPassword(null);
                return "saved";
            }
            else{
                throw new BadRequestException("Wrong combination");
            }
    }

    public ProfileDTO getUserByCustomerCode(String customerCode){
        return modelMapper.map(userRepository.findUserByCustomerCode(customerCode), ProfileDTO.class);
    }

    public String updateProfile(ProfileDTO user) throws Exception{
        try{
            User userEntity = userRepository.findUserByCustomerCode(user.getCustomerCode());
            User updateUser = modelMapper.map(userEntity, User.class);
            userRepository.save(updateUser);

            return "Profile updated";
        }

        catch (Exception ex){
            throw new Exception("Unable to update user in database");
        }
    }

}