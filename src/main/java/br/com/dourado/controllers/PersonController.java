package br.com.dourado.controllers;

import br.com.dourado.data.dto.PersonDTO;
import br.com.dourado.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController
{
  @Autowired
  private PersonServices service;

  @GetMapping(produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE
  })
  @Operation(
    summary = "Find All Persons",
    description = "Find All Persons",
    tags = {"Person"},
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200",
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(
              schema = @Schema(implementation = PersonDTO.class)
            )
          )
        }
      ),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    }
  )
  public List<PersonDTO> findAll()
  {
    return service.findAll();
  }

  @GetMapping(value = "/{id}",
    produces = {
    MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_YAML_VALUE
  }
  )
  @Operation(
    summary = "Find a Person",
    description = "Find a specific Person by your id",
    tags = {"Person"},
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = PersonDTO.class))
      ),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    }
  )
  public PersonDTO findById(@PathVariable("id") Long id)
  {
    return service.findById(id);
  }

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
  @Operation(
    summary = "Create a Person",
    description = "Create a Person",
    tags = {"Person"},
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = PersonDTO.class))
      ),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    }
  )
  public PersonDTO create(@RequestBody PersonDTO person)
  {
    return service.create(person);
  }

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
  @Operation(
    summary = "Update a Person",
    description = "Update a Person",
    tags = {"Person"},
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = PersonDTO.class))
      ),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    }
  )
  public PersonDTO update(@RequestBody PersonDTO person)
  {
    return service.update(person);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id)
  {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
