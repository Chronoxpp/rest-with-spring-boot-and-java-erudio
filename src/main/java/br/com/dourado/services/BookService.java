package br.com.dourado.services;

import br.com.dourado.data.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService
{
  public List<BookDTO> findAll()
  {
    return obterListaLivrosDto();
  }

  List<BookDTO> obterListaLivrosDto()
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

    List<BookDTO> books = new ArrayList<BookDTO>();
    books.add(book1);
    books.add(book2);
    books.add(book3);

    return books;
  }
}
