package com.example.springboot.service;

import com.example.springboot.common.AppLogger;
import com.example.springboot.common.JsonUtil;
import com.example.springboot.data.BookDao;
import com.example.springboot.data.model.Book;
import com.example.springboot.resource.model.BookInfo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookService
{
  AppLogger log = new AppLogger(this.getClass());

  private final BookDao bookDao;

  public BookService(BookDao bookDao)
  {
    this.bookDao = bookDao;
  }

  public BookInfo getBook(String id)
  {
    Map<String, String> logFieldMap = new LinkedHashMap<String, String>();
    logFieldMap.put("bookId", id);
    log.info("Retrieving book", "BookService.getBook.start", logFieldMap);

    Map<String, Object> query = new LinkedHashMap<>();
    query.put("id", id);

    Book book           = bookDao.find(query);
    String title        = book.getTitle();
    String description  = book.getDescription();
    Date publishDate    = book.getPublishDate();
    String genre        = book.getGenre();
    String author       = book.getAuthor();
    String link         = book.getLink();

    SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
    String publishDateStr       = formatter.format(publishDate);

    log.info(JsonUtil.toJson(book), "BookService.getBook.end", logFieldMap);

    return new BookInfo(id
                        , title
                        , description
                        , publishDateStr
                        , genre
                        , author
                        , link);
  }

  public String addBook(BookInfo bookInfo)
  {
    log.info(JsonUtil.toJson(bookInfo), "BookService.addBook.start");

    String title            = bookInfo.getTitle();
    String description      = bookInfo.getDescription();
    String publishDateStr   = bookInfo.getPublishDate();
    String genre            = bookInfo.getGenre();
    String author           = bookInfo.getAuthor();
    String link             = bookInfo.getLink();

    SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
    Date publishDate            = null;

    try
    {
      if (publishDateStr != null)
      {
        publishDate = formatter.parse(publishDateStr);
      }
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }

    Book book = new Book( null
                          , title
                          , description
                          , publishDate
                          , genre
                          , author
                          , link);

    String bookId = bookDao.save(book);

    Map<String, String> logFieldMap = new LinkedHashMap<String, String>();
    logFieldMap.put("generatedId", bookId);
    log.info(bookId != null ? "Book added" : "Book add failed", "BookService.addBook.end", logFieldMap);

    return bookId;
  }

  public boolean updateBook(String id, BookInfo bookInfo)
  {
    Map<String, String> logFieldMap = new LinkedHashMap<String, String>();
    logFieldMap.put("bookId", id);
    log.info(JsonUtil.toJson(bookInfo), "BookService.updateBook.start", logFieldMap);

    String title            = bookInfo.getTitle();
    String description      = bookInfo.getDescription();
    String publishDateStr   = bookInfo.getPublishDate();
    String genre            = bookInfo.getGenre();
    String author           = bookInfo.getAuthor();
    String link             = bookInfo.getLink();

    SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
    Date publishDate            = null;

    try
    {
      if (publishDateStr != null)
      {
        publishDate = formatter.parse(publishDateStr);
      }
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }

    Book book = new Book( null
                          , title
                          , description
                          , publishDate
                          , genre
                          , author
                          , link);

    Map<String, Object> query = new LinkedHashMap<>();
    query.put("id", id);

    boolean update = bookDao.update(query, book);

    logFieldMap.put("recordUpdated", String.valueOf(update));
    log.info(update ? "Book updated" : "Book update failed", "BookService.updateBook.end", logFieldMap);

    return update;
  }

  public boolean deleteBook(String id)
  {
    Map<String, String> logFieldMap = new LinkedHashMap<String, String>();
    logFieldMap.put("bookId", id);
    log.info("Removing book", "BookService.deleteBook.start", logFieldMap);

    Map<String, Object> query = new LinkedHashMap<>();
    query.put("id", id);

    boolean delete = bookDao.remove(query);

    logFieldMap.put("recordDeleted", String.valueOf(delete));
    log.info(delete ? "Book removed" : "Book remove failed", "BookService.deleteBook.end", logFieldMap);

    return delete;
  }
}
