package br.com.dourado.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
@NoArgsConstructor
@Data
public class PersonDTO implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String address;
    @JsonIgnore
    private String gender;
}
