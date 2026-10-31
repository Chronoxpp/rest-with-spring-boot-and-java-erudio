package br.com.dourado.controllers;

import br.com.dourado.controllers.docs.PersonControllerDocs;
import br.com.dourado.data.dto.PersonDTO;
import br.com.dourado.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController implements PersonControllerDocs
{
  @Autowired
  private PersonServices service;

  //Find all Persons
  @GetMapping(produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE
  })
  @Override
  public List<PersonDTO> findAll()
  {
    return service.findAll();
  }

  //Find a Person by id
  @GetMapping(value = "/{id}",
    produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    }
  )
  @Override
  public PersonDTO findById(@PathVariable("id") Long id)
  {
    return service.findById(id);
  }

  //Create a Person
  @PostMapping(
    consumes = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    },
    produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    }
  )
  @Override
  public PersonDTO create(@RequestBody PersonDTO person)
  {
    return service.create(person);
  }

  //Update a Person
  @PutMapping(
    consumes = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    },
    produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    }
  )
  @Override
  public PersonDTO update(@RequestBody PersonDTO person)
  {
    return service.update(person);
  }

  //Delete a Person
  @DeleteMapping(value = "/{id}")
  @Override
  public ResponseEntity<?> delete(@PathVariable("id") Long id)
  {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
