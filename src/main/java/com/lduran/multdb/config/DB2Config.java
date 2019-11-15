package com.lduran.multdb.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.lduran.multdb.db2.modelrepository", entityManagerFactoryRef = "db2EntityManagerFactory", transactionManagerRef = "db2TransactionManager")
public class DB2Config
{
	@Bean
	@ConfigurationProperties("db2.datasource")
	public DataSourceProperties db2Properties()
	{
		return new DataSourceProperties();
	}

	@Bean
	public DataSource db2DataSource(@Qualifier("db2Properties") DataSourceProperties db2Properties)
	{
		return db2Properties.initializeDataSourceBuilder().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(@Qualifier("db2DataSource") DataSource db2DataSource, EntityManagerFactoryBuilder builder)
	{
		return builder.dataSource(db2DataSource).packages("com.lduran.multdb.db2.modelentity").build();
	}

	@Bean
	public PlatformTransactionManager db2TransactionManager(EntityManagerFactory db2EntityManagerFactory)
	{
		return new JpaTransactionManager(db2EntityManagerFactory);
	}
}