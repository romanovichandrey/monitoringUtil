package by.bytechs.services.interfaces;

import by.bytechs.entity.CashStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Romanovich Andrei
 */
public interface CashStatusService {

    Map<String, List<CashStatus>> findCashStatusByDatesAndPst(Date beginDate, Date endDate);
}
