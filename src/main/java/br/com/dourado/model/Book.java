package br.com.dourado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "books")
public class Book implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 160)
  private String author;

  @Column(nullable = false)
  private LocalDate launchDate;

  @Column(nullable = false)
  private double price;

  @Column(nullable = false, length = 240)
  private String title;
}
