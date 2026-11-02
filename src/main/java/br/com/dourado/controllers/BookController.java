package br.com.dourado.controllers;

import br.com.dourado.controllers.docs.BookControllerDocs;
import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.services.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class BookController implements BookControllerDocs
{
  @Autowired
  private BookService service;

  //Find all Persons
  @GetMapping(produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE
  })
  @Override
  public List<BookDTO> findAll()
  {
    return service.findAll();
  }

  //Find a Book by id
  @GetMapping(value = "/{id}",
    produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    }
  )
  @Override
  public BookDTO findById(@PathVariable("id") Long id)
  {
    return service.findById(id);
  }

  //Create a Book
  @PostMapping(
    consumes = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    },
    produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    }
  )
  @Override
  public BookDTO create(@RequestBody BookDTO book)
  {
    return service.create(book);
  }

  //Update a Book
  @PutMapping(
    consumes = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    },
    produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
    }
  )
  @Override
  public BookDTO update(@RequestBody BookDTO book)
  {
    return service.update(book);
  }

  //Delete a Book
  @DeleteMapping(value = "/{id}")
  @Override
  public ResponseEntity<?> delete(@PathVariable("id") Long id)
  {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
