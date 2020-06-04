package com.energygrid.user_service.user;

import com.energygrid.user_service.AuthenticationUtils;
import com.energygrid.user_service.common.models.User;
import com.energygrid.user_service.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static com.energygrid.user_service.common.security.UserRole.USER;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository subject;

    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {

    }


    @After
    public void tearDown() {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndFetch() {
        User testUser = new User("Test","Testory", "test212313212312312",
                "testaccount@test.nl", true, true, true, true, USER.getGrantedAuthorities());
        subject.save(testUser);
        User isThisTestUser = subject.findUserByEmail("testaccount@test.nl");

        assertThat(isThisTestUser.getEmail(),  equalTo("testaccount@test.nl"));
    }

}
