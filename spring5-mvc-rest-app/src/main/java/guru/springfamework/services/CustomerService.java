package guru.springfamework.services;



import guru.springframework.model.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createNewCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomerById(Long id);
}
