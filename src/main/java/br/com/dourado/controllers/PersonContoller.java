package br.com.dourado.controllers;

import br.com.dourado.data.dto.v1.PersonDTO;
import br.com.dourado.data.dto.v2.PersonDTOV2;
import br.com.dourado.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonContoller
{
    @Autowired
    private PersonServices service;

    @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> findAll()
    {
        return service.findAll();
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO findById(@PathVariable("id") long id)
    {
        return service.findById(id);
    }

    @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(@RequestBody PersonDTO personDto)
    {
        return service.create(personDto);
    }

    @PostMapping(
      value = "/v2",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTOV2 create(@RequestBody PersonDTOV2 personDto)
    {
        return service.createV2(personDto);
    }

    @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(@RequestBody PersonDTO personDto)
    {
        return service.update(personDto);
    }

    @DeleteMapping(
      value = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable("id") long id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
