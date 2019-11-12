package be.kuleuven.sessylibrary.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/find-books")
@Produces(MediaType.APPLICATION_JSON)
public class FindBooksResource {

    @GET
    public String find(@QueryParam("title")Optional<String> name) {
        return "goeie boek hé: " + name.get();
    }
}
