package com.github.pavelvil.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "file:src/test/resources/application.properties")
public class TestBasicConfiguration {

    @Value("#{environment['spring.datasource.url']}")
    private String jdbcUrl;
    @Value("#{environment['spring.datasource.username']}")
    private String username;
    @Value("#{environment['spring.datasource.password']}")
    private String password;
    @Value("#{environment['spring.datasource.driver-class-name']}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().driverClassName(driverClassName)
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    public NamedParameterJdbcOperations jdbcTemplate() throws Exception {
        final DataSource dataSource = dataSource();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("/schema-test.sql"));
        populator.addScript(new ClassPathResource("/data-test.sql"));
        DatabasePopulatorUtils.execute(populator, dataSource);
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
