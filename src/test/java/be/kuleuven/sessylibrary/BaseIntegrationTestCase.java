package be.kuleuven.sessylibrary;

import io.dropwizard.db.DataSourceFactory;
import org.h2.jdbcx.JdbcDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class BaseIntegrationTestCase {

    private Jdbi db;

    protected void cleanupDb() {
        Arrays.asList("sessyTest.db.mv.db", "sessyTest.db.trace.db")
                .stream()
                .map(s -> Paths.get(s))
                .forEach(path -> {
                    if(Files.exists(path)) {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    protected Jdbi getDb() {
        if(db == null) {
            this.db = Jdbi.create(getDataSource());
            db.installPlugin(new SqlObjectPlugin());
            db.open();
        }
        return db;
    }

    private DataSourceFactory createDataSourceFactory() {
        var dsFactory = new DataSourceFactory();
        dsFactory.setUrl("jdbc:h2:./sessyTest.db");
        dsFactory.setUser("sa");
        dsFactory.setPassword("");
        dsFactory.setDriverClass("org.h2.Driver");

        return dsFactory;
    }

    private JdbcDataSource getDataSource() {
        var factory = createDataSourceFactory();
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(factory.getUrl());
        dataSource.setUser(factory.getUser());
        dataSource.setPassword(factory.getPassword());
        return dataSource;
    }

}
