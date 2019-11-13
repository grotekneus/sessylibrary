package be.kuleuven.sessylibrary.domain;

public class Book {
    private int isbn;
    private String title;
    private String author;
    private String thumbnail;
    private String description;

    public Book() {
    }

    public Book(int isbn, String title, String author, String thumbnail, String beschrijving) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
        this.description = beschrijving;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
