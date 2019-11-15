package com.lduran.multdb.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.lduran.multdb.db1.modelrepository", entityManagerFactoryRef = "db1EntityManagerFactory", transactionManagerRef = "db1TransactionManager")
public class DB1Config
{
	@Bean
	@Primary
	@ConfigurationProperties("db1.datasource")
	public DataSourceProperties db1Properties()
	{
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	public DataSource db1DataSource(@Qualifier("db1Properties") DataSourceProperties db1Properties)
	{
		return db1Properties.initializeDataSourceBuilder().build();
	}

	@Bean
	// @Primary
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(@Qualifier("db1DataSource") DataSource db1DataSource, EntityManagerFactoryBuilder builder)
	{
		return builder.dataSource(db1DataSource).packages("com.lduran.multdb.db1.modelentity").build();
	}

	@Bean
	@Primary
	public PlatformTransactionManager db1TransactionManager(EntityManagerFactory db1EntityManagerFactory)
	{
		return new JpaTransactionManager(db1EntityManagerFactory);
	}
}