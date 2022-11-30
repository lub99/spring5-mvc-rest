package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRSTNAME = "Mat";
    public static final long ID = 1L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllCustomers() throws Exception {

        List<CustomerDto> customerDtos = Arrays.asList(new CustomerDto(), new CustomerDto());

        when(customerService.getAllCustomers()).thenReturn(customerDtos);


        mockMvc.perform(get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerMapper.customerToCustomerDto(customer));

        mockMvc.perform(get("/api/v1/customers/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)));
    }
}