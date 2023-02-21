package guru.springfamework.api.v1.model;

import lombok.Data;



/**
 * A DTO for the {@link guru.springfamework.domain.Customer} entity
 */
@Data
public class CustomerDTO {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private String customerUrl;
}