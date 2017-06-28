package by.bytechs.services;

import by.bytechs.dao.interfaces.JournalLogEntityDao;
import by.bytechs.entity.JournalLogEntity;
import by.bytechs.services.interfaces.JournalLogEntityService;
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
public class JournalLogEntityServiceImpl implements JournalLogEntityService {
    @Autowired
    private JournalLogEntityDao journalLogEntityDao;

    @Override
    public Map<String, List<JournalLogEntity>> findByTerminalIdAndDateAndJornalMessageIDs(String terminalID, Date beginDate, Date endDate, List<Integer> journalMessageIds) {
        List<JournalLogEntity> journalLogEntities = journalLogEntityDao.
                findByTerminalIDAndTerminalDateBetweenAndJournalMessageIDIn(terminalID, beginDate, endDate, journalMessageIds);
        journalLogEntities.sort(Comparator.comparing(JournalLogEntity::getTerminalDate));
        Map<String, List<JournalLogEntity>> resultMap = new TreeMap<>();
        String cashCertID = null;
        Date cashCertDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        Pattern pattern = Pattern.compile("(.+:)\\s(.+)\\s(.+)");
        int iter = 0;
        for (JournalLogEntity entity : journalLogEntities) {
            if (entity.getJournalMessageID() == 8030) {
                cashCertDate = entity.getTerminalDate();
                Matcher matcher = pattern.matcher(entity.getMessage());
                if (matcher.matches()) {
                    cashCertID = matcher.group(2);
                    resultMap.put(cashCertID, new ArrayList<>());
                }
            } else if (cashCertID != null) {
                List<JournalLogEntity> logEntityList = resultMap.get(cashCertID);
                logEntityList.add(entity);
                if (iter == journalLogEntities.size() - 1) {
                    if (entity.getJournalMessageID() != 8018) {
                        calendar.add(Calendar.MINUTE, 60);
                        Date tempEndDate = calendar.getTime();
                        List<JournalLogEntity> tempList = journalLogEntityDao.
                                findByTerminalIDAndTerminalDateBetweenAndJournalMessageIDIn(terminalID, cashCertDate,
                                        tempEndDate, journalMessageIds);
                        tempList.sort(Comparator.comparing(JournalLogEntity::getTerminalDate));
                        for (JournalLogEntity tempEntity : tempList) {
                            if (tempEntity.getJournalMessageID() == 8030) {
                                Matcher matcher = pattern.matcher(entity.getMessage());
                                String tempCashCertId = null;
                                if (matcher.matches()) {
                                    tempCashCertId = matcher.group(2);
                                }
                                if (tempCashCertId != null && !tempCashCertId.equals(cashCertID)) {
                                    break;
                                }
                            } else {
                                logEntityList.add(entity);
                            }
                        }
                    }
                }
            }
            iter++;
        }
        return resultMap;
    }
}
