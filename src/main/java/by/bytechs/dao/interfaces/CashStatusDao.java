package by.bytechs.dao.interfaces;

import by.bytechs.entity.CashStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Romanovich Andrei
 */
@Repository
public interface CashStatusDao extends JpaRepository<CashStatus, Long> {

    List<CashStatus> findByTimestampBetweenAndTerminalIdContaining(Date beginDate, Date endDate, String terminalId);
}
