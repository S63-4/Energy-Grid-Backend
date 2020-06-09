package com.energygrid.user_service;

import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.common.models.Employee;
import com.energygrid.user_service.common.models.User;
import com.energygrid.user_service.common.security.UserPermissions;
import com.energygrid.user_service.common.security.UserRole;
import com.energygrid.user_service.common.utils.CsvValues;
import com.energygrid.user_service.common.utils.RandomString;
import com.energygrid.user_service.repositories.CustomerRepository;
import com.energygrid.user_service.repositories.EmployeeRepository;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource({"classpath:database.properties", "classpath:email.properties"})
public class UserServiceApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            Customer customer = new Customer("Kevin","Vink",passwordEncoder.encode("test4"),"Kevin@tester.com",true,true,true,true,UserRole.USER.getGrantedAuthorities(),"06123","0612345","4004PD","Coolstreat","Memestreat","9","123123123");
            Employee employee = new Employee("Henk","Kachel",passwordEncoder.encode("test2"),"Henk2@tester.com",true,true,true,true,UserRole.Employee.getGrantedAuthorities(),"1");
            Employee manager = new Employee("Henk","Baas",passwordEncoder.encode("test3"),"Henk3@tester.com",true,true,true,true,UserRole.Manager.getGrantedAuthorities(),"2");

            customerRepository.save(customer);
            employeeRepository.save(employee);
            employeeRepository.save(manager);

        };
    }



    @Configuration
    class RestTemplateConfig {

        // Create a bean for restTemplate to call services
        @Bean
        @LoadBalanced        // Load balance between service instances running at different ports.
        public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
            Duration time = Duration.ofMinutes(1);
            return restTemplateBuilder
                    .setReadTimeout(time)
                    .build();
        }
    }


}
