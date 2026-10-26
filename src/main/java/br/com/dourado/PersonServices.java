package br.com.dourado;

import br.com.dourado.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices
{
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

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
