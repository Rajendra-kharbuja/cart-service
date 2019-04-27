package com.general.cart.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.host}")
    private String host;

    @Value("${database.port}")
    private String port;

    @Value("${database.databaseName}")
    private String databaseName;

    @Value("${database.schema:public}")
    private String schema;

    @Value("${database.maxPoolSize:5}")
    private int maxPoolSize;

    @Value("${database.minIdleConnections:0}")
    private int minIdleConnections;

    @Value("${database.idleTimeout:30000}")
    private int idleTimeout;

    @Primary
    @Bean
    public DataSource dataSource() {
        return hikariDataSource();
    }

    private DataSource hikariDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {

        String postgresqlUrl = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName + "?user=" + username + "&password=" + password;

        log.info("Creating HikariConfig");

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(postgresqlUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(minIdleConnections);
        config.setIdleTimeout(idleTimeout);
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName("hikari_connection_pool");
        config.setSchema(schema);

        config.setDataSourceProperties(dataSourceProperties());

        return config;
    }

    private Properties dataSourceProperties() {
        Properties properties = new Properties();
        properties.setProperty("cachePrepStmts", "true");
        properties.setProperty("prepStmtCacheSize", "250");
        properties.setProperty("useServerPrepStmts", "true");

        return properties;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate());
    }
}
