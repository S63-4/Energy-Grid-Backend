package com.energygrid.user_service.services;


import com.energygrid.user_service.AuthenticationUtils;
import com.energygrid.user_service.common.dto.CustomerDTO;
import com.energygrid.user_service.common.dto.CustomerRegisterDTO;
import com.energygrid.user_service.common.dto.ProfileDTO;
import com.energygrid.user_service.common.exceptions.BadRequestException;
import com.energygrid.user_service.common.exceptions.DatabaseException;
import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.common.utils.RandomString;
import com.energygrid.user_service.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.energygrid.user_service.common.security.UserRole.USER;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Iterable<Customer> allCustomers (){
        return customerRepository.findAll();
    }

    public Iterable<ProfileDTO> allCustomerProfiles (){

        ArrayList<ProfileDTO> profiles = new ArrayList<>();

        for (Customer customer: customerRepository.findAll()) {
            profiles.add(modelMapper.map(customer, ProfileDTO.class));
        }


        return profiles;
    }

    public String newCustomer(CustomerDTO customer) {

        RandomString randomString = new RandomString();

        Customer newCustomer = modelMapper.map(customer, Customer.class);

        newCustomer.setAuthorities(USER.getGrantedAuthorities());
        newCustomer.setCustomerCode(randomString.getAlphaNumericString(12));
        newCustomer.setEnabled(true);
        newCustomer.setPassword("null");

        try{
            customerRepository.save(newCustomer);

            //emailService.sendRegistrationMail(newCustomer.getEmail(), newCustomer.getCustomerCode());

            return "saved";
        }catch (Exception ex){
            throw new DatabaseException("New customer not saved");
        }
    }

    public String register(CustomerRegisterDTO user) {
        final Pattern Email = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        System.out.println("Received: " + user.getCustomerCode());

        if (user == null) throw new IllegalArgumentException("The user object is not allowed to be null.");

        if (user.getEmail().isEmpty() || user.getEmail() == null) {
            throw new IllegalArgumentException("Email can`t be empty or null");
        }

        if (user.getPassword().isEmpty() || user.getPassword() == null) {
            throw new IllegalArgumentException("Password can`t be empty or null");
        }
        if (user.getPassword().length() < 8) {
            throw new BadRequestException("Password must be at least 8 characters");
        }
        if (!Email.matcher(user.getEmail()).find()) {
            throw new IllegalArgumentException("The email should be a valid email address.");
        }
        if (customerRepository.existsByEmail(user.getEmail()) && customerRepository.existsByCustomerCode(user.getCustomerCode())) {

            var userEntity = customerRepository.findCustomerByCustomerCode(user.getCustomerCode());

            var updateUser = modelMapper.map(userEntity, Customer.class);

            updateUser.setAccountNonLocked(true);
            updateUser.setAccountNonExpired(true);
            updateUser.setCredentialsNonExpired(true);

            updateUser.setPassword(new AuthenticationUtils().encode(user.getPassword()));
            customerRepository.save(updateUser);
            updateUser.setPassword(null);
            return "saved";
        } else {
            throw new BadRequestException("Wrong combination");
        }
    }

    public ProfileDTO getCustomerByCustomerCode(String customerCode) {
        if(customerCode == ""){
            throw new BadRequestException("Customer code cannot be empty");
        }
        else{
            Customer customer = customerRepository.findCustomerByCustomerCode(customerCode);

            if(customer == null){
                throw new BadRequestException("Customer not found");
            }
            else{
                return modelMapper.map(customer, ProfileDTO.class);
            }
        }
    }

    public Customer getByCustomerCode(String customerCode) {
        return customerRepository.findCustomerByCustomerCode(customerCode);
    }

    public String deleteCustomerByCustomerCode(String customerCode){
        try {
            customerRepository.delete(customerRepository.findCustomerByCustomerCode(customerCode));
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Account deleted.";
    }

    public String updateProfile(ProfileDTO user) throws Exception {
        try {
            var userEntity = customerRepository.findCustomerByCustomerCode(user.getCustomerCode());
            var updateUser = modelMapper.map(userEntity, Customer.class);
            customerRepository.save(updateUser);

            return "Profile updated";
        } catch (Exception ex) {
            throw new Exception("Unable to update user in database");
        }
    }
    private Boolean hasAuthority(Long id, Authentication authentication){
        // id is the user id you expect.

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();
        Customer customer = customerRepository.findCustomerByCustomerCode(email);


        if(id.equals(customer.getId()) || customer.getAuthorities().contains("employee:read")){
            return true;
        }
        else
        {
            return false;
        }
    }

}
