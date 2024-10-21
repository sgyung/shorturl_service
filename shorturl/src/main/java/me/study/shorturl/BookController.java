package me.study.shorturl;


import me.study.shorturl.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

/**
 * CRUD
 * 1.Validation이 안되어있다.
 */
@RestController
public class BookController {

    @Autowired
    private ConcurrentMap<Long,Book> bookMap;


    @GetMapping(path ="/abc")
    public ResponseEntity<Object> retrieveBook() throws URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("https://m.gsshop.com/prd/prd.gs?prdid=1061268195&pgmID=547088&mseq=W00054-C_MYSHOP-SUB_PRD"));
        return new ResponseEntity<>(
                httpHeaders, HttpStatus.PERMANENT_REDIRECT
        );
    }


    @GetMapping(path ="/book/{id}")
    public Book retrieveBook(@PathVariable Long id){
        Book foundBook = bookMap.get(id);

        return foundBook;
    }

    @GetMapping(path ="/books")
    public List<Book> retrieveAllBook(){
        List<Book> books = new ArrayList<>();

        for(Long id : bookMap.keySet()){
            books.add(bookMap.get(id));
        }

        return books;
    }

    @PostMapping(path = "/book")
    public String insertBook(@Validated  @RequestBody Book book){
        bookMap.put(book.getId(),book);
        return "OK";
    }

    @DeleteMapping(path = "/book/{id}")
    public String deleteBook(@PathVariable Long id){
        bookMap.remove(id);
        return "OK";
    }

    @PatchMapping(path = "/book")
    public String patchBook(@RequestBody Book bookRequestDto){
        Long id = bookRequestDto.getId();
        Book targetBook = bookMap.get(id);
        targetBook.setName(bookRequestDto.getName());
        targetBook.setPrice(bookRequestDto.getPrice());
        return "OK";
    }

}
