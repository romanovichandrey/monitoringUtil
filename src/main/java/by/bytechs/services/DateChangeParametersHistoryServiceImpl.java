package by.bytechs.services;

import by.bytechs.dao.interfaces.DateChangeTerminalParametersHistoryDao;
import by.bytechs.entity.terminalParameters.DateChangeTerminalParametersHistory;
import by.bytechs.services.interfaces.DateChangeParametersHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Romanovich Andrei
 */
@Service
@Transactional
public class DateChangeParametersHistoryServiceImpl implements DateChangeParametersHistoryService {

    @Autowired
    private DateChangeTerminalParametersHistoryDao dateChangeTerminalParametersHistoryDao;

    @Override
    public boolean saveOrUpdateParameterHistory(DateChangeTerminalParametersHistory dateChangeTerminalParametersHistory) {
        dateChangeTerminalParametersHistoryDao.saveAndFlush(dateChangeTerminalParametersHistory);
        return true;
    }
}
