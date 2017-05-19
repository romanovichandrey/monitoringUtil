package by.bytechs.services;

import by.bytechs.dao.interfaces.DateChangeTerminalParametersDao;
import by.bytechs.entity.Terminal;
import by.bytechs.entity.terminalParameters.DateChangeTerminalParameters;
import by.bytechs.entity.terminalParameters.TerminalParameters;
import by.bytechs.services.interfaces.DateChangeParametersHistoryService;
import by.bytechs.services.interfaces.DateChangeTerminalParametersService;
import by.bytechs.services.interfaces.TerminalParametersService;
import by.bytechs.services.interfaces.TerminalService;
import by.bytechs.util.interfaces.CSVReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Romanovich Andrei
 */
@Service
@Transactional
public class DateChangeTerminalParametersServiceImpl implements DateChangeTerminalParametersService {

    @Autowired
    private DateChangeTerminalParametersDao dateChangeTerminalParametersDao;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private DateChangeParametersHistoryService dateChangeParametersHistoryService;
    @Autowired
    private CSVReaderService csvReaderService;
    @Autowired
    private TerminalParametersService terminalParametersService;

    @Override
    public boolean saveParameters(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        System.out.println("Введите путь к файлу с данными о переводе канала связи в формате X:\\xxx\\xxx.csv");
        String filePath = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Map<String, Terminal> terminalMap = terminalService.findAllTerminal();
        List<String[]> arrayList = csvReaderService.getMasParamCsvFile(filePath);
        Map<Integer, TerminalParameters> parametersMap = terminalParametersService.findAllParameter();
        for (String[] mas : arrayList) {
            Date date;
            boolean networkBTSStatus;
            String typeTerminal;
            String terminalID;
            try {
                date = dateFormat.parse(mas[0]);
                networkBTSStatus = mas[1].contains("true") ? true : false;
                typeTerminal = mas[3];
                terminalID = mas[4];
                if (typeTerminal.equalsIgnoreCase("ATM")) {
                    while (terminalID.length() != 6) {
                        terminalID = "0" + terminalID;
                    }
                } else {
                    String pstCode = "09749";
                    while (terminalID.length() != 3) {
                        terminalID = "0" + terminalID;
                    }
                    terminalID = pstCode + terminalID;
                }
                if (terminalMap.get(terminalID) != null) {
                    if (networkBTSStatus) {
                        DateChangeTerminalParameters currentParameter = dateChangeTerminalParametersDao.
                                findByTerminalParametersAndTerminalID(parametersMap.get(1), terminalMap.get(terminalID));
                        if (currentParameter == null) {
                            DateChangeTerminalParameters changeTerminalParameters = new DateChangeTerminalParameters();
                            changeTerminalParameters.setDateChange(date);
                            changeTerminalParameters.setTerminalID(terminalMap.get(terminalID));
                            changeTerminalParameters.setTerminalParameters(parametersMap.get(1));
                            dateChangeTerminalParametersDao.saveAndFlush(changeTerminalParameters);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
