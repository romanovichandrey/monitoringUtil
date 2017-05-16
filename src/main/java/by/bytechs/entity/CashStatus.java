package by.bytechs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * This class storage cash status information from PST.
 * @author Romanovich Andrei
 * @see Serializable
 */
@Entity
@Table(name="cashStatus")
public class CashStatus implements Serializable {

    @Id
    @Column
    private Long id;

    @Column(name = "idTerm")
    private String terminalId;

    @Column(name = "devComl")
    private String xml;

    @Column
    private Date timestamp;

    @Column
    private String device;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        this.device = device;
    }
}
