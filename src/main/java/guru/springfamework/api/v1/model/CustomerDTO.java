package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 9/27/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Привет это описание")
public class CustomerDTO {

@ApiModelProperty(value = "Это первое имя ", required = true)
private String firstname;
    private String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
