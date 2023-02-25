package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest extends TestCase {



    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerServiceImpl customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService=new CustomerServiceImpl();
        customerService.setCustomerMapper(customerMapper);
        customerService.setCustomerRepository(customerRepository);
       // customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(customers.size(), customerDTOS.size());
    }



    public void testGetCustomerById() throws Exception {
        // given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer1));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1l);

        //then
        assertEquals(customerDTO.getId(), customer1.getId());
        assertEquals(customerDTO.getLastname(), customer1.getLastname());
        assertEquals(customerDTO.getFirstname(), customer1.getFirstname());
        assertEquals(customerDTO.getCustomerUrl(), CustomerController.CUSTOMERS + customer1.getId());
    }

    public void testCreateNewCustomer() throws Exception {
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");
        customerDTO.setLastname("Никитович");


        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1l);
        when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(CustomerController.CUSTOMERS+savedCustomer.getId(),savedDto.getCustomerUrl());


    }
}