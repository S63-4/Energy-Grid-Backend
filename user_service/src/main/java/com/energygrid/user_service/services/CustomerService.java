package com.energygrid.user_service.services;

import com.energygrid.common.dto.CustomerDTO;
import com.energygrid.common.dto.CustomerRegisterDTO;
import com.energygrid.common.dto.ProfileDTO;
import com.energygrid.common.exceptions.BadRequestException;
import com.energygrid.common.exceptions.DatabaseException;
import com.energygrid.common.models.Customer;
import com.energygrid.common.models.User;
import com.energygrid.common.utils.AuthenticationUtils;
import com.energygrid.common.utils.RandomString;
import com.energygrid.user_service.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.energygrid.common.security.UserRole.USER;

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
        return modelMapper.map(customerRepository.findCustomerByCustomerCode(customerCode), ProfileDTO.class);
    }

    public Customer getByCustomerCode(String customerCode) {
        return customerRepository.findCustomerByCustomerCode(customerCode);
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

}
