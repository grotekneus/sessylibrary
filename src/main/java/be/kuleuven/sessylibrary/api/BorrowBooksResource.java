package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.BookLendService;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/borrow")
@Produces(MediaType.APPLICATION_JSON)
public class BorrowBooksResource extends BooksResource {

    public BorrowBooksResource(Jdbi dbInstance) {
        super(dbInstance);
    }

    @GET
    public void borrow(@QueryParam("isbn") Optional<Integer> isbn, @Context HttpServletRequest request) {
        var book = booksRepository.findBookByIsbn(isbn.orElse(6666));
        if(book == null) {
            throw new UnsupportedOperationException("Book with isbn " + isbn + " not found!");
        }

        new BookLendService().lend(book, requireUserFrom(request));
    }

}
