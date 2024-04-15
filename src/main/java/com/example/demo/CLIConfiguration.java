package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CLIConfiguration {

    @Bean
    public CLI cli(BookService bookService) {
        return new CLI(bookService);
    }
}
