package by.bytechs.dao.interfaces;

import by.bytechs.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Romanovich Andrei
 */
@Repository
public interface TerminalDao extends JpaRepository<Terminal, String> {
}
