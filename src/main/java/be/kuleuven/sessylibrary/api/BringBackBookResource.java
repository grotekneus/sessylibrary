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
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/bring-back")
@Produces(MediaType.APPLICATION_JSON)
public class BringBackBookResource extends BooksResource {

    public BringBackBookResource(Jdbi dbInstance) {
        super(dbInstance);
    }

    @GET
    public Response bringBack(@QueryParam("isbn") Optional<Integer> isbn, @Context HttpServletRequest request) {
        var book = booksRepository.findBookByIsbn(isbn.orElse(6666));
        if(book == null) {
            throw new UnsupportedOperationException("Book with isbn " + isbn + " not found!");
        }

        try {
            new BookLendService().bringBack(book, requireUserFrom(request));
        } catch(UnsupportedOperationException oe) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok().build();
    }
}
