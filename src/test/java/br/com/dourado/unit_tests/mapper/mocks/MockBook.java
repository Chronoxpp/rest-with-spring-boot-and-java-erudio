package br.com.dourado.unit_tests.mapper.mocks;

import br.com.dourado.data.dto.BookDTO;
import br.com.dourado.model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockBook
{
    public Book mockEntity()
    {
        return mockEntity(0);
    }

    public Book mockEntity(Integer number)
    {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Author test" + number);
        book.setPrice(2 * number);
        book.setTitle("Title test" + number);
        book.setLaunchDate(LocalDate.of(2026, 3, 1).plusDays(number));

        return book;
    }

    public List<Book> mockEntityList()
    {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++)
        {
            books.add(mockEntity(i));
        }

        return books;
    }

    public BookDTO mockDTO()
    {
        return mockDTO(0);
    }

    public BookDTO mockDTO(Integer number)
    {
        BookDTO book = new BookDTO();
        book.setId(number.longValue());
        book.setAuthor("Author test" + number);
        book.setPrice(2 * number);
        book.setTitle("Title test" + number);
        book.setLaunchDate(LocalDate.of(2026, 3, 1).plusDays(number));

        return book;
    }

    public List<BookDTO> mockDTOList()
    {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++)
        {
            books.add(mockDTO(i));
        }

        return books;
    }
}