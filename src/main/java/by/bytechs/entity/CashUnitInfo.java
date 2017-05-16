package by.bytechs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This class storage cash unit information from PST.
 * @author Romanovich Andrei
 * @see Serializable
 */
@Entity
@Table
public class CashUnitInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pk_CashUnitInfo_Id")
    private Long id;
    @Column(nullable = false)
    private String idTerm;
    @Column(nullable = false)
    private String deviceName;
    @Column(nullable = false)
    private Date timeStamp;
    @Column(nullable = false)
    private int deviceStatus;
    @Column
    private int countBanknotes;
    @Column
    private String sumCurrencyInfo;

    public CashUnitInfo() {
    }

    public CashUnitInfo(String idTerm, String deviceName, Date timeStamp, int deviceStatus, int countBanknotes,
                        String sumCurrencyInfo) {
        this.idTerm = idTerm;
        this.deviceName = deviceName;
        this.timeStamp = timeStamp;
        this.deviceStatus = deviceStatus;
        this.countBanknotes = countBanknotes;
        this.sumCurrencyInfo = sumCurrencyInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(String idTerm) {
        this.idTerm = idTerm;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public int getCountBanknotes() {
        return countBanknotes;
    }

    public void setCountBanknotes(int countBanknotes) {
        this.countBanknotes = countBanknotes;
    }

    public String getSumCurrencyInfo() {
        return sumCurrencyInfo;
    }

    public void setSumCurrencyInfo(String sumCurrencyInfo) {
        this.sumCurrencyInfo = sumCurrencyInfo;
    }
}
