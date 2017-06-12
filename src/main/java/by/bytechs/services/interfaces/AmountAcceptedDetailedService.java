package by.bytechs.services.interfaces;

import by.bytechs.desktopUI.WaitPanel;
import by.bytechs.entity.AmountAcceptedDetailed;

import java.util.Date;

/**
 * @author Romanovich Andrei
 */
public interface AmountAcceptedDetailedService {

    boolean save(AmountAcceptedDetailed acceptedDetailed);

    boolean findAndSave(Date beginDate, Date endDate, WaitPanel waitPanel);
}
