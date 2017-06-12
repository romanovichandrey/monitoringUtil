package by.bytechs.dao.interfaces;

import by.bytechs.entity.JournalLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Romanovich Andrei
 */
public interface JournalLogEntityDao extends JpaRepository<JournalLogEntity, Long> {


    List<JournalLogEntity> findByTerminalIDAndTerminalDateBetweenAndJournalMessageIDIn(String terminalID,
                                                                                       Date beginDate, Date endDate,
                                                                                       List<Integer> journalMessageId);
}
