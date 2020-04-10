package com.example.springboot.service;

import com.example.springboot.data.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class BookService
{
  Logger log = LoggerFactory.getLogger(this.getClass());

  public BookService()
  {
  }

  public Book getBook(String id)
  {
    String title        = "Stiff: The Curious Lives of Human Cadavers";
    String description  = "Stiff: The Curious Lives of Human Cadavers";
    Date datetime       = null;
    String genre        = "science";
    String author       = "Mary Roach";
    String link         = "http://e-bookmobile.com/books/Stiff";

    SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString         = "2020-04-03";

    try
    {
      datetime = formatter.parse(dateInString);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }

    return new Book(id
                    , title
                    , description
                    , datetime
                    , genre
                    , author
                    , link);
  }

  public String addBook(Book book)
  {
    return UUID.randomUUID().toString();
  }

  public boolean updateBook(String id, Book book)
  {
    return true;
  }

  public boolean deleteBook(String id)
  {
    return true;
  }
}
