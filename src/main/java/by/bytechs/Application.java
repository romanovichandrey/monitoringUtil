package by.bytechs;

import by.bytechs.dao.config.RepositoryConfig;
import by.bytechs.services.config.ServiceConfig;
import by.bytechs.services.interfaces.CashUnitInfoService;
import by.bytechs.services.interfaces.DateChangeTerminalParametersService;
import by.bytechs.util.interfaces.CSVReaderService;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Scanner;

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
        CSVReaderService csvReaderService = context.getBean(CSVReaderService.class);
        DateChangeTerminalParametersService dateChangeTerminalParametersService = context.getBean(DateChangeTerminalParametersService.class);
        System.out.println("Выберите необходимое действие:\n" +
                "1. Поиск xml и сохранение в базу статусов кассет;\n" +
                "2. Поиск данных в CSV файле и создание xml о задержаных картах;\n" +
                "3. Поиск данных в CSV файле и сохранение данных в базу о переводе канала связи;\n" +
                "4. Поиск данных в CSV файле и сохранение данных в sql файл о переводе канала связи;\n" +
                "5. Выход.\n");
        Scanner scanner = new Scanner(System.in);
        String numberSelect = scanner.nextLine();
        if (numberSelect.equals("1")) {
            cashUnitInfoService.saveCashUnitInfo(System.in);
        } else if (numberSelect.equals("2")) {
            csvReaderService.saveXmlWuthDrawalCards(System.in);
        } else if (numberSelect.equals("3")) {
            dateChangeTerminalParametersService.saveParametersOrSqlScript(System.in, true);
        } else if (numberSelect.equals("4")) {
            dateChangeTerminalParametersService.saveParametersOrSqlScript(System.in, false);
        } else {
            System.exit(1);
        }

    }

}
