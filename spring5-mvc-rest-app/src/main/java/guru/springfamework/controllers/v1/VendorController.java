package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.api.v1.model.VendorListDto;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api("Vendor controller.")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL  = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get all vendors.", notes = "All vendors returned without id property!")
    @GetMapping
    public VendorListDto getAllVendorsDto(){
        return new VendorListDto(vendorService.getAllVendorsDto());
    }

    @ApiOperation(value = "Create new vendor.", notes = "Vendor must be ...")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createVendor(@RequestBody VendorDto vendorDto){
        return vendorService.createVendor(vendorDto);
    }

    @ApiOperation(value = "Delete vendor by id", notes = "Id should be present")
    @DeleteMapping("/{id}")
    public void deleteVendorById(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }

    @ApiOperation(value = "Get vendor by id", notes = "Error when vendor with id not found")
    @GetMapping("/{id}")
    public VendorDto getVendorDtoById(@PathVariable Long id){
        return vendorService.getVendorDtoById(id);
    }

    @ApiOperation(value = "Update vendor", notes = "New vendor must be posted")
    @PutMapping("/{id}")
    public VendorDto updateVendorDtoById(@PathVariable Long id, @RequestBody VendorDto vendorDto){
        return vendorService.updateVendorByIdWithDto(id, vendorDto);
    }
}
