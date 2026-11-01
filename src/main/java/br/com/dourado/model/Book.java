package br.com.dourado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book
{
  private long id;
  private String author;
  private String title;
  private LocalDate launchDate;
  private double price;
}
