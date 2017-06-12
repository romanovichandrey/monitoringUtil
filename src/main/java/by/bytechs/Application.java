package by.bytechs;

import by.bytechs.dao.config.RepositoryConfig;
import by.bytechs.desktopUI.MainWindows;
import by.bytechs.services.config.ServiceConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 */
@SpringBootApplication
@Import({RepositoryConfig.class, ServiceConfig.class})
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder().sources(Application.class, RepositoryConfig.class, ServiceConfig.class)
                        .headless(false).run(args);
        MainWindows mainWindows = context.getBean(MainWindows.class);
        mainWindows.setVisible(true);
    }
}
