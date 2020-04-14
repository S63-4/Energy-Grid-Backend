package com.energygrid.user_service.repositories;

import com.energygrid.common.models.Customer;
import com.energygrid.common.utils.AuthenticationUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static com.energygrid.common.security.UserRole.USER;
import static org.hamcrest.Matchers.is;
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
    public void shouldSaveAndFetch() {
        Customer testCustomer = new Customer("Test","Testory", passwordEncoder.encode("test212313212312312"),"testaccount@test.nl", false, false, false,false, USER.getGrantedAuthorities(), "11","623415", "7593DD", "testStraat", "test", "50", "445467838");
        subject.save(testCustomer);
        Customer isThisTestUser = subject.findCustomerByCustomerCode("445467838");

        assertThat(isThisTestUser, is(testCustomer));
    }
}