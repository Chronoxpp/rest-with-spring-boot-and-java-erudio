package br.com.dourado.data.dto;

import br.com.dourado.serializer.GenderSerializer;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

//@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
@NoArgsConstructor
@Data
@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    //@JsonProperty("first_name")
    private String firstName;
    //@JsonProperty("last_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;
    private String address;
    //@JsonIgnore
    @JsonSerialize(using = GenderSerializer.class)
    private String gender;
    private String sensitiveData;
}
