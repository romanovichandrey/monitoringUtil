package by.bytechs;

import by.bytechs.dao.config.RepositoryConfig;
import by.bytechs.services.config.ServiceConfig;
import by.bytechs.services.interfaces.CashUnitInfoService;
import org.springframework.boot.Banner;
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
                        .bannerMode(Banner.Mode.CONSOLE).run(args);
        CashUnitInfoService cashUnitInfoService = context.getBean(CashUnitInfoService.class);
        cashUnitInfoService.saveCashUnitInfo(System.in);

    }

}
