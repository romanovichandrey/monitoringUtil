package by.bytechs.services.interfaces;

import by.bytechs.entity.CashUnitInfo;

import java.io.InputStream;

/**
 * @author Romanovich Andrei
 */
public interface CashUnitInfoService {

    void saveCashUnitInfo(InputStream inputStream);
    void saveCashUnitInfo(CashUnitInfo cashUnitInfo);
}
