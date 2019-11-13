package be.kuleuven.sessylibrary.domain;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BooksRepository {

    @SqlScript("CREATE TABLE IF NOT EXISTS books(isbn INTEGER PRIMARY KEY, title VARCHAR, author VARCHAR, thumbnail VARCHAR, description VARCHAR)")
    @SqlScript("TRUNCATE TABLE books")
    void createTable();

    @SqlUpdate("INSERT INTO books(isbn, title, author, thumbnail, description) VALUES (:isbn, :title, :author, :thumbnail, :description)")
    void insert(@BindBean Book book);

    @SqlQuery("SELECT * FROM books WHERE title LIKE '%' || :title || '%'")
    //@SqlQuery("SELECT * FROM books WHERE title = :title")
    @RegisterBeanMapper(Book.class)
    List<Book> findBooksByTitle(@Bind("title") String title);
}
