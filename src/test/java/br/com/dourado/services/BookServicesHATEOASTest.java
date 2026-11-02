package br.com.dourado.services;

import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.exception.RequiredObjectIsNullException;
import br.com.dourado.model.Book;
import br.com.dourado.repository.BookRepository;
import br.com.dourado.unit_tests.mapper.mocks.MockBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesHATEOASTest
{
  private MockBook input;

  @Mock
  private BookRepository repository;

  @InjectMocks
  private BookService service;

  private AutoCloseable closeable;

  @BeforeEach
  void setUp()
  {
    input = new MockBook();
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
    Book book = input.mockEntity(1);
    book.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(book));

    var result = service.findById(1L);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertNotNull(result.getLinks());

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/book/v1/1")
          && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/book/v1/1")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Author test1",
      result.getAuthor()
    );

    assertEquals(
      2,
      result.getPrice()
    );

    assertEquals(
      "Title test1",
      result.getTitle()
    );

    assertEquals(
      LocalDate.of(2026, 3, 2),
      result.getLaunchDate()
    );
  }

  @Test
  void create()
  {
    Book book = input.mockEntity(1);
    Book persisted = book;
    persisted.setId(1L);

    BookDTO dto = input.mockDTO(1);

    when(repository.save(book)).thenReturn(persisted);

    var result = service.create(dto);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertNotNull(result.getLinks());

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/book/v1/1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/book/v1/1")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Author test1",
      result.getAuthor()
    );

    assertEquals(
      2,
      result.getPrice()
    );

    assertEquals(
      "Title test1",
      result.getTitle()
    );

    assertEquals(
      LocalDate.of(2026, 3, 2),
      result.getLaunchDate()
    );
  }

  @Test
  void testCreateWithNullBook()
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
    Book book = input.mockEntity(1);
    Book persisted = book;
    persisted.setId(1L);

    BookDTO dto = input.mockDTO(1);

    when(repository.findById(1L)).thenReturn(Optional.of(persisted));

    when(repository.save(book)).thenReturn(persisted);

    var result = service.update(dto);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertNotNull(result.getLinks());

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/book/v1/1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      result.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/book/v1/1")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Author test1",
      result.getAuthor()
    );

    assertEquals(
      2,
      result.getPrice()
    );

    assertEquals(
      "Title test1",
      result.getTitle()
    );

    assertEquals(
      LocalDate.of(2026, 3, 2),
      result.getLaunchDate()
    );
  }

  @Test
  void testUpdateWithNullBook()
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
    Book book = input.mockEntity(1);
    book.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(book));

    assertDoesNotThrow(() -> service.delete(1L));

    verify(repository, times(1)).findById(anyLong());
    verify(repository, times(1)).delete(book);
    verifyNoMoreInteractions(repository);
  }

  @Test
  void findAll()
  {
    List<Book> list = input.mockEntityList();
    when(repository.findAll()).thenReturn(list);

    List<BookDTO> books = service.findAll();

    assertNotNull(books);
    assertEquals(14, books.size());

    //

    var bookOne = books.get(1);

    assertNotNull(bookOne);
    assertNotNull(bookOne.getId());
    assertNotNull(bookOne.getLinks());

    assertNotNull(
      bookOne.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/book/v1/1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      bookOne.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      bookOne.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      bookOne.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      bookOne.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/book/v1/1")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Author test1",
      bookOne.getAuthor()
    );

    assertEquals(
      2,
      bookOne.getPrice()
    );

    assertEquals(
      "Title test1",
      bookOne.getTitle()
    );

    assertEquals(
      LocalDate.of(2026, 3, 2),
      bookOne.getLaunchDate()
    );

    //

    var bookFour = books.get(4);

    assertNotNull(bookFour);
    assertNotNull(bookFour.getId());
    assertNotNull(bookFour.getLinks());

    assertNotNull(
      bookFour.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/api/book/v1/4")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      bookFour.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("GET")
        )
    );

    assertNotNull(
      bookFour.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("POST")
        )
    );

    assertNotNull(
      bookFour.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("/api/book/v1")
            && link.getType().equals("PUT")
        )
    );

    assertNotNull(
      bookFour.getLinks()
        .stream()
        .anyMatch(
          link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("/api/book/v1/4")
            && link.getType().equals("DELETE")
        )
    );

    assertEquals(
      "Author test4",
      bookFour.getAuthor()
    );

    assertEquals(
      8,
      bookFour.getPrice()
    );

    assertEquals(
      "Title test4",
      bookFour.getTitle()
    );

    assertEquals(
      LocalDate.of(2026, 3, 5),
      bookFour.getLaunchDate()
    );
  }
}
