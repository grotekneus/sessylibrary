package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.BooksRepository;
import be.kuleuven.sessylibrary.domain.user.User;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.http.HttpServletRequest;

public abstract class BooksResource {

    protected final BooksRepository booksRepository;

    public BooksResource(Jdbi dbInstance) {
        this.booksRepository = dbInstance.onDemand(BooksRepository.class);
    }

    protected User requireUserFrom(HttpServletRequest request) {
        var user = request.getSession().getAttribute(User.USER_KEY);
        if(user == null) {
            throw new UnsupportedOperationException("user not logged in!");
        }

        return (User) user;
    }

}
