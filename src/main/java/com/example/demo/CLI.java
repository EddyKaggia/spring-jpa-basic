package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

public class CLI implements CommandLineRunner {

    private final BookService bookService;

    @Autowired
    public CLI(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Enter your choice:");
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book author:");
        String author = scanner.nextLine();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        bookService.saveBook(book);
        System.out.println("Book added successfully!");
    }

    private void viewBooks() {
        List<Book> books = bookService.getAllBooks();
        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println(book.getId() + ". " + book.getTitle() + " by " + book.getAuthor());
        }
    }
}
