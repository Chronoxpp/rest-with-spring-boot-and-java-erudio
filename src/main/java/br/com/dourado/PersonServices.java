package br.com.dourado;

import br.com.dourado.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices
{
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll()
    {
        List<Person> persons = new ArrayList<Person>();

        for(long i = 0; i < 8; i += 1)
        {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    private Person mockPerson(long i)
    {
        Person person = new Person();
        person.setId(i);
        person.setFirstName("Lucas " + i);
        person.setLastName("Dourado " + i);
        person.setAddress("Rondonopolis - MT - Brasil");
        person.setGender("Male");

        return person;
    }

    public Person findById(String id)
    {
        logger.info("Finding Person with id: " + id);

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Lucas");
        person.setLastName("Dourado");
        person.setAddress("Rondonopolis - MT - Brasil");
        person.setGender("Male");

        return person;
    }
}
