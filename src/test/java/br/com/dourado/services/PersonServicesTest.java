package br.com.dourado.services;

import br.com.dourado.data.dto.PersonDTO;
import br.com.dourado.exception.RequiredObjectIsNullException;
import br.com.dourado.model.Person;
import br.com.dourado.repository.PersonRepository;
import br.com.dourado.unit_tests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest
{
  private MockPerson input;

  @Mock
  private PersonRepository repository;

  @InjectMocks
  private PersonServices service;

  private AutoCloseable closeable;

  @BeforeEach
  void setUp()
  {
    input = new MockPerson();
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception
  {
    closeable.close();
  }

  @Test
  void findById()
  {
    Person person = input.mockEntity(1);
    person.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(person));

    var result = service.findById(1L);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertNotNull(result.getLinks());

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/person/v1/1")
          && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/person/v1/1")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Address Test1",
      result.getAddress()
    );

    assertEquals(
      "First Name Test1",
      result.getFirstName()
    );

    assertEquals(
      "Last Name Test1",
      result.getLastName()
    );

    assertEquals(
      "Female",
      result.getGender()
    );
  }

  @Test
  void create()
  {
    Person person = input.mockEntity(1);
    Person persisted = person;
    persisted.setId(1L);

    PersonDTO dto = input.mockDTO(1);

    when(repository.save(person)).thenReturn(persisted);

    var result = service.create(dto);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertNotNull(result.getLinks());

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/person/v1/1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/person/v1/1")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Address Test1",
      result.getAddress()
    );

    assertEquals(
      "First Name Test1",
      result.getFirstName()
    );

    assertEquals(
      "Last Name Test1",
      result.getLastName()
    );

    assertEquals(
      "Female",
      result.getGender()
    );
  }

  @Test
  void testCreateWithNullPerson()
  {
    Exception exception = assertThrows(
      RequiredObjectIsNullException.class,
      () -> service.create(null)
    );

    String expectedMessage = "It is not allowed to persist a null object";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void update()
  {
    Person person = input.mockEntity(1);
    Person persisted = person;
    persisted.setId(1L);

    PersonDTO dto = input.mockDTO(1);

    when(repository.findById(1L)).thenReturn(Optional.of(persisted));

    when(repository.save(person)).thenReturn(persisted);

    var result = service.update(dto);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertNotNull(result.getLinks());

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/person/v1/1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/person/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/person/v1/1")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Address Test1",
      result.getAddress()
    );

    assertEquals(
      "First Name Test1",
      result.getFirstName()
    );

    assertEquals(
      "Last Name Test1",
      result.getLastName()
    );

    assertEquals(
      "Female",
      result.getGender()
    );
  }

  @Test
  void testUpdateWithNullPerson()
  {
    Exception exception = assertThrows(
      RequiredObjectIsNullException.class,
      () -> service.update(null)
    );

    String expectedMessage = "It is not allowed to persist a null object";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void delete()
  {
    Person person = input.mockEntity(1);
    person.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(person));

    assertDoesNotThrow(() -> service.delete(1L));

    verify(repository, times(1)).findById(anyLong());
    verify(repository, times(1)).delete(person);
    verifyNoMoreInteractions(repository);
  }

  @Test
  void findAll()
  {
    List<Person> list = input.mockEntityList();
    when(repository.findAll()).thenReturn(list);

    List<PersonDTO> persons = service.findAll();

    assertNotNull(persons);
    assertEquals(14, persons.size());
  }
}
