package by.bytechs.entity;

import by.bytechs.entity.terminalParameters.DateChangeTerminalParameters;
import by.bytechs.entity.terminalParameters.DateChangeTerminalParametersHistory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Romanovich Andrei
 */
@Entity
@Table(name = "Terminals")
public class Terminal {

    @Column(name = "TerminalID")
    @Id
    private String terminalId;
    @Column
    private String logicalName;
    @Column(name = "IP")
    private String ip;
    @Column(name = "Port")
    private int port;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location")
    private TerminalLocation terminalLocation;
    @Column(name = "Adr")
    private String address;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "idType")
    private Marka marka;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatusMan")
    private ManualStatus manualStatus;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "securityGroup")
    private SecurityGroups securityGroup;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCbu")
    private TerminalCbu terminalCbu;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "TerminalCurrency",
            joinColumns = @JoinColumn(name = "idTerm"),
            inverseJoinColumns = @JoinColumn(name = "idCurrency"))
    private Set<Currency> currencies;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "TerminalID")
    private TerminalAdvancedSettings advancedSettings;
    @Column(name = "timePaymentOrExtradition")
    private Date timePaymentOrExtradition;
    @OneToMany(mappedBy = "terminalID", fetch = FetchType.LAZY)
    private List<DateChangeTerminalParameters> dateChangeTerminalParametersList;
    @OneToMany(mappedBy = "terminalID", fetch = FetchType.LAZY)
    private List<DateChangeTerminalParametersHistory> dataChangeTerminalParametersHistories;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getLogicalName() {
        return logicalName;
    }

    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public TerminalLocation getLocation() {
        return terminalLocation;
    }

    public void setLocation(TerminalLocation terminalLocation) {
        this.terminalLocation = terminalLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public ManualStatus getManualStatus() {
        return manualStatus;
    }

    public void setManualStatus(ManualStatus manualStatus) {
        this.manualStatus = manualStatus;
    }

    public SecurityGroups getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(SecurityGroups securityGroup) {
        this.securityGroup = securityGroup;
    }

    public TerminalCbu getTerminalCbu() {
        return terminalCbu;
    }

    public void setTerminalCbu(TerminalCbu terminalCbu) {
        this.terminalCbu = terminalCbu;
    }

    public double getLongetude() {
        if (getAdvancedSettings() == null) {
            return 0.;
        }
        return getAdvancedSettings().getLongitude();
    }

    public void setLongetude(double longetude) {
        if (this.getAdvancedSettings() == null) {
            TerminalAdvancedSettings settings = new TerminalAdvancedSettings();
            settings.setTerminalId(this.getTerminalId());
            this.setAdvancedSettings(settings);
        }
        this.getAdvancedSettings().setLongitude(longetude);
    }

    public double getLatitude() {
        if (getAdvancedSettings() == null) {
            return 0.;
        }
        return getAdvancedSettings().getLatitude();
    }

    public void setLatitude(double latitude) {
        if (this.getAdvancedSettings() == null) {
            TerminalAdvancedSettings settings = new TerminalAdvancedSettings();
            settings.setTerminalId(this.getTerminalId());
            this.setAdvancedSettings(settings);
        }
        this.getAdvancedSettings().setLatitude(latitude);
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    public TerminalAdvancedSettings getAdvancedSettings() {
        return advancedSettings;
    }

    public void setAdvancedSettings(TerminalAdvancedSettings advancedSettings) {
        this.advancedSettings = advancedSettings;
    }

    public Date getTimePaymentOrExtradition() {
        return timePaymentOrExtradition;
    }

    public void setTimePaymentOrExtradition(Date timePaymentOrExtradition) {
        this.timePaymentOrExtradition = timePaymentOrExtradition;
    }

    public List<DateChangeTerminalParameters> getDateChangeTerminalParametersList() {
        return dateChangeTerminalParametersList;
    }

    public void setDateChangeTerminalParametersList(List<DateChangeTerminalParameters> dateChangeTerminalParametersList) {
        this.dateChangeTerminalParametersList = dateChangeTerminalParametersList;
    }

    public List<DateChangeTerminalParametersHistory> getDataChangeTerminalParametersHistories() {
        return dataChangeTerminalParametersHistories;
    }

    public void setDataChangeTerminalParametersHistories(List<DateChangeTerminalParametersHistory> dataChangeTerminalParametersHistories) {
        this.dataChangeTerminalParametersHistories = dataChangeTerminalParametersHistories;
    }
}