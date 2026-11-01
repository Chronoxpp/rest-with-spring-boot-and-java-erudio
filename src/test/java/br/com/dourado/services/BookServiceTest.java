package br.com.dourado.services;

import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.model.Book;
import br.com.dourado.repository.BookRepositoryInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest
{
  // findAll, findById, create, update, delete

  @Mock
  private BookRepositoryInterface bookRepository;

  @InjectMocks
  private BookService service;

  @Test
  @DisplayName("Deve retornar uma lista de livros DTO quando os livros existirem")
  void deveRetornarListaDeLivrosDTOQuandoOsLivrosExistirem()
  {
    //Given
    List<Book> books = criarListaLivros();
    when(bookRepository.findAll()).thenReturn(books);

    List<BookDTO> expectedBooks = criarListaLivrosDTO();

    //When
    List<BookDTO> actualBooks = service.findAll();

    //Then
    assertIterableEquals(
      expectedBooks,
      actualBooks,
      () -> "A lista de livros recebida não possui os mesmo itens da lista esperada OU os itens da lista recebida estão fora da ordem esperada."
    );
  }

  List<Book> criarListaLivros()
  {
    Book book1 = new Book();
    book1.setId(1);
    book1.setAuthor("Fulano");
    book1.setLaunchDate(LocalDate.of(2026, 1, 15));
    book1.setPrice(50D);
    book1.setTitle("As aventuras de...");

    Book book2 = new Book();
    book2.setId(2);
    book2.setAuthor("Beltrano");
    book2.setLaunchDate(LocalDate.of(2026, 2, 15));
    book2.setPrice(100D);
    book2.setTitle("O terror de...");

    Book book3 = new Book();
    book3.setId(3);
    book3.setAuthor("Ciclano");
    book3.setLaunchDate(LocalDate.of(2026, 3, 15));
    book3.setPrice(150.15D);
    book3.setTitle("Vivendo sob uma...");

    List<Book> expectedBooks = new ArrayList<>();
    expectedBooks.add(book1);
    expectedBooks.add(book2);
    expectedBooks.add(book3);

    return expectedBooks;
  }

  List<BookDTO> criarListaLivrosDTO()
  {
    BookDTO book1 = new BookDTO();
    book1.setId(1);
    book1.setAuthor("Fulano");
    book1.setLaunchDate(LocalDate.of(2026, 1, 15));
    book1.setPrice(50D);
    book1.setTitle("As aventuras de...");

    BookDTO book2 = new BookDTO();
    book2.setId(2);
    book2.setAuthor("Beltrano");
    book2.setLaunchDate(LocalDate.of(2026, 2, 15));
    book2.setPrice(100D);
    book2.setTitle("O terror de...");

    BookDTO book3 = new BookDTO();
    book3.setId(3);
    book3.setAuthor("Ciclano");
    book3.setLaunchDate(LocalDate.of(2026, 3, 15));
    book3.setPrice(150.15D);
    book3.setTitle("Vivendo sob uma...");

    List<BookDTO> expectedBooks = new ArrayList<>();
    expectedBooks.add(book1);
    expectedBooks.add(book2);
    expectedBooks.add(book3);

    return expectedBooks;
  }

  @Test
  @DisplayName("Deve retornar uma lista vazia quando não existirem livros")
  void deveRetornarListaVaziaQuandoNaoExistiremLivros()
  {
    // Given
    List<Book> books = new ArrayList<>();
    when(bookRepository.findAll()).thenReturn(books);

    List<BookDTO> expectedBooks = new ArrayList<>();

    // When
    List<BookDTO> actualBooks = service.findAll();

    // Then
    assertEquals(expectedBooks, actualBooks);
  }
}
