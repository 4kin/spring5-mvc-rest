package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private  CustomerMapper customerMapper;
    private  CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerServiceImpl() {

    }

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl(CustomerController.CUSTOMERS + customer.getId());
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id).map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(CustomerController.CUSTOMERS + customer.getId());
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new); //todo implement better exception handling
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl(CustomerController.CUSTOMERS+savedCustomer.getId());
        return returnDTO;
    }


}
