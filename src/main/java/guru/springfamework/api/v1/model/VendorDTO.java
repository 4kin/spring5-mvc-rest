
package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link guru.springfamework.domain.Vendor} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "описание ДТО")
public class VendorDTO {
// private Long id;

    private String name;

    @JsonProperty("vendor_url")
    @ApiModelProperty(hidden = true)
    private String vendorUrl;

}