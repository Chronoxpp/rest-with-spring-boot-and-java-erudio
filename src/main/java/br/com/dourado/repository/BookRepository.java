package br.com.dourado.repository;

import br.com.dourado.model.Book;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookRepository implements BookRepositoryInterface
{
  @Override
  public List<Book> findAll()
  {
    return List.of();
  }

  @Override
  public Book findById(long id)
  {
    Book book = new Book();
    book.setId(1);
    book.setAuthor("Fulano");
    book.setLaunchDate(LocalDate.of(2026, 1, 15));
    book.setPrice(75.25D);
    book.setTitle("Vivendo sob uma...");

    return book;
  }
}
