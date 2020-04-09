package com.energygrid.user_service.controllers;

import com.energygrid.common.dto.CustomerDTO;
import com.energygrid.common.dto.CustomerRegisterDTO;
import com.energygrid.common.models.Customer;
import com.energygrid.user_service.repositories.CustomerRepository;
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
        CustomerDTO newCustomer = new CustomerDTO("testname", "surname", "testmail@email.com",
                "0494829","06123456", "4750DD", "teststreet",
                "teststad", "10");

        Gson gson = new Gson();
        String json = gson.toJson(newCustomer);

        String result = subject.newCustomer(json);

        assertThat(newCustomer.getFirstName(), is(result));
   }

    @Test
    public void shouldRegisterCustomer() throws Exception {
        Gson gson = new Gson();

        CustomerRegisterDTO customerRegister = new CustomerRegisterDTO("test1234","password", "12345");

        String registration = gson.toJson(customerRegister);

        String saved = subject.customerRegister(registration);
    }
}