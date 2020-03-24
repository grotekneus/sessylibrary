package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.BookLendService;
import be.kuleuven.sessylibrary.domain.BooksRepository;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/borrow")
@Produces(MediaType.APPLICATION_JSON)
public class BorrowBooksResource extends BooksResource {

    private BookLendService bookLendService;

    public BorrowBooksResource(BooksRepository repository) {
        super(repository);
        // TODO should be an interface to be able to mock with Mockito...
        bookLendService = new BookLendService();
    }

    public BorrowBooksResource(Jdbi dbInstance) {
        super(dbInstance);
        bookLendService = new BookLendService();
    }

    @GET
    public Response borrow(@QueryParam("isbn") Optional<Integer> isbn, @Context HttpServletRequest request) {
        var book = booksRepository.findBookByIsbn(isbn.orElse(6666));
        // extra code balblabla
        return Response.ok().build();
    }

}
