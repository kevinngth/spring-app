package com.training.book_store.app.service;

import com.training.book_store.app.model.Book;
import com.training.book_store.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Component
public class BookService {
    @Autowired
    BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addNewBook(String title, String author, String rawDatePublished, Double rrp) {
        LocalDate datePublished = LocalDate.parse(rawDatePublished);
        Book b = new Book(title, author, datePublished, rrp);
        bookRepo.save( b );
    }

    public List<Book> searchTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    public List<Book> getAllBooks() {
        List<Book> books = (List<Book>) bookRepo.findAll();
        return books;
    }

    public Double getDiscountedPrice(Book book, double discount) {
        return book.getRrp() * discount;
    }

    public HashMap<Book, Double> haveWeeklyWednesdaySale() {
        double discount = 0.5;
        double noDiscount = 1;
        List<Book> books = getAllBooks();
        return discountedBooks(books, isWednesday() ? discount : noDiscount );
    }

    public HashMap<Book, Double> haveHappyHourSale() {
        double discount = 0.9;
        double noDiscount = 1;
        List<Book> books = getAllBooks();
        return discountedBooks(books, isHappyHour() ? discount : noDiscount );
    }

    private boolean isWednesday() {
        return LocalDate.now().getDayOfWeek().toString().equals( "WEDNESDAY" );
    }

    private boolean isHappyHour() {
        return LocalDateTime.now().getHour() % 2 != 0;
    }

    private HashMap<Book, Double> discountedBooks(List<Book> books, double discount) {
        HashMap<Book, Double> discountedBooks = new HashMap<>(books.size());
        for (Book book : books) {
            discountedBooks.put(book, getDiscountedPrice(book, discount));
        }
        return discountedBooks;
    }
}
