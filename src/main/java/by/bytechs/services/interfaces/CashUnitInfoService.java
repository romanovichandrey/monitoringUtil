package by.bytechs.services.interfaces;

import by.bytechs.entity.CashUnitInfo;

import java.util.Date;

/**
 * @author Romanovich Andrei
 */
public interface CashUnitInfoService {

    boolean saveCashUnitInfo(Date beginDate, Date endDate);
    void saveCashUnitInfo(CashUnitInfo cashUnitInfo);
}
