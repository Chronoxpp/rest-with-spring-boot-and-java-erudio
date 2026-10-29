package br.com.dourado.controllers;

import br.com.dourado.data.dto.PersonDTO;
import br.com.dourado.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
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
