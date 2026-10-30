package br.com.dourado.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
