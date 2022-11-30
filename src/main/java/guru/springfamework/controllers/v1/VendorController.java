package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.api.v1.model.VendorListDto;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL  = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public VendorListDto getAllVendorsDto(){
        return new VendorListDto(vendorService.getAllVendorsDto());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createVendor(@RequestBody VendorDto vendorDto){
        return vendorService.createVendor(vendorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteVendorById(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }

    @GetMapping("/{id}")
    public VendorDto getVendorDtoById(@PathVariable Long id){
        return vendorService.getVendorDtoById(id);
    }

    @PutMapping("/{id}")
    public VendorDto updateVendorDtoById(@PathVariable Long id, @RequestBody VendorDto vendorDto){
        return vendorService.updateVendorByIdWithDto(id, vendorDto);
    }
}
