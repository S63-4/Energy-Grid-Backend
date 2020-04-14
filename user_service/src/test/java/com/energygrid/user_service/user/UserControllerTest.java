package com.energygrid.user_service.user;
import com.energygrid.user_service.common.dto.ProfileDTO;
import com.energygrid.user_service.controllers.UserController;
import com.energygrid.user_service.repositories.UserRepository;
import com.energygrid.user_service.services.UserService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@ActiveProfiles("test")
public class UserControllerTest {
    private UserController subject;

    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        //subject = new UserController(userService, userRepository);
    }


    @Test
    public void shouldRegisterUser() throws Exception {

    }

    @Test
    public void shouldReturnProfile() throws Exception {
        ProfileDTO testUser  = new ProfileDTO("victor","victory","test@test.com","0773077070","0612345678","5981TT","kerkstraat","EINDHOVEN","33","123456");
        given(userService.getUserByCustomerCode("123456")).willReturn(testUser);
        ProfileDTO code = subject.getProfile();
        assertThat(code.getCustomerCode(), is("123456"));

    }




}
