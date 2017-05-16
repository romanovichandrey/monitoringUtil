package by.bytechs.dao.interfaces;

import by.bytechs.entity.CashUnitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Romanovich Andrei
 */
@Repository
public interface CashUnitInfoDao extends JpaRepository<CashUnitInfo, Long> {
}
