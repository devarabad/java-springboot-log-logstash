package com.example.springboot.data.model;

import java.util.Date;

public class Book
{
  private final String  id;
  private final String  title;
  private final String  description;
  private final Date    datetime;
  private final String  genre;
  private final String  author;
  private final String  link;

  public Book(String id
              , String title
              , String description
              , Date datetime
              , String genre
              , String author
              , String link)
  {
    this.id           = id;
    this.title        = title;
    this.description  = description;
    this.datetime     = datetime;
    this.genre        = genre;
    this.author       = author;
    this.link         = link;
  }

  public String getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public Date getDatetime()
  {
    return datetime;
  }

  public String getGenre()
  {
    return genre;
  }

  public String getAuthor()
  {
    return author;
  }

  public String getLink()
  {
    return link;
  }
}
