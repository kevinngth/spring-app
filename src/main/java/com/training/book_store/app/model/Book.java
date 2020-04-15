package com.training.book_store.app.model;

import javax.persistence.*;
import java.lang.Double;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private LocalDate datePublished;
    private Double rrp;

    @ManyToMany( cascade = { CascadeType.ALL },
            mappedBy = "books")
    private List<Store> stores = new ArrayList<>();

    @Version
    private long version;

    protected Book() {}

    public Book(String title, String author, LocalDate datePublished, Double rrp) {
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
        this.rrp = rrp;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", datePublished=" + datePublished +
                ", rrp=" + rrp +
                ", stores=" + stores +
                ", version=" + version +
                '}';
    }

    public List<Store> getStores() {
        return stores;
    }

    public void addStore(Store store) {
        this.stores.add( store );
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public Double getRrp() {
        return rrp;
    }

    public void setRrp(Double rrp) {
        this.rrp = rrp;
    }
}
