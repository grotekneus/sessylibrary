package be.kuleuven.sessylibrary;

import be.kuleuven.sessylibrary.api.FindBooksResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    public void run(SessyLibConfig configuration, Environment environment) throws Exception {
        final var findBooks = new FindBooksResource();
        environment.jersey().register(findBooks);
        environment.healthChecks().register("all-ok", new SessyLibHealthCheck());
    }
}
