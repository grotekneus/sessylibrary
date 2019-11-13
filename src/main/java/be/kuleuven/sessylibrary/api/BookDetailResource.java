package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.Book;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/get-book")
@Produces(MediaType.APPLICATION_JSON)
public class BookDetailResource extends BooksResource {

    public BookDetailResource(Jdbi dbInstance) {
        super(dbInstance);
    }

    @GET
    public Book find(@QueryParam("isbn") Optional<Integer> isbn) {
        return booksRepository.findBookByIsbn(isbn.orElse(666));
    }

}
