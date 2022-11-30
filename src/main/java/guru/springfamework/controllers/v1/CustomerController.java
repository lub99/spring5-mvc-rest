package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import guru.springfamework.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping( "${api.version}/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/", ""})
    public ResponseEntity<CustomerListDto> getAllCustomers(){
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        return ResponseEntity.ok(new CustomerListDto(customerDtos));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto savedCustomerDto = customerService.createNewCustomer(customerDto);
        return ResponseEntity.created(URI.create(savedCustomerDto.getCustomerUrl())).body(savedCustomerDto);
    }
}
