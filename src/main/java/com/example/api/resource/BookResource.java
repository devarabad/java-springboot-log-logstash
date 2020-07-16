package com.example.api.resource;

import com.example.api.resource.model.BookInfo;
import com.example.api.resource.model.EntityResponse;
import com.example.api.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookResource
{
  private final BookService bookService;

  public BookResource(BookService bookService)
  {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookInfo> getBook(@PathVariable(name = "id")  String id
                                          , @RequestHeader            HttpHeaders headers)
  {
    return ResponseEntity.ok(bookService.getBook(id));
  }

  @PostMapping
  public ResponseEntity<EntityResponse> postBook( @RequestHeader  HttpHeaders headers
                                                  , @RequestBody  BookInfo newBook)
  {
    String generatedId          = bookService.addBook(newBook);
    Map<String, Object> entity  = new LinkedHashMap<String, Object>();
    entity.put("id", generatedId);

    EntityResponse entityResponse = new EntityResponse("success"
                                                        , "Book added (but not really)"
                                                        , entity);

    return ResponseEntity.status(HttpStatus.CREATED).body(entityResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EntityResponse> putBook(@PathVariable(name = "id")  String id
                                                , @RequestBody              BookInfo updateBook)
  {
    boolean updated = bookService.updateBook(id, updateBook);
    String status   = "success";
    String message  = "Book updated (but not really)";

    if (!updated)
    {
      status  = "failed";
      message = "Failed to update the book";
    }

    EntityResponse entityResponse = new EntityResponse(status, message, null);

    return ResponseEntity.ok().body(entityResponse);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<EntityResponse> patchBook(@PathVariable(name = "id")  String id
                                                  , @RequestBody              BookInfo updateBook)
  {
    boolean updated = bookService.updateBook(id, updateBook);
    String status   = "success";
    String message  = "Book partially updated (but not really)";

    if (!updated)
    {
      status  = "failed";
      message = "Failed to update the book";
    }

    EntityResponse entityResponse = new EntityResponse(status, message, null);

    return ResponseEntity.ok().body(entityResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<EntityResponse> deleteBook(@PathVariable(name = "id") String id)
  {
    boolean deleted = bookService.deleteBook(id);
    String status   = "success";
    String message  = "Book deleted (but not really)";

    if (!deleted)
    {
      status  = "failed";
      message = "Failed to delete the book";
    }

    EntityResponse entityResponse = new EntityResponse(status, message, null);

    return ResponseEntity.ok().body(entityResponse);
  }
}
