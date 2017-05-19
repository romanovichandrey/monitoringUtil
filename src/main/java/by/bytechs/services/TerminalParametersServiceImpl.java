package by.bytechs.services;

import by.bytechs.dao.interfaces.TerminalParametersDao;
import by.bytechs.entity.terminalParameters.TerminalParameters;
import by.bytechs.services.interfaces.TerminalParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Romanovich Andrei
 */
@Service
@Transactional
public class TerminalParametersServiceImpl implements TerminalParametersService {

    @Autowired
    private TerminalParametersDao terminalParametersDao;

    @Override
    public Map<Integer, TerminalParameters> findAllParameter() {
        Map<Integer, TerminalParameters> parametersMap = new TreeMap<>();
        for (TerminalParameters parameters : terminalParametersDao.findAll()) {
            parametersMap.put(parameters.getParameterID(), parameters);
        }
        return parametersMap;
    }
}
