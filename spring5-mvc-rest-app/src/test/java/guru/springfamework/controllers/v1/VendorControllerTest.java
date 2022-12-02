package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.exceptions.ResourceNotFoundException;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {

    @MockBean
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc;

    VendorDto vendorDto1;
    VendorDto vendorDto2;

    @Before
    public void setUp() throws Exception {
        vendorDto1 = new VendorDto("Vendor 1", VendorController.BASE_URL + "/1");
        vendorDto2 = new VendorDto("Vendor 2", VendorController.BASE_URL + "/2");
    }

    @Test
    public void getAllVendorsDto() throws Exception {
        List<VendorDto> vendorDtos = Arrays.asList(vendorDto1, vendorDto2);

        given(vendorService.getAllVendorsDto()).willReturn(vendorDtos);


        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorDtoById() throws Exception {

        given(vendorService.getVendorDtoById(anyLong())).willReturn(vendorDto1);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor 1")));
    }

    @Test
    public void getVendorDtoByIdNotFound() throws Exception {

        given(vendorService.getVendorDtoById(anyLong())).willThrow(new ResourceNotFoundException());

        mockMvc.perform(get(VendorController.BASE_URL + "/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}