package be.kuleuven.sessylibrary.domain;

import be.kuleuven.sessylibrary.BaseIntegrationTestCase;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BooksRepositoryIntegrationTests extends BaseIntegrationTestCase {

    private BooksRepository repository;

    @BeforeEach
    public void setUpRepo() {
        this.repository = getDb().onDemand(BooksRepository.class);
        this.repository.createTable();
    }

    @AfterEach
    public void tearDown() {
        cleanupDb();
    }

    @Test
    public void canUpdateBooksBySettingBorrowedFlag() {
        var book = new Book(123, "title", "author", "thumbnail.png", "very good yes yes");
        repository.insert(book);
        book.setBorrowed(true);
        repository.update(book);

        var updatedBook = repository.findBookByIsbn(123);
        assertThat(updatedBook.isBorrowed(), is(true));
    }

    @Test
    public void canInsertBook() {
        repository.insert(new Book(123, "title", "author", "thumbnail.png", "very good yes yes"));
        var book = repository.findBookByIsbn(123);

        assertThat(book.getTitle(), is("title"));
    }
}
