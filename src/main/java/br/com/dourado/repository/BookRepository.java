package br.com.dourado.repository;

import br.com.dourado.model.Book;
import org.springframework.stereotype.Repository;

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
    return new Book();
  }

  @Override
  public Book create(Book book)
  {
    return book;
  }

  @Override
  public Book update(Book book)
  {
    return book;
  }
}
