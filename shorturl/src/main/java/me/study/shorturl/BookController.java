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

    private Long idCount = 3L;

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

        if(foundBook == null){
            throw new BookNotFoundException("요청하신 책은 존재하지 않습니다.");
        }

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
    public String insertBook(@RequestBody Book book){
        validateBook(book);
        long newId = idCount;
        book.setId(newId);

        bookMap.put(newId,book);

        idCount++;

        return "OK";
    }

    public void validateBook(Book book){
        StringBuilder errorMessage = new StringBuilder();

        if(book.getName() == null || book.getName().trim().isEmpty()){
            errorMessage.append("책 이름은 필수로 입력해주세요.").append("\n");
        }
        if(book.getPrice() == null || book.getPrice() <= 0){
            errorMessage.append("책 가격은 0 이상이어야 합니다.").append("\n");
        }
        if(book.getAuthor() == null || book.getAuthor().trim().isEmpty()){
            errorMessage.append("책 저자는 필수로 입력해주세요.").append("\n");
        }

        if(errorMessage.length() > 0){
            throw new IllegalArgumentException(errorMessage.toString());
        }
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
