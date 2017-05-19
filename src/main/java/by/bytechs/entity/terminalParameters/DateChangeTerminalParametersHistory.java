package by.bytechs.entity.terminalParameters;

import by.bytechs.entity.Terminal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * It is intended for storage of all changes of parameters of the terminal
 * @author Romanovich Andrei
 */
@Entity
@Table
public class DateChangeTerminalParametersHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parameterID", nullable = false)
    private TerminalParameters terminalParameters;
    @Column
    private Date dateChange;
    @ManyToOne
    @JoinColumn(name = "TerminalID", nullable = false)
    private Terminal terminalID;
    @Column
    private String userName;
    @Column
    private String oldValue;
    @Column
    private String newValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TerminalParameters getTerminalParameters() {
        return terminalParameters;
    }

    public void setTerminalParameters(TerminalParameters terminalParameters) {
        this.terminalParameters = terminalParameters;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    public Terminal getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(Terminal terminalID) {
        this.terminalID = terminalID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
