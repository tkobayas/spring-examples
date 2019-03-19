package com.technicalkeeda.configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@ComponentScan(basePackages = "com.technicalkeeda")
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean
	public DataSource dataSource() throws NamingException {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/MySQLDS");
        System.out.println("datasource = " + dataSource);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
        System.out.println("jdbcTemplate = " + jdbcTemplate);
		return jdbcTemplate;
	}
	
    @Bean
    public JtaTransactionManager transactionManager() throws NamingException{
        JtaTransactionManager transactionManager
                = new JtaTransactionManager();
        System.out.println("transactionManager = " + transactionManager);
        return transactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate() throws NamingException {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager());
        System.out.println("transactionTemplate = " + transactionTemplate);
        return transactionTemplate;
    }

}