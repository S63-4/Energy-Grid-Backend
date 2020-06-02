package com.energygrid.user_service.controllers;

import com.energygrid.user_service.AuthenticationUtils;
import com.energygrid.user_service.UserServiceApplication;
import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.repositories.CustomerRepository;
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

import static com.energygrid.user_service.common.security.UserRole.USER;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository subject;

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
    @Ignore
    public void shouldSaveAndFetch() {
        Customer testUser = new Customer("Test","Testory", passwordEncoder.encode("test212313212312312"), "test@test.ok.nl", true, true, true, true, USER.getGrantedAuthorities(), "0612345678", "0687654321",
                "5987AK", "Schoolstraat", "32", "Kegchel", "123456");
        subject.save(testUser);
        Customer isThisTestUser = subject.findCustomerByCustomerCode("123456");

        assertThat(isThisTestUser,  equalTo("123456"));
    }

}
