package by.bytechs;

import by.bytechs.dao.config.RepositoryConfig;
import by.bytechs.desktopUI.MainWindows;
import by.bytechs.services.config.ServiceConfig;
import by.bytechs.services.interfaces.CashUnitInfoService;
import by.bytechs.services.interfaces.DateChangeTerminalParametersService;
import by.bytechs.util.interfaces.CSVReaderService;
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
                        .headless(false).run(args);


        DateChangeTerminalParametersService dateChangeTerminalParametersService = context.getBean(DateChangeTerminalParametersService.class);
        MainWindows mainWindows = context.getBean(MainWindows.class);
        mainWindows.setVisible(true);


        System.out.println("Выберите необходимое действие:\n" +
                "1. Поиск xml и сохранение в базу статусов кассет;\n" +
                "2. Поиск данных в CSV файле и создание xml о задержаных картах;\n" +
                "3. Поиск данных в CSV файле и сохранение данных в базу о переводе канала связи;\n" +
                "4. Поиск данных в CSV файле и сохранение данных в sql файл о переводе канала связи;\n" +
                "5. Выход.\n");
        Scanner scanner = new Scanner(System.in);
        String numberSelect = scanner.nextLine();
        if (numberSelect.equals("1")) {

        } else if (numberSelect.equals("2")) {

        } else if (numberSelect.equals("3")) {
            dateChangeTerminalParametersService.saveParametersOrSqlScript(System.in, true);
        } else if (numberSelect.equals("4")) {
            dateChangeTerminalParametersService.saveParametersOrSqlScript(System.in, false);
        } else {
            System.exit(1);
        }
    }

}
