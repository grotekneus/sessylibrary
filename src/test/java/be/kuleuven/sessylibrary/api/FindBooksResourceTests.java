package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.Book;
import be.kuleuven.sessylibrary.domain.BooksRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindBooksResourceTests {

    private FindBooksResource resource;
    private BooksRepository booksRepository;

    @BeforeEach
    public void setUp() {
        booksRepository = mock(BooksRepository.class);
        resource = new FindBooksResource(booksRepository);
    }

    @Test
    public void find_ifNoTitleIsPresent_findsWithEmptyStringInstead() {
        Book book = new Book();
        when(booksRepository.findBooksByTitle("")).thenReturn(Arrays.asList(book));
        var result = resource.find(Optional.empty()).get(0);

        assertThat(result, Matchers.sameInstance(book));
    }

    @Test
    public void find_findsBookViaRepository_returnsBookInstance() {
        Book book = new Book();
        when(booksRepository.findBooksByTitle("title")).thenReturn(Arrays.asList(book));
        var result = resource.find(Optional.of("title")).get(0);

        assertThat(result, Matchers.sameInstance(book));
    }
}
