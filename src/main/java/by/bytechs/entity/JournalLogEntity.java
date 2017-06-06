package by.bytechs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="TerminalLog")
public class JournalLogEntity implements Serializable {
    @Id
    @Column
    private Long id;
    @Column(name = "message_id")
    private int messageID;
    @Column(name = "atm")
    private String terminalID;
    @Column(name = "termdate")
    private Date terminalDate;
    @Column(name = "original_mess")
    private String message;
    @Column(name = "timestamp")
    private Date serverDate;
    @Column(name = "journal_msg_id")
    private int journalMessageID;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getMessageID() { return messageID; }
    public void setMessageID(int messageID) { this.messageID = messageID; }

    public String getTerminalID() { return terminalID; }
    public void setTerminalID(String terminalID) { this.terminalID = terminalID; }

    public Date getTerminalDate() { return terminalDate; }
    public void setTerminalDate(Date terminalDate) { this.terminalDate = terminalDate; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getServerDate() { return serverDate; }
    public void setServerDate(Date serverDate) { this.serverDate = serverDate; }

    public int getJournalMessageID() { return journalMessageID; }
    public void setJournalMessageID(int journalMessageID) { this.journalMessageID = journalMessageID; }
}

