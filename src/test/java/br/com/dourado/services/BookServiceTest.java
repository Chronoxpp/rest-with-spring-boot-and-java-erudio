package br.com.dourado.services;

import br.com.dourado.data.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest
{
  // findAll, findById, create, update, delete

  BookService service;

  @BeforeEach
  void setUp()
  {
    service = new BookService();
  }

  @Test
  @DisplayName("Deve retornar uma lista de livros DTO quando os livros existirem")
  void deveRetornarListaDeLivrosDTOQuandoOsLivrosExistirem()
  {
    //Given
    List<BookDTO> expectedBooks = criarListaLivrosDto();

    //When
    List<BookDTO> actualBooks = service.findAll();

    //Then
    assertIterableEquals(
      expectedBooks,
      actualBooks,
      () -> "A lista de livros recebida não possui os mesmo itens da lista esperada OU os itens da lista recebida estão fora da ordem esperada."
    );
  }

  List<BookDTO> criarListaLivrosDto()
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

    List<BookDTO> expectedBooks = new ArrayList<BookDTO>();
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
    List<BookDTO> expectedBooks = new ArrayList<>();

    // When
    List<BookDTO> actualBooks = service.findAll();

    // Then
    assertEquals(expectedBooks, actualBooks);
  }
}
