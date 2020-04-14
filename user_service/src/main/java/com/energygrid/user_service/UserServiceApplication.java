package com.energygrid.user_service;

import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.common.models.User;
import com.energygrid.user_service.common.utils.CsvValues;
import com.energygrid.user_service.common.utils.RandomString;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;

import static com.energygrid.user_service.common.security.UserRole.ADMIN;
import static com.energygrid.user_service.common.security.UserRole.USER;


@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,RepositoryRestMvcAutoConfiguration.class})
@ComponentScan
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserServiceApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            AuthenticationUtils auth = new AuthenticationUtils();
            RandomString rdm = new RandomString();
            BufferedReader reader = new BufferedReader(new FileReader("enexis_electricity_01012010_mod.csv"));
            // Do one readLine to skip the first line of column headers
            reader.readLine();
            String value1 = reader.readLine();
            String value2 = reader.readLine();
            String[] data1 = value1.split(",");
            String[] data2 = value2.split(",");

            var user1 = new Customer("victor", "victory", passwordEncoder.encode("test2"), "test@test.com", true, true, true,
                    true, ADMIN.getGrantedAuthorities(), "0773077070", "0612345678", data1[CsvValues.ZIPCODE.getValue()], data1[CsvValues.STREET.getValue()], data1[CsvValues.CITY.getValue()], data1[CsvValues.HOUSE_NUMBER.getValue()], "123456"); //default
            //var user2 = new User("Piet","Pieters",passwordEncoder.encode("test1"),"test@test.nl", "0773086060", "0687654321",data2[CsvValues.ZIPCODE.getValue()],data2[CsvValues.STREET.getValue()], data2[CsvValues.CITY.getValue()], data2[CsvValues.HOUSE_NUMBER.getValue()], "007",true,true,true,true, USER.getGrantedAuthorities()); //default

            user1 = userRepository.save(user1);
            //  user2 = userRepository.save(user2);
        };
    }
    @Configuration
    class RestTemplateConfig {

        // Create a bean for restTemplate to call services
        @Bean
        @LoadBalanced        // Load balance between service instances running at different ports.
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }


}
