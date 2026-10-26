package br.com.dourado.controllers;

import br.com.dourado.PersonServices;
import br.com.dourado.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonContoller
{
    @Autowired
    private PersonServices service;

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") String id)
    {
        return service.findById(id);
    }
}
