package br.com.dourado.services;

import br.com.dourado.controllers.PersonController;
import br.com.dourado.data.dto.PersonDTO;
import br.com.dourado.exception.ResourceNotFoundException;
import br.com.dourado.model.Person;
import br.com.dourado.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.dourado.mapper.ObjectMapper.parseListObjects;
import static br.com.dourado.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices
{
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonServices(final PersonRepository repository)
    {
        this.repository = repository;
    }

    public List<PersonDTO> findAll()
    {
        logger.info("Finding all persons");

        var dtos = parseListObjects(repository.findAll(), PersonDTO.class);

        dtos.forEach(this::addHateoasLinks);

        return dtos;
    }

    public PersonDTO findById(long id)
    {
        logger.info("Finding Person with id: " + id);

        var entity = repository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var dto = parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    private void addHateoasLinks(PersonDTO dto)
    {
        dto.add(
          linkTo(
            methodOn(PersonController.class).findById(dto.getId())
          ).withSelfRel().withType("GET"));

        dto.add(
          linkTo(
            methodOn(PersonController.class).findAll()
          ).withRel("findAll").withType("GET"));

        dto.add(
          linkTo(
            methodOn(PersonController.class).create(dto)
          ).withRel("create").withType("POST"));

        dto.add(
          linkTo(
            methodOn(PersonController.class).update(dto)
          ).withRel("update").withType("PUT"));

        dto.add(
          linkTo(
            methodOn(PersonController.class).delete(dto.getId())
          ).withRel("delete").withType("DELETE"));
    }

    public PersonDTO create(PersonDTO personDto)
    {
        logger.info("Create one person");

        var newPerson = parseObject(personDto, Person.class);

        var dto = parseObject(repository.save(newPerson), PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public PersonDTO update(PersonDTO personDto)
    {
        logger.info("Update one person");

        Person personStored = repository
          .findById(personDto.getId())
          .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personStored.setId(personDto.getId());
        personStored.setFirstName(personDto.getFirstName());
        personStored.setLastName(personDto.getLastName());
        personStored.setAddress(personDto.getAddress());
        personStored.setGender(personDto.getGender());

        var dto = parseObject(repository.save(personStored), PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public void delete(long id)
    {
        logger.info("Delete one person");

        Person personStored = repository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(personStored);
    }
}
