package hello;

import hello.jdbc.fixtures.DbFixturesPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplicationDbFixtures implements CommandLineRunner {

    public static void main(String args[]) {
        SpringApplication.run(JdbcApplicationDbFixtures.class, args);
    }

    @Autowired
    private DbFixturesPopulator dbFixturesPopulator;

    @Override
    public void run(String... strings) throws Exception {
        dbFixturesPopulator.createSchema();
        dbFixturesPopulator.insertData();
    }
}
