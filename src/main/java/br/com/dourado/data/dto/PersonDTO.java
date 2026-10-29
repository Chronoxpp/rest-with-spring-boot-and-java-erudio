package br.com.dourado.data.dto;

import br.com.dourado.serializer.GenderSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

//@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
@NoArgsConstructor
@Data
public class PersonDTO implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    //@JsonProperty("first_name")
    private String firstName;
    //@JsonProperty("last_name")
    private String lastName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;
    private String address;
    //@JsonIgnore
    @JsonSerialize(using = GenderSerializer.class)
    private String gender;
}
