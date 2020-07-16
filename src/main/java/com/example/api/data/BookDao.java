package com.example.api.data;

import com.example.api.data.model.Book;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Repository
public class BookDao implements DataAccessObject<Book>
{
  @Override
  public Book find(Map<String, Object> query)
  {
    String title        = "Stiff: The Curious Lives of Human Cadavers";
    String description  = "Stiff: The Curious Lives of Human Cadavers";
    Date publishDate    = null;
    String genre        = "science";
    String author       = "Mary Roach";
    String link         = "http://e-bookmobile.com/books/Stiff";

    SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString         = "2020-04-03";

    try
    {
      publishDate = formatter.parse(dateInString);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }

    return new Book("1"
                    , title
                    , description
                    , publishDate
                    , genre
                    , author
                    , link);
  }

  @Override
  public String save(Book book)
  {
    return UUID.randomUUID().toString();
  }

  @Override
  public boolean update(Map<String, Object> query, Book book)
  {
    return true;
  }

  @Override
  public boolean remove(Map<String, Object> query)
  {
    return true;
  }
}
