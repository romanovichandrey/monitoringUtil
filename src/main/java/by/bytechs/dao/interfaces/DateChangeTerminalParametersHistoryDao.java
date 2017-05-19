package by.bytechs.dao.interfaces;

import by.bytechs.entity.terminalParameters.DateChangeTerminalParametersHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Romanovich Andrei
 */
@Repository
public interface DateChangeTerminalParametersHistoryDao extends JpaRepository<DateChangeTerminalParametersHistory, Long> {
}
