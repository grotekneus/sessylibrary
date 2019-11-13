package be.kuleuven.sessylibrary.infrastructure;


import be.kuleuven.sessylibrary.domain.Book;
import org.hamcrest.collection.IsIterableWithSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlainTextBooksRepositoryTests {

    private PlainTextBooksRepository repository;

    @BeforeAll
    public void setUp() {
        repository = new PlainTextBooksRepository();
    }

    @AfterEach
    public void cleanUp() {
        var db = dbFile();
        if(db.exists()) {
            db.delete();
        }
    }

    private File dbFile() {
        return new File(PlainTextBooksRepository.DB_FILE_NAME);
    }

    @Test
    public void FindByTitle() {
        repository.createTable();
        repository.insert(new Book(1, "title", "author", "thumbnail.jpg", "test"));
        repository.insert(new Book(2, "title2", "author", "thumbnail.jpg", "test2"));

        var resultArray = repository.findBooksByTitle("title2");
        assertThat(resultArray, IsIterableWithSize.iterableWithSize(1));

        var result = resultArray.get(0);
        assertThat(result.getIsbn(), is(2));
        assertThat(result.getTitle(), is(equalTo("title2")));
    }

    @Test
    public void FindByISBN() {
        repository.createTable();
        repository.insert(new Book(2, "title2", "author", "thumbnail.jpg", "test2"));

        var result = repository.findBookByIsbn(2);
        assertThat(result.getIsbn(), is(2));
        assertThat(result.getTitle(), is(equalTo("title2")));
    }

    @Test
    public void InsertCreatesNewRow() throws IOException {
        repository.createTable();
        repository.insert(new Book(1, "title", "author", "thumbnail.jpg", "test"));
        repository.insert(new Book(2, "title2", "author", "thumbnail.jpg", "test2"));

        var result = Files.readAllLines(Paths.get(dbFile().toURI()));
        assertThat(result, hasItems("1|title|author|thumbnail.jpg|test", "2|title2|author|thumbnail.jpg|test2"));
    }

    @Test
    public void CreateDatabaseIsEmptyFile() {
        repository.createTable();
        assertThat(dbFile().exists(), is(true));
    }

}
