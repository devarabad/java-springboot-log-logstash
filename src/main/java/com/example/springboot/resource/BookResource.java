package com.example.springboot.resource;

import com.example.springboot.data.model.Book;
import com.example.springboot.resource.model.EntityResponse;
import com.example.springboot.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookResource
{
  Logger log = LoggerFactory.getLogger(this.getClass());

  private final BookService bookService;

  public BookResource(BookService bookService)
  {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public Book getBook(@PathVariable(name = "id") String id
                      , @RequestAttribute("correlationId") String correlationId
                      , @RequestHeader HttpHeaders headers)
  {
    Book book = bookService.getBook(id);
    return book;
  }

  @PostMapping
  public EntityResponse postBook( @RequestAttribute("correlationId") String correlationId
                                  , @RequestHeader HttpHeaders headers
                                  , @RequestBody Book newBook)
  {
    String generatedId          = bookService.addBook(newBook);
    Map<String, Object> entity  = new LinkedHashMap<String, Object>();
    entity.put("id", generatedId);

    return new EntityResponse("success"
                              , "Book added (but not really)"
                              , entity);
  }

  @PutMapping("/{id}")
  public EntityResponse putBook(@PathVariable(name = "id") String id, @RequestBody Book newBook)
  {
    boolean updated = bookService.updateBook(id, newBook);
    String status   = "success";
    String message  = "Book updated (but not really)";

    if (!updated)
    {
      status  = "failed";
      message = "Failed to update the book";
    }

    return new EntityResponse(status, message, null);
  }

  @PatchMapping("/{id}")
  public EntityResponse patchBook(@PathVariable(name = "id") String id, @RequestBody Book newBook)
  {
    boolean updated = bookService.updateBook(id, newBook);
    String status   = "success";
    String message  = "Book partially updated (but not really)";

    if (!updated)
    {
      status  = "failed";
      message = "Failed to update the book";
    }

    return new EntityResponse(status, message, null);
  }

  @DeleteMapping("/{id}")
  public EntityResponse deleteBook(@PathVariable(name = "id") String id)
  {
    boolean deleted = bookService.deleteBook(id);
    String status   = "success";
    String message  = "Book deleted (but not really)";

    if (!deleted)
    {
      status  = "failed";
      message = "Failed to delete the book";
    }

    return new EntityResponse(status, message, null);
  }
}
