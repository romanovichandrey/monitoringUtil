package by.bytechs.services.interfaces;

import by.bytechs.entity.Terminal;

import java.util.Map;

/**
 * @author Romanovich Andrei
 */
public interface TerminalService {

    Map<String, Terminal> findAllTerminal();
}
