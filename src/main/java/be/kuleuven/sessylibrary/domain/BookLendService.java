package be.kuleuven.sessylibrary.domain;

import be.kuleuven.sessylibrary.domain.user.User;

public class BookLendService {

    public void lend(Book book, User user) {
        // TODO unit test me!
        book.borrow();
    }

    public void bringBack(Book book, User user) {
        // TODO check if book needs bringing back? Unit test me!
        // TODO call book repository .update() to save state into the DB
        book.bringBack();
    }
}
