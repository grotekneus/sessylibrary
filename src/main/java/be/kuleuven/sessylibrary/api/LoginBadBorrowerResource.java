package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.user.BadBorrower;
import be.kuleuven.sessylibrary.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/login-bad")
@Produces(MediaType.APPLICATION_JSON)
public class LoginBadBorrowerResource {

    @GET
    public void login(@Context HttpServletRequest request) {
        request.getSession().setAttribute(User.USER_KEY, new BadBorrower());
    }

}
