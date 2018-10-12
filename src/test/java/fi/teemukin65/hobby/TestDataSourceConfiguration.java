package fi.teemukin65.hobby;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@TestConfiguration
public class TestDataSourceConfiguration {
    private final Environment env;

    @Autowired
    public TestDataSourceConfiguration(Environment env) {
        this.env = env;
    }

    @Bean()
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        hikariDataSource.setJdbcUrl(env.getRequiredProperty("spring.datasource.url").concat("-test"));
        hikariDataSource.setUsername("storyadmin");
        hikariDataSource.setPassword("storyadmin");
        return hikariDataSource;

    }

}
