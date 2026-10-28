package br.com.dourado.mapper.custom;

import br.com.dourado.data.dto.v2.PersonDTOV2;
import br.com.dourado.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper
{
  public PersonDTOV2 toDTO(Person person)
  {
    PersonDTOV2 dto = new PersonDTOV2();

    dto.setId(person.getId());
    dto.setFirstName(person.getFirstName());
    dto.setLastName(person.getLastName());
    dto.setBirthDay(new Date());
    dto.setAddress(person.getAddress());
    dto.setGender(person.getGender());

    return dto;
  }

  public Person toEntity(PersonDTOV2 personDto)
  {
    Person person = new Person();

    person.setId(personDto.getId());
    person.setFirstName(personDto.getFirstName());
    person.setLastName(personDto.getLastName());
    person.setAddress(personDto.getAddress());
    person.setGender(personDto.getGender());

    return person;
  }
}
