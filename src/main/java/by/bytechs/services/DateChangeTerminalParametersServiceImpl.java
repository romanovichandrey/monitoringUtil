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

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    public boolean saveParametersOrSqlScript(InputStream inputStream, boolean operation) {
        try {
            Scanner scanner = new Scanner(inputStream);
            System.out.println("Введите путь к файлу с данными о переводе канала связи в формате X:\\xxx\\xxx.csv");
            String filePath = scanner.nextLine();
            String saveFilePath = null;
            BufferedWriter bufferedWriter = null;
            if (!operation) {
                System.out.println("Введите путь для сохранения файла в формате X:\\xxx\\xxxx.sql");
                saveFilePath = scanner.nextLine();
                File saveFile = new File(saveFilePath);
                if (!saveFile.exists()) {
                    saveFile.mkdirs();
                }
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile)));
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String[]> arrayList = csvReaderService.getMasParamCsvFile(filePath);
            Map<String, Terminal> terminalMap = terminalService.findAllTerminal();
            Map<Integer, TerminalParameters> parametersMap = null;
            if (operation) {
                parametersMap = terminalParametersService.findAllParameter();
            }
            for (String[] mas : arrayList) {
                Date date;
                boolean networkBTSStatus;
                String typeTerminal;
                String logicalName;
                try {
                    date = dateFormat.parse(mas[0]);
                    networkBTSStatus = mas[1].contains("true") ? true : false;
                    typeTerminal = mas[3];
                    logicalName = mas[4];
                    if (typeTerminal.equalsIgnoreCase("ATM")) {
                        while (logicalName.length() != 3) {
                            logicalName = "0" + logicalName;
                        }
                        logicalName = "АТМ " + logicalName;
                    } else {
                        while (logicalName.length() != 3) {
                            logicalName = "0" + logicalName;
                        }
                        logicalName = "ПСТ " + logicalName;
                    }
                    if (operation) {
                        if (terminalMap.get(logicalName) != null) {
                            if (networkBTSStatus) {
                                DateChangeTerminalParameters currentParameter = dateChangeTerminalParametersDao.
                                        findByTerminalParametersAndTerminalID(parametersMap.get(1), terminalMap.get(logicalName));
                                if (currentParameter == null) {
                                    DateChangeTerminalParameters changeTerminalParameters = new DateChangeTerminalParameters();
                                    changeTerminalParameters.setDateChange(date);
                                    changeTerminalParameters.setTerminalID(terminalMap.get(logicalName));
                                    changeTerminalParameters.setTerminalParameters(parametersMap.get(1));
                                    dateChangeTerminalParametersDao.saveAndFlush(changeTerminalParameters);
                                }
                            }
                        }
                    } else {
                        if (terminalMap.get(logicalName) != null) {
                            String insertLine = "INSERT INTO [Monitoring].[dbo].[DateChangeTerminalParameters] " +
                                    "(parameterID, dateChange, terminalID) VALUES(1,";
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(insertLine);
                            stringBuffer.append("'" + sqlFormat.format(date) + "',");
                            stringBuffer.append("'" + terminalMap.get(logicalName).getTerminalId() + "')");
                            bufferedWriter.write(stringBuffer.toString());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
