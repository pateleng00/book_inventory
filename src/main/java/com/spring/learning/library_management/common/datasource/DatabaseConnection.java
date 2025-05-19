package com.spring.learning.library_management.common.datasource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

// This class is responsible for managing database connections.
@Configuration
@EnableJdbcRepositories(
        basePackages = {"com.spring.learning.library_management"})

public class DatabaseConnection {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate openConnection() throws SQLException {
         DataSource dataSource = openConnection().getDataSource(); // Replace with actual DataSource initialization
        assert dataSource != null;
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }




}
