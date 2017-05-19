package by.bytechs.dao.interfaces;

import by.bytechs.entity.terminalParameters.TerminalParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Romanovich Andrei
 */
@Repository
public interface TerminalParametersDao extends JpaRepository<TerminalParameters, Integer> {
}
