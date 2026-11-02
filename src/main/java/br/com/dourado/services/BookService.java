package br.com.dourado.services;

import br.com.dourado.controllers.BookController;
import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.exception.RequiredObjectIsNullException;
import br.com.dourado.exception.ResourceNotFoundException;
import br.com.dourado.model.Book;
import br.com.dourado.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.dourado.mapper.ObjectMapper.parseListObjects;
import static br.com.dourado.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService
{
  private final Logger logger = LoggerFactory.getLogger(BookService.class.getName());

  @Autowired
  private BookRepository repository;

  public BookService(final BookRepository repository)
  {
    this.repository = repository;
  }

  public List<BookDTO> findAll()
  {
    logger.info("Finding all books");

    var dtos = parseListObjects(repository.findAll(), BookDTO.class);

    dtos.forEach(this::addHateoasLinks);

    return dtos;
  }

  public BookDTO findById(long id)
  {
    logger.info("Finding Book with id: " + id);

    var entity = repository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Book not found."));

    var dto = parseObject(entity, BookDTO.class);

    addHateoasLinks(dto);

    return dto;
  }

  private void addHateoasLinks(BookDTO dto)
  {
    dto.add(
      linkTo(
        methodOn(BookController.class).findById(dto.getId())
      ).withSelfRel().withType("GET"));

    dto.add(
      linkTo(
        methodOn(BookController.class).findAll()
      ).withRel("findAll").withType("GET"));

    dto.add(
      linkTo(
        methodOn(BookController.class).create(dto)
      ).withRel("create").withType("POST"));

    dto.add(
      linkTo(
        methodOn(BookController.class).update(dto)
      ).withRel("update").withType("PUT"));

    dto.add(
      linkTo(
        methodOn(BookController.class).delete(dto.getId())
      ).withRel("delete").withType("DELETE"));
  }

  public BookDTO create(BookDTO bookDTO)
  {
    if (bookDTO == null)
      throw new RequiredObjectIsNullException();

    logger.info("Create one book");

    var newBook = parseObject(bookDTO, Book.class);

    var dto = parseObject(repository.save(newBook), BookDTO.class);

    addHateoasLinks(dto);

    return dto;
  }

  public BookDTO update(BookDTO bookDto)
  {
    if (bookDto == null)
      throw new RequiredObjectIsNullException();

    logger.info("Update one book");

    Book bookStored = repository
      .findById(bookDto.getId())
      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    bookStored.setId(bookDto.getId());
    bookStored.setAuthor(bookDto.getAuthor());
    bookStored.setTitle(bookDto.getTitle());
    bookStored.setLaunchDate(bookDto.getLaunchDate());
    bookStored.setPrice(bookDto.getPrice());

    var dto = parseObject(repository.save(bookStored), BookDTO.class);

    addHateoasLinks(dto);

    return dto;
  }

  public void delete(long id)
  {
    logger.info("Delete one book");

    Book bookStored = repository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    repository.delete(bookStored);
  }
}
