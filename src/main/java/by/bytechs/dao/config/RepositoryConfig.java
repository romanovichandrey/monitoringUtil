package by.bytechs.dao.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * @author Romanovich Andrei
 */
@Configuration
@ComponentScan(basePackages = "by.bytechs.dao.interfaces")
@EnableTransactionManagement
@EnableJpaRepositories("by.bytechs")
public class RepositoryConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setInitialPoolSize(Integer.parseInt(environment.getProperty("c3p0.initialSize")));
        dataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("c3p0.minSize")));
        dataSource.setMaxStatements(Integer.parseInt(environment.getProperty("c3p0.maxStatements")));
        dataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("c3p0.maxSize")));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        jpaProperties.setProperty("hibernate.show_sql",environment.getProperty("hibernate.show_sql"));
        factory.setJpaProperties(jpaProperties);
        factory.setPackagesToScan("by.bytechs.entity");

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }
}
