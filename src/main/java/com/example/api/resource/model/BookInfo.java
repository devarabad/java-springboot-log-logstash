package com.example.api.resource.model;

public class BookInfo
{
  private String  id;
  private String  title;
  private String  description;
  private String  publishDate;
  private String  genre;
  private String  author;
  private String  link;

  public BookInfo()
  {
  }

  public BookInfo(String id
                  , String title
                  , String description
                  , String publishDate
                  , String genre
                  , String author
                  , String link)
  {
    this.id           = id;
    this.title        = title;
    this.description  = description;
    this.publishDate  = publishDate;
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

  public String getPublishDate()
  {
    return publishDate;
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
