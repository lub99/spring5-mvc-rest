
package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDto {

    @ApiModelProperty(value = "This is firstname.", required = true)
    private String firstname;
    private String lastname;
    private String customerUrl;
}
