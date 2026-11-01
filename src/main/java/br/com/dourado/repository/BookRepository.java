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
}
