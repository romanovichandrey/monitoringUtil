package by.bytechs.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class mapping on the database amount accepted detailed.
 * @author Romanovich Andrei
 */
@Entity
@Table
public class AmountAcceptedDetailed implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String terminalID;
    @Column
    private String cashCertID;
    @Column
    private Long insertedAmount;
    @Column
    private String insertedCurrency;
    @Column
    private boolean insertedError;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public String getCashCertID() {
        return cashCertID;
    }

    public void setCashCertID(String cashCertID) {
        this.cashCertID = cashCertID;
    }

    public Long getInsertedAmount() {
        return insertedAmount;
    }

    public void setInsertedAmount(Long insertedAmount) {
        this.insertedAmount = insertedAmount;
    }

    public String getInsertedCurrency() {
        return insertedCurrency;
    }

    public void setInsertedCurrency(String insertedCurrency) {
        this.insertedCurrency = insertedCurrency;
    }

    public boolean isInsertedError() {
        return insertedError;
    }

    public void setInsertedError(boolean insertedError) {
        this.insertedError = insertedError;
    }
}