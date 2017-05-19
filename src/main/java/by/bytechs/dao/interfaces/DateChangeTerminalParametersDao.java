package by.bytechs.dao.interfaces;

import by.bytechs.entity.Terminal;
import by.bytechs.entity.terminalParameters.DateChangeTerminalParameters;
import by.bytechs.entity.terminalParameters.TerminalParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Romanovich Andrei
 */
@Repository
public interface DateChangeTerminalParametersDao extends JpaRepository<DateChangeTerminalParameters, Integer> {

    DateChangeTerminalParameters findByTerminalParametersAndTerminalID(TerminalParameters terminalParameters, Terminal terminalId);
}
