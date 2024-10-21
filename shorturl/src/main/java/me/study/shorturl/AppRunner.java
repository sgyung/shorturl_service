package me.study.shorturl;

import me.study.shorturl.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;


@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private ConcurrentMap<Long, Book> bookMap;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setPrice(15000L);
        book.setName("채식주의자");
        book.setAuthor("한강");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setPrice(17000L);
        book2.setName("태백산맥");
        book2.setAuthor("조정래");

        bookMap.put(1L, book);
        bookMap.put(2L, book2);
    }
}
