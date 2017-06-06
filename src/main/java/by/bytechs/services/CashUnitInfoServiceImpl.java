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
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public boolean saveCashUnitInfo(Date beginDate, Date endDate) {
        try {

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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void saveCashUnitInfo(CashUnitInfo cashUnitInfo) {
        cashUnitInfoDao.saveAndFlush(cashUnitInfo);
    }
}
