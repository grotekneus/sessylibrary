package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.Book;
import be.kuleuven.sessylibrary.domain.BooksRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class BookDetailResourceTests {

    private BookDetailResource resource;
    private BooksRepository booksRepository;

    @BeforeEach
    public void setUp() {
        booksRepository = Mockito.mock(BooksRepository.class);
        resource = new BookDetailResource(booksRepository);
    }

    @Test
    public void find_ifNoIsbnIsPresent_findsWith666Instead() {
        Book book = new Book();
        Mockito.when(booksRepository.findBookByIsbn(666)).thenReturn(book);
        var result = resource.find(Optional.empty());

        assertThat(result, Matchers.sameInstance(book));
    }

    @Test
    public void find_findsBookViaRepository_returnsBookInstance() {
        Book book = new Book();
        Mockito.when(booksRepository.findBookByIsbn(123)).thenReturn(book);
        var result = resource.find(Optional.of(123));

        assertThat(result, Matchers.sameInstance(book));
    }
}
