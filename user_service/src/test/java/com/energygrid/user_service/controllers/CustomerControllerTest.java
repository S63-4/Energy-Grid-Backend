package com.energygrid.user_service.controllers;

import com.energygrid.common.dto.CustomerDTO;
import com.energygrid.common.dto.CustomerRegisterDTO;
import com.energygrid.common.dto.ProfileDTO;
import com.energygrid.user_service.services.CustomerService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomerControllerTest {

    private CustomerController subject;

    @Mock
    private CustomerService customerService;
    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new CustomerController(customerService, modelMapper);
    }

    @Test
    public void shouldReturnNewCustomer() throws Exception {
        Gson gson = new Gson();

        CustomerDTO newCustomer = new CustomerDTO("testname", "surname", "testmail@email.com",
                "0494829","06123456", "4750DD", "teststreet",
                "teststad", "10");

        String json = gson.toJson(newCustomer);

        String result = subject.newCustomer(json);

        assertThat(newCustomer.getFirstName(), is(result));
   }

    @Test
    public void shouldRegisterCustomer() throws Exception {
        Gson gson = new Gson();

        CustomerDTO newCustomer = new CustomerDTO("testname", "surname", "testmail@email.com",
                "0494829","06123456", "4750DD", "teststreet",
                "teststad", "10");

        CustomerRegisterDTO customerRegister = new CustomerRegisterDTO("testmail@email.com","password", "123456");

        given(customerService.register(customerRegister)).willReturn("saved");

        String registration = gson.toJson(customerRegister);

        String saved = subject.customerRegister(registration);
    }

    @Test
    public void shouldReturnProfile() throws Exception {
        ProfileDTO testUser  = new ProfileDTO("victor","victory","test@test.com","0773077070","0612345678","5981TT","kerkstraat","EINDHOVEN","33","123456");
        given(customerService.getCustomerByCustomerCode("123456")).willReturn(testUser);
        ProfileDTO code = subject.getProfile();
        assertThat(code.getCustomerCode(), is("123456"));
    }
}