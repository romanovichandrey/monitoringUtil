package by.bytechs.services;

import by.bytechs.dao.interfaces.AmountAcceptedDetailedDao;
import by.bytechs.desktopUI.WaitPanel;
import by.bytechs.entity.AmountAcceptedDetailed;
import by.bytechs.entity.JournalLogEntity;
import by.bytechs.entity.Terminal;
import by.bytechs.services.interfaces.AmountAcceptedDetailedService;
import by.bytechs.services.interfaces.JournalLogEntityService;
import by.bytechs.services.interfaces.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Romanovich Andrei
 */
@Service
public class AmountAcceptedDetailedServiceImpl implements AmountAcceptedDetailedService {
    @Autowired
    private JournalLogEntityService journalLogEntityService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private AmountAcceptedDetailedDao amountAcceptedDetailedDao;

    @Override
    @Transactional
    public boolean save(AmountAcceptedDetailed acceptedDetailed) {
        AmountAcceptedDetailed temp = amountAcceptedDetailedDao.saveAndFlush(acceptedDetailed);
        if (temp != null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean findAndSave(Date beginDate, Date endDate, WaitPanel waitPanel) {
        Map<String, Terminal> terminalMap = terminalService.findAllPst();
        Pattern pattern = Pattern.compile("(.+):\\s(\\d+)\\s(\\w{3})(.+)");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Map.Entry<String, Terminal> entry : terminalMap.entrySet()) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(beginDate);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date tempEndDate = calendar.getTime();
            Date tempBeginDate = beginDate;
            while (tempEndDate.compareTo(endDate) != 1) {
                waitPanel.setText("(" + entry.getValue().getLogicalName() + ":" + dateFormat.format(tempEndDate) + ")");
                Map<String, List<JournalLogEntity>> journalLogMap = journalLogEntityService.
                        findByTerminalIdAndDateAndJornalMessageIDs(entry.getKey(), tempBeginDate, tempEndDate, Arrays.asList(8030, 80181, 8018));
                for (Map.Entry<String, List<JournalLogEntity>> entry1 : journalLogMap.entrySet()) {
                    AmountAcceptedDetailed detailed = new AmountAcceptedDetailed();
                    detailed.setCashCertID(entry1.getKey());
                    detailed.setTerminalID(entry.getKey());
                    for (JournalLogEntity logEntity : entry1.getValue()) {
                        detailed.setTimeStamp(logEntity.getTerminalDate());
                        if (logEntity.getJournalMessageID() == 80181) {
                            Matcher matcher = pattern.matcher(logEntity.getMessage());
                            if (matcher.matches()) {
                                detailed.setInsertedAmount(Long.parseLong(matcher.group(2)));
                                detailed.setInsertedCurrency(matcher.group(3));
                            }
                        } else if (logEntity.getJournalMessageID() == 8018) {
                            Matcher matcher = pattern.matcher(logEntity.getMessage());
                            if (matcher.matches()) {
                                detailed.setInsertedAmount(Long.parseLong(matcher.group(2)));
                                detailed.setInsertedCurrency(matcher.group(3));
                                detailed.setInsertedError(true);
                            }
                        }
                    }
                    save(detailed);
                }
                tempBeginDate = tempEndDate;
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                tempEndDate = calendar.getTime();
            }
        }
        return true;
    }
}
