package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.Book;
import be.kuleuven.sessylibrary.domain.BooksRepository;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/find-books")
@Produces(MediaType.APPLICATION_JSON)
public class FindBooksResource extends BooksResource {

    public FindBooksResource(BooksRepository repository) {
        super(repository);
    }

    public FindBooksResource(Jdbi dbInstance) {
        super(dbInstance);
    }

    @GET
    public List<Book> find(@QueryParam("title")Optional<String> title) {
        return booksRepository.findBooksByTitle(title.orElse("").toLowerCase());
    }
}
