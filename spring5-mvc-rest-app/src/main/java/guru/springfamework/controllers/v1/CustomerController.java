package guru.springfamework.controllers.v1;




import guru.springfamework.services.CustomerService;
import guru.springframework.model.CustomerDto;
import guru.springframework.model.CustomerListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * No need for returning ResponseEntity<T> in RestController
 * because @ResponseBody is default for controller.
 * */
@Api("This is Customer controller")
@RestController
@RequestMapping( CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get all customers", notes = "Some notes of api.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getAllCustomers(){
        CustomerListDto customerListDto = new CustomerListDto();
        customerListDto.getCustomers().addAll(customerService.getAllCustomers());
        return customerListDto;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto savedCustomerDto = customerService.createNewCustomer(customerDto);
        return ResponseEntity.created(URI.create(savedCustomerDto.getCustomerUrl())).body(savedCustomerDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateExistingCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        CustomerDto savedCustomerDto = customerService.updateCustomer(id, customerDto);
        return savedCustomerDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
