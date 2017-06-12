package by.bytechs.services;

import by.bytechs.dao.interfaces.TerminalDao;
import by.bytechs.entity.Terminal;
import by.bytechs.services.interfaces.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Romanovich Andrei
 */
@Service
@Transactional
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private TerminalDao terminalDao;

    @Override
    public Map<String, Terminal> findAllTerminal() {
        List<Terminal> terminalList = terminalDao.findAll();
        Map<String, Terminal> terminalMap = new TreeMap<>();
        for (Terminal terminal : terminalList) {
            terminalMap.put(terminal.getLogicalName(), terminal);
        }
        return terminalMap;
    }

    @Override
    public Map<String, Terminal> findAllPst() {
        List<Terminal> terminalList = terminalDao.findAll();
        Map<String, Terminal> terminalMap = new TreeMap<>();
        Pattern pattern = Pattern.compile("09749\\d+");
        for (Terminal terminal : terminalList) {
            Matcher matcher = pattern.matcher(terminal.getTerminalId());
            if (matcher.find()) {
                terminalMap.put(terminal.getTerminalId(), terminal);
            }
        }
        return terminalMap;
    }
}
