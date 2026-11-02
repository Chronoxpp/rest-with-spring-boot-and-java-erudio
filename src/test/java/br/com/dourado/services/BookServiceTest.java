package br.com.dourado.services;

import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.exception.ResourceNotFoundException;
import br.com.dourado.model.Book;
import br.com.dourado.repository.BookRepositoryInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest
{
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

    //When
    List<BookDTO> actualBooks = service.findAll();

    //Then
    assertThat(actualBooks)
      .extracting(
        BookDTO::getId,
        BookDTO::getAuthor,
        BookDTO::getTitle,
        BookDTO::getLaunchDate,
        BookDTO::getPrice
      ).containsExactlyInAnyOrder(
        tuple(1L, "Fulano", "As aventuras de...",
          LocalDate.of(2026, 1, 15), 50D),
        tuple(2L, "Beltrano", "O terror de...",
          LocalDate.of(2026, 2, 15), 100D),
        tuple(3L, "Ciclano", "Vivendo sob uma...",
          LocalDate.of(2026, 3, 15), 150.15D)
      );
  }

  private List<Book> criarListaLivros()
  {
    return List.of(
      new Book(
        1L,
        "Fulano",
        "As aventuras de...",
        LocalDate.of(2026, 1, 15),
        50D
      ),
      new Book(
        2L,
        "Beltrano",
        "O terror de...",
        LocalDate.of(2026, 2, 15),
        100D
      ),
      new Book(
        3L,
        "Ciclano",
        "Vivendo sob uma...",
        LocalDate.of(2026, 3, 15),
        150.15D
      )
    );
  }

  @Test
  @DisplayName("Deve retornar uma lista vazia quando não existirem livros")
  void deveRetornarListaVaziaQuandoNaoExistiremLivros()
  {
    // Given
    when(bookRepository.findAll()).thenReturn(List.of());

    // When
    List<BookDTO> actualBooks = service.findAll();

    // Then
    assertThat(actualBooks).isEmpty();
  }

  @Test
  @DisplayName("Deve retornar BookDTO quando livro existir")
  void deveRetornarLivroDTOQuandoExistirLivro()
  {
    //Given
    Book book = new Book(
      1L,
      "Fulano",
      "As aventuras de...",
      LocalDate.of(2026, 1, 15),
      75.25D
    );

    when(bookRepository.findById(1L)).thenReturn(book);

    //When
    BookDTO actualBook = service.findById(1L);

    //Then
    assertThat(actualBook)
      .isNotNull()
      .satisfies(
        dto ->
        {
          assertThat(dto.getId()).isEqualTo(1L);
          assertThat(dto.getAuthor()).isEqualTo("Fulano");
          assertThat(dto.getTitle()).isEqualTo("As aventuras de...");
          assertThat(dto.getLaunchDate()).isEqualTo(LocalDate.of(2026, 1, 15));
          assertThat(dto.getPrice()).isEqualTo(75.25D);
        }
      );
  }

  @Test
  @DisplayName("Deve retornar exceção de livro não encontrado quando livro não existir")
  void deveRetornarExcecaoLivroNaoEncontradoQuandoLivroNaoExistir()
  {
    //Given
    long idInexistente = 999L;
    when(bookRepository.findById(idInexistente)).thenReturn(null);

    //When / Then
    assertThatThrownBy(() -> service.findById(idInexistente))
      .isInstanceOf(ResourceNotFoundException.class)
      .hasMessage("Book not found.");
  }

  @Test
  @DisplayName("Deve retornar livro cadastrado quando cadastro ocorrer com sucesso")
  void deveRetornarLivroCadastradoQuandoCadastroOcorrerComSucesso()
  {
    //Given
    BookDTO expectedBook = new BookDTO(
      1L,
      "Fulano",
      "As aventuras de...",
      LocalDate.of(2026, 1, 15),
      75.25D
    );

    Book bookPersisted = new Book(
      1L,
      "Fulano",
      "As aventuras de...",
      LocalDate.of(2026, 1, 15),
      75.25D
    );

    when(bookRepository.create(any(Book.class))).thenReturn(bookPersisted);

    //When
    BookDTO actualBook = service.create(new BookDTO());

    //Then
    assertThat(actualBook)
      .usingRecursiveComparison()
      .isEqualTo(expectedBook);
  }

  @Test
  @DisplayName("Deve retornar livro atualizado quando atualizacao ocorrer com sucesso")
  void deveRetornarLivroAtualizadoQuandoAtualizacaoOcorrerComSucesso()
  {
    //Given
    BookDTO expectedBook = new BookDTO(
      1L,
      "Fulano",
      "As aventuras de...",
      LocalDate.of(2026, 1, 15),
      75.25D
    );

    Book bookPersisted = new Book(
      1L,
      "Fulano",
      "As aventuras de...",
      LocalDate.of(2026, 1, 15),
      75.25D
    );

    when(bookRepository.update(any(Book.class))).thenReturn(bookPersisted);

    //When
    BookDTO actualBook = service.update(new BookDTO());

    //Then
    assertThat(actualBook)
      .usingRecursiveComparison()
      .isEqualTo(expectedBook);
  }
}
