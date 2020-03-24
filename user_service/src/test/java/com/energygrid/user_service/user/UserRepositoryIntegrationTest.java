package com.energygrid.user_service.user;
import com.energygrid.common.models.User;
import com.energygrid.common.utils.AuthenticationUtils;
import com.energygrid.user_service.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


import static com.energygrid.common.security.UserRole.USER;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository subject;

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
        User testUser = new User("Test","Testory", passwordEncoder.encode("test212313212312312"),"testaccount@test.nl", "0773077070", "0612345678", "1111TT","SCHOOLSTRAAT", "EINDHOVEN", "11","623415",true,true,true,true,USER.getGrantedAuthorities());
        subject.save(testUser);
        User isThisTestUser = subject.findUserByEmail("testaccount@test.nl");

        assertThat(isThisTestUser, is(testUser));
    }

}
