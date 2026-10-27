package br.com.dourado.services;

import br.com.dourado.exception.ResourceNotFoundException;
import br.com.dourado.model.Person;
import br.com.dourado.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

    public List<Person> findAll()
    {
        logger.info("Finding all persons");

        return repository.findAll();
    }

    public Person findById(long id)
    {
        logger.info("Finding Person with id: " + id);

        return repository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person)
    {
        logger.info("Create one person");

        return repository.save(person);
    }

    public Person update(Person person)
    {
        logger.info("Update one person");

        Person personStored = repository
          .findById(person.getId())
          .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personStored.setId(person.getId());
        personStored.setFirstName(person.getFirstName());
        personStored.setLastName(person.getLastName());
        personStored.setAddress(person.getAddress());
        personStored.setGender(person.getGender());

        return repository.save(person);
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
