package br.com.dourado.services;

import br.com.dourado.data.dto.PersonDTO;
import br.com.dourado.exception.ResourceNotFoundException;
import br.com.dourado.model.Person;
import br.com.dourado.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.dourado.mapper.ObjectMapper.parseListObjects;
import static br.com.dourado.mapper.ObjectMapper.parseObject;

;

@Service
public class PersonServices
{
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    private PersonRepository repository;

    public PersonServices(final PersonRepository repository)
    {
        this.repository = repository;
    }

    public List<PersonDTO> findAll()
    {
        logger.info("Finding all persons");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(long id)
    {
        logger.info("Finding Person with id: " + id);

        var entity = repository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO personDto)
    {
        logger.info("Create one person");

        var newPerson = parseObject(personDto, Person.class);

        return parseObject(repository.save(newPerson), PersonDTO.class);
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

        return parseObject(repository.save(personStored), PersonDTO.class);
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
