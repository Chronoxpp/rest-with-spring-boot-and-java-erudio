package br.com.dourado.services;

import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.repository.BookRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.dourado.mapper.ObjectMapper.parseListObjects;

@Service
public class BookService
{
  private final BookRepositoryInterface repository;

  public BookService(BookRepositoryInterface repository)
  {
    this.repository = repository;
  }

  public List<BookDTO> findAll()
  {
    return parseListObjects(repository.findAll(), BookDTO.class);
  }
}
