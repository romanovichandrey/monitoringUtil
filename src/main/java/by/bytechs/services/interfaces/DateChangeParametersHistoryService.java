package by.bytechs.services.interfaces;

import by.bytechs.entity.terminalParameters.DateChangeTerminalParametersHistory;

/**
 * @author Romanovich Andrei
 */
public interface DateChangeParametersHistoryService {

    boolean saveOrUpdateParameterHistory(DateChangeTerminalParametersHistory dateChangeTerminalParametersHistory);
}
