package be.kuleuven.sessylibrary.domain;

import be.kuleuven.sessylibrary.domain.user.User;

public class BookLendService {

    public void lend(Book book, User user) {
        // TODO unit test me!
        book.borrow();
    }
}
