package com.energygrid.user_service.controllers;

import com.energygrid.user_service.AuthenticationUtils;
import com.energygrid.user_service.UserServiceApplication;
import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.common.models.Employee;
import com.energygrid.user_service.repositories.CustomerRepository;
import com.energygrid.user_service.repositories.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.energygrid.user_service.common.security.UserRole.USER;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")

public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private EmployeeRepository subject;

    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        passwordEncoder = new AuthenticationUtils();
    }


    @After
    public void tearDown() {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndAdd() {
        Employee testUser = new Employee("asd","asd","Test","kachel@test.nl",
                true,true,true,true,USER.getGrantedAuthorities(),"123456");
        subject.save(testUser);
        var users = (List<Employee>) subject.findAll();
        assertNotNull(users);

    }

}
