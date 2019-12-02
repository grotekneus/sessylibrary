package be.kuleuven.sessylibrary.infrastructure;

import be.kuleuven.sessylibrary.domain.Book;
import be.kuleuven.sessylibrary.domain.BooksRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class PlainTextBooksRepository implements BooksRepository {

    private File dbHandle;
    public static final String DB_FILE_NAME = "db.txt";

    private Path dbPath() {
        return Paths.get(dbHandle.toURI());
    }

    @Override
    public void createTable() {
        dbHandle = new File(DB_FILE_NAME);
        if(dbHandle.exists()) {
            dbHandle.delete();
        }
        try {
            dbHandle.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("unable to create DB file...");
        }
    }

    @Override
    public void insert(Book book) {
        try {
            Files.write(dbPath(), serializeBook(book).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("unable to append DB file...");
        }
    }

    @Override
    public void update(Book book) {

    }

    private List<Book> findAllBooksInFile() {
        try {
            return Files.readAllLines(dbPath())
                    .stream()
                    .map(line -> deserializeBook(line))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("unable to read DB file...");
        }
    }

    private Book deserializeBook(String line) {
        String[] split = line.split("\\|");
        return new Book(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4]);
    }

    private String serializeBook(Book book) {
        return String.format("%s|%s|%s|%s|%s\n", book.getIsbn() + "", book.getTitle(), book.getAuthor(), book.getThumbnail(), book.getDescription());
    }

    @Override
    public Book findBookByIsbn(int isbn) {
        return findAllBooksInFile()
                .stream()
                .filter(book -> book.getIsbn() == isbn)
                .findFirst().get();
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return findAllBooksInFile()
                .stream()
                .filter(book -> book.getTitle().contains(title))
                .collect(Collectors.toList());
    }
}
