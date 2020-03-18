package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.Book;
import be.kuleuven.sessylibrary.domain.BooksRepository;
import be.kuleuven.sessylibrary.domain.user.SpotlessBorrower;
import be.kuleuven.sessylibrary.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BorrowBooksResourceTests {

    private BorrowBooksResource resource;
    private BooksRepository booksRepository;

    @BeforeEach
    public void setUp() {
        booksRepository = mock(BooksRepository.class);
        resource = new BorrowBooksResource(booksRepository);
    }

    @Test
    public void borrowBooks_isbnOkAndUserLoggedIn_returnsStatusOk() {
        when(booksRepository.findBookByIsbn(124)).thenReturn(new Book());
        var request = mock(HttpServletRequest.class);
        var session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(User.USER_KEY)).thenReturn(new SpotlessBorrower());

        var response = resource.borrow(Optional.of(124), request);
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void borrowBooks_isbnOkAndUserNotLoggedIn_returnsStatusForbidden() {
        when(booksRepository.findBookByIsbn(124)).thenReturn(new Book());
        var request = mock(HttpServletRequest.class);
        var session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(User.USER_KEY)).thenReturn(null);

        var response = resource.borrow(Optional.of(124), request);
        assertThat(response.getStatus(), is(Response.Status.FORBIDDEN.getStatusCode()));
    }

    @Test
    public void borrowBooks_borrowingUnknownIsbn_throwsException() {
        when(booksRepository.findBookByIsbn(124)).thenReturn(null);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            resource.borrow(Optional.of(124), null);
        });
    }
}
