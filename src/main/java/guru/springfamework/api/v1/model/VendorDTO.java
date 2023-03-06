
package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class VendorDTO {
// private Long id;
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;

}