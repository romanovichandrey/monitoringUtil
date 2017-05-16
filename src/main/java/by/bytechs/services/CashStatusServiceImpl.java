package by.bytechs.services;

import by.bytechs.dao.interfaces.CashStatusDao;
import by.bytechs.entity.CashStatus;
import by.bytechs.services.interfaces.CashStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Romanovich Andrei
 */
@Service
@Transactional
public class CashStatusServiceImpl implements CashStatusService {

    @Autowired
    private CashStatusDao cashStatusDao;

    @Override
    public Map<String, List<CashStatus>> findCashStatusByDatesAndPst(Date beginDate, Date endDate) {
        String idTerm = "09749";
        List<CashStatus> cashStatusList = cashStatusDao.findByTimestampBetweenAndTerminalIdContaining(beginDate, endDate, idTerm);
        Pattern pattern = Pattern.compile("000\\d+");
        Map<String, List<CashStatus>> resultMap = new TreeMap<>();
        for (CashStatus cashStatus : cashStatusList) {
            Matcher matcher = pattern.matcher(cashStatus.getTerminalId());
            if (!matcher.find()) {
                if (resultMap.get(cashStatus.getTerminalId()) != null) {
                    List<CashStatus> resultList = resultMap.get(cashStatus.getTerminalId());
                    resultList.add(cashStatus);
                } else {
                    List<CashStatus> resultList = new ArrayList<>();
                    resultList.add(cashStatus);
                    resultMap.put(cashStatus.getTerminalId(), resultList);
                }
            }
        }
        cashStatusList.clear();
        cashStatusList = null;
        idTerm = null;
        return resultMap;
    }
}
