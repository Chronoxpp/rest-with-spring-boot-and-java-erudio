package br.com.dourado.controllers;

import br.com.dourado.services.PersonServices;
import br.com.dourado.model.Person;
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
    public List<Person> findAll()
    {
        return service.findAll();
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") long id)
    {
        return service.findById(id);
    }

    @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person)
    {
        return service.create(person);
    }

    @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person)
    {
        return service.update(person);
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
