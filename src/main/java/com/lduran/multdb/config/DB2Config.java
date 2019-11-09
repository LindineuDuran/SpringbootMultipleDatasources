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

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory", basePackages =
{ "com.lduran.multdb.db2.modelrepository" })
public class DB2Config
{
	@Bean(name = "dataSourceProperties")
	@ConfigurationProperties("db2.datasource")
	public DataSourceProperties dataSourceProperties()
	{
		return new DataSourceProperties();
	}

	@Bean(name = "dataSource")
	@ConfigurationProperties("db2.datasource.configuration")
	public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties)
	{
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "db2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource)
	{
		return builder.dataSource(dataSource).packages("com.lduran.multdb.db2.modelentity").persistenceUnit("db2").build();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("db2EntityManagerFactory") EntityManagerFactory db2EntityManagerFactory)
	{
		return new JpaTransactionManager(db2EntityManagerFactory);
	}
}