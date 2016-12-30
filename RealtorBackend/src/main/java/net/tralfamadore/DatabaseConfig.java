package net.tralfamadore;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*
@Configuration
@PropertySource("classpath:/META-INF/database.properties")
@EnableTransactionManagement
@EnableAsync
public class DatabaseConfig {
    private static Logger logger = Logger.getLogger(DatabaseConfig.class);

	@Autowired
	private Environment env;

	// -- specifying destroyMethod="close" is not required. It's inferred by the
	// Spring container.
	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws IOException {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(System.getProperty("password"));
		Properties props = new EncryptableProperties(encryptor);
		props.load(this.getClass().getClassLoader().getResourceAsStream("/Users/billreh/IdeaProjects/RealtorBackend/RealtorBackend/src/main/resources/META-INF/database.properties"));
		String passwd = props.getProperty("database.password");
		logger.info(passwd);


		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setUsername(env.getProperty("database.password"));
		dataSource.setPassword(passwd);
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan("net.tralfamadore");
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.id.new_generator_mappings", "false");
		entityManagerFactory.setJpaProperties(props);
		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
*/
