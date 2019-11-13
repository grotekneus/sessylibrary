package be.kuleuven.sessylibrary;

import be.kuleuven.sessylibrary.api.FindBooksResource;
import be.kuleuven.sessylibrary.domain.Book;
import be.kuleuven.sessylibrary.domain.BooksRepository;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class SessyLibApplication extends Application<SessyLibConfig> {

    public static void main(String[] args) throws Exception {
        new SessyLibApplication().run(args);
    }

    @Override
    public String getName() {
        return "sessylibrary";
    }

    @Override
    public void initialize(Bootstrap<SessyLibConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(SessyLibConfig config, Environment environment) throws Exception {
        final Jdbi db = CreateDbInstance(config, environment);

        registerRESTResources(environment, db);

        environment.healthChecks().register("all-ok", new SessyLibHealthCheck());
    }

    private Jdbi CreateDbInstance(SessyLibConfig config, Environment environment) {
        var factory = new JdbiFactory();
        var db = factory.build(environment, config.getDataSourceFactory(), "dbPool");
        db.installPlugin(new SqlObjectPlugin());

        var booksRepo = db.onDemand(BooksRepository.class);
        booksRepo.createTable();
        booksRepo.insert(new Book(123456, "title 1", "author", "thumbnail1.png", "my nice book one"));
        booksRepo.insert(new Book(123457, "title 2", "author", "thumbnail2.png", "my nice book two"));

        return db;
    }

    private void registerRESTResources(Environment environment, Jdbi dbInstance) {
        final var findBooks = new FindBooksResource(dbInstance);
        environment.jersey().register(findBooks);
    }
}
