package by.bytechs.services;

import by.bytechs.dao.interfaces.CashUnitInfoDao;
import by.bytechs.entity.CashStatus;
import by.bytechs.entity.CashUnitInfo;
import by.bytechs.services.interfaces.CashStatusService;
import by.bytechs.services.interfaces.CashUnitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Romanovich Andrei
 */
@Service
@Transactional
public class CashUnitInfoServiceImpl implements CashUnitInfoService {

    @Autowired
    private CashStatusService cashStatusService;
    @Autowired
    private CashUnitInfoDao cashUnitInfoDao;

    @Override
    public void saveCashUnitInfo(InputStream inputStream) {
        try {
            Scanner scanner = new Scanner(inputStream);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            System.out.println("Введите начальную дату в формате dd.MM.yyyy HH:mm:ss");
            Date beginDate = dateFormat.parse(scanner.nextLine());
            System.out.println("Введите конечную дату в формате dd.MM.yyyy HH:mm:ss");
            Date endDate = dateFormat.parse(scanner.nextLine());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(beginDate);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            Date closeDate = calendar.getTime();
            long endDateMili = endDate.getTime();
            boolean dateStatus = true;
            while (closeDate.before(endDate) || closeDate.equals(endDate)) {
                Map<String, List<CashStatus>> cashStatusMap = cashStatusService.findCashStatusByDatesAndPst(beginDate, closeDate);
                for (Map.Entry<String, List<CashStatus>> entry : cashStatusMap.entrySet()) {
                    for (CashStatus cashStatus : entry.getValue()) {
                        CashUnitInfo cashUnitInfo = new CashUnitInfo();
                        cashUnitInfo.setTimeStamp(cashStatus.getTimestamp());
                        cashUnitInfo.setIdTerm(cashStatus.getTerminalId());
                        InputSource inputSource = new InputSource(new StringReader(cashStatus.getXml()));
                        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                        SAXParser saxParser = saxParserFactory.newSAXParser();
                        CashStatusHandler cashStatusHandler = new CashStatusHandler(cashUnitInfo, this);
                        saxParser.parse(inputSource, cashStatusHandler);
                        cashUnitInfo = null;
                        inputSource = null;
                        saxParserFactory = null;
                        saxParser = null;
                        cashStatusHandler = null;
                    }
                }
                System.out.println("Сохранение за период: " + dateFormat.format(beginDate) + " - " + dateFormat.format(closeDate) + " прошло успешно!");
                beginDate = closeDate;
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
                if (dateStatus && (endDateMili - closeDate.getTime()) < (24 * 60 * 60 * 1000)) {
                    closeDate = endDate;
                    dateStatus = false;
                } else {
                    closeDate = calendar.getTime();
                }
                cashStatusMap.clear();
                System.gc();

            }
            System.out.println("Операция выполнена успешно!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCashUnitInfo(CashUnitInfo cashUnitInfo) {
        cashUnitInfoDao.saveAndFlush(cashUnitInfo);
    }
}
