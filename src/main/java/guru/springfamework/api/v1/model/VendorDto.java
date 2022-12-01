package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto {

    @ApiModelProperty(value = "Vendor company name", required = true)
    private String name;

    @ApiModelProperty(value = "Vendor url for details about vendor.")
    private String vendorUrl;
}
