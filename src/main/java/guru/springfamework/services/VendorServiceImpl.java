package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.exceptions.ResourceNotFoundException;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDto> getAllVendorsDto() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
                    vendorDto.setProductUrl(VendorController.BASE_URL + "/" + vendor.getId());
                    return vendorDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDto getVendorDtoById(Long id) {

        return vendorRepository
                .findById(id)
                .map(vendor -> {
                    VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
                    vendorDto.setProductUrl(VendorController.BASE_URL + "/" + vendor.getId());
                    return vendorDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDto createVendor(VendorDto vendorDto) {

        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);

        return saveVendorAndReturnDto(vendor);
    }

    @Override
    public VendorDto updateVendorByIdWithDto(Long id, VendorDto vendorDto) {

        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);
        vendor.setId(id);

        return saveVendorAndReturnDto(vendor);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDto saveVendorAndReturnDto(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDto savedVendorDto = vendorMapper.vendorToVendorDto(savedVendor);
        savedVendorDto.setProductUrl(VendorController.BASE_URL + "/" + vendor.getId());

        return savedVendorDto;
    }
}
