package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/logout")
@Produces(MediaType.APPLICATION_JSON)
public class LogoutResource {

    @GET
    public void login(@Context HttpServletRequest request) {
        request.getSession().removeAttribute(User.USER_KEY);
    }

}
