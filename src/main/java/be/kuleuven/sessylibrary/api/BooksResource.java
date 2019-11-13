package be.kuleuven.sessylibrary.api;

import be.kuleuven.sessylibrary.domain.BooksRepository;
import org.jdbi.v3.core.Jdbi;

public abstract class BooksResource {

    protected final BooksRepository booksRepository;

    public BooksResource(Jdbi dbInstance) {
        this.booksRepository = dbInstance.onDemand(BooksRepository.class);
    }

}
