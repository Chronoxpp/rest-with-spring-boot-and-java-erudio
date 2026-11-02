package br.com.dourado.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1L;

  private long id;
  private String author;
  private LocalDate launchDate;
  private double price;
  private String title;
}
