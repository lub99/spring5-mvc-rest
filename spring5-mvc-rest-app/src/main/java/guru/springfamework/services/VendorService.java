package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDto;

import java.util.List;

public interface VendorService {

    List<VendorDto> getAllVendorsDto();

    VendorDto getVendorDtoById(Long id);

    VendorDto createVendor(VendorDto vendorDto);

    void deleteVendorById(Long id);

    VendorDto updateVendorByIdWithDto(Long id, VendorDto vendorDto);
}
