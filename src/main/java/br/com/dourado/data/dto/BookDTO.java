package br.com.dourado.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO
{
  private long id;
  private String author;
  private String title;
  private LocalDate launchDate;
  private double price;
}
