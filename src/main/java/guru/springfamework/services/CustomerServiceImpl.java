package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * Return all customers from DB
     * */
    @Override
    public List<CustomerDto> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl("/customers/" + customer.getId());
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {

        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {

        Customer customer = customerMapper.customerDtoToCustomer(customerDto);

        return saveCustomerAndReturnDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {

        Customer customerToSave = customerMapper.customerDtoToCustomer(customerDto);
        customerToSave.setId(id);

        //hibernate will first select (then update) customer because customer with this id is already in DB
        return saveCustomerAndReturnDto(customerToSave);
    }

    private CustomerDto saveCustomerAndReturnDto(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDto savedCustomerDto = customerMapper.customerToCustomerDto(savedCustomer);
        savedCustomerDto.setCustomerUrl("/customers/" + savedCustomer.getId());
        return savedCustomerDto;
    }
}
