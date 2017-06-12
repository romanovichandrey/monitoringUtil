package by.bytechs.services.interfaces;

import by.bytechs.entity.JournalLogEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Romanovich Andrei
 */
public interface JournalLogEntityService {

    /**
     * Find JournalLogEntity by terminalId, dates and journalMessageIds
     * @param terminalID
     * @param beginDate
     * @param endDate
     * @param journalMessageIds
     * @return Map (cashCertID -> list journalLogEntity)
     */
    Map<String, List<JournalLogEntity>> findByTerminalIdAndDateAndJornalMessageIDs(String terminalID, Date beginDate,
                                                                                   Date endDate, List<Integer> journalMessageIds);
}
