package by.bytechs.services.config;

import by.bytechs.dao.config.RepositoryConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Romanovich Andrei
 */
@Configuration
@ComponentScan(basePackages = "by.bytechs.services")
@Import(RepositoryConfig.class)
public class ServiceConfig {
}
