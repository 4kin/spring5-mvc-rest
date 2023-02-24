package guru.springfamework.api.v1.model;

import lombok.*;

import java.io.Serializable;

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
    private String customerUrl;
}