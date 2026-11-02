package br.com.dourado.services;

import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.exception.ResourceNotFoundException;
import br.com.dourado.model.Book;
import br.com.dourado.repository.BookRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.dourado.mapper.ObjectMapper.parseListObjects;
import static br.com.dourado.mapper.ObjectMapper.parseObject;

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

  public BookDTO findById(long id)
  {
    Book book =  repository.findById(id);

    if (book == null)
      throw new ResourceNotFoundException("Book not found.");

    return parseObject(repository.findById(id), BookDTO.class);
  }

  public BookDTO create(BookDTO bookDTO)
  {
    Book newBook = parseObject(bookDTO, Book.class);

    Book persistedBook = repository.create(newBook);

    return parseObject(persistedBook, BookDTO.class);
  }
}
