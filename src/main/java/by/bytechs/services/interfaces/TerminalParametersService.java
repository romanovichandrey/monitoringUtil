package by.bytechs.services.interfaces;

import by.bytechs.entity.terminalParameters.TerminalParameters;

import java.util.Map;

/**
 * @author Romanovich Andrei
 */
public interface TerminalParametersService {
    Map<Integer, TerminalParameters> findAllParameter();
}
