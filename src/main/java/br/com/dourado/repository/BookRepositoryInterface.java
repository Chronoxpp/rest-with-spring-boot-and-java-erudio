package br.com.dourado.repository;


import br.com.dourado.model.Book;

import java.util.List;

public interface BookRepositoryInterface
{
  public List<Book> findAll();
}
