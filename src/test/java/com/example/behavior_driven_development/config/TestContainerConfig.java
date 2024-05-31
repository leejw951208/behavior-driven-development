package com.example.behavior_driven_development.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

@Configuration
public class TestContainerConfig {
    private static final MySQLContainer<?> MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8")
                .withDatabaseName("test")
                .withUsername("sa")
                .withPassword("sa");
        MY_SQL_CONTAINER.start();
    }

    @Bean
    public MySQLContainer<?> provideMySQLContainer() {
        return MY_SQL_CONTAINER;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(MY_SQL_CONTAINER.getDriverClassName())
                .url(MY_SQL_CONTAINER.getJdbcUrl())
                .username(MY_SQL_CONTAINER.getUsername())
                .password(MY_SQL_CONTAINER.getPassword())
                .build();
    }
}
