package com.energygrid.user_service.controllers;

import com.energygrid.user_service.common.dto.CustomerDTO;
import com.energygrid.user_service.common.dto.ProfileDTO;
import com.energygrid.user_service.common.models.Customer;
import com.energygrid.user_service.services.CustomerService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


import static com.energygrid.user_service.common.security.UserRole.Employee;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@RunWith(SpringRunner.class)
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
   public void shouldGetAllCustomers() throws Exception   {
       Customer customer1 = new Customer("victor", "victory", "password",
               "test@test.com", true, true, true,
               true, Employee.getGrantedAuthorities(), "0773077070",
               "0612345678", "4354DD", "teststraat", "woenselbois", "54", "123456");

       Customer customer2 = new Customer("Kees", "Kachel", "password",
               "kachel@test.com", true, true, true,
               true, Employee.getGrantedAuthorities(), "0773077070",
               "0612345678", "4354DD", "teststraat", "woensel", "54", "654321");

       given(customerService.allCustomers()).willReturn(Arrays.asList(customer1, customer2));

       Iterable<Customer> response =  subject.allUsers();

       assertThat(response, Matchers.<Customer> iterableWithSize(2));
   }

   @Test
   public void shouldGetCustomerByCode() throws Exception {
       Customer customer = new Customer("Kees", "Kachel", "password",
               "kachel@test.com", true, true, true,
               true, Employee.getGrantedAuthorities(), "0773077070",
               "0612345678", "4354DD", "teststraat", "woensel", "54", "654321");

       given(customerService.getByCustomerCode("654321")).willReturn(customer);

       Customer response = subject.getCustomerByCode("654321");

       assertThat(response.getCustomerCode(), is("654321"));
   }

    @Test
    public void shouldReturnProfile() throws Exception {
        ProfileDTO testUser  = new ProfileDTO("victor","victory","test@test.com","0773077070","0612345678","5981TT","kerkstraat","EINDHOVEN","33","123456");
        given(customerService.getCustomerByCustomerCode("123456")).willReturn(testUser);
        ProfileDTO code = subject.getProfile();
        assertThat(code.getCustomerCode(), is("123456"));
    }
}