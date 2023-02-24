package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {
    public static final String ID = "1";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        List<CustomerDTO> customers = Arrays.asList(newCustomerDTo(1L), newCustomerDTo(2L));

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMERS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {

        //given
        String firstname = "Michale";
        String lastname = "Weston";
        CustomerDTO customer = new CustomerDTO()
                .builder()
                .firstname(firstname)
                .lastname(lastname)
                .customerUrl(CustomerController.CUSTOMERS + ID)
                .build();

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        //when
        mockMvc.perform(get(CustomerController.CUSTOMERS + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(firstname)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname",equalTo(lastname)));
    }

    CustomerDTO newCustomerDTo(Long id) {
        return new CustomerDTO().builder().id(id).firstname("TestName").lastname("TestLastName").build();
    }
}
