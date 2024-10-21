package me.study.shorturl;

import me.study.shorturl.dto.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Bean은 주입받아서 쓰는 애들
 *
 *
 */
@Configuration
public class AppConfig{

    @Bean
    public ConcurrentMap<Long, Book> bookMap(){
        return new ConcurrentHashMap<>();
    }

}
