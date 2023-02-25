package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * A DTO for the {@link guru.springfamework.domain.Customer} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CustomerDTO {
    private Long id;
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url") //todo что за аннотация?
    private String customerUrl;
}