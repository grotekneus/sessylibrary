package be.kuleuven.sessylibrary;

import be.kuleuven.sessylibrary.api.*;
import be.kuleuven.sessylibrary.domain.BooksRepository;
import be.kuleuven.sessylibrary.domain.FavoriteBooks;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.session.SessionHandler;
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
        environment.servlets().setSessionHandler(new SessionHandler());
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
        FavoriteBooks.WoutersFavorites().stream().forEach(book -> booksRepo.insert(book));

        return db;
    }

    private void registerRESTResources(Environment environment, Jdbi dbInstance) {
        environment.jersey().register(new FindBooksResource(dbInstance));
        environment.jersey().register(new BookDetailResource(dbInstance));
        environment.jersey().register(new BorrowBooksResource(dbInstance));
        environment.jersey().register(new LoginBadBorrowerResource());
        environment.jersey().register(new LoginSpotlessBorrowerResource());
        environment.jersey().register(new LogoutResource());
    }
}
