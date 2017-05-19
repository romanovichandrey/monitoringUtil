package by.bytechs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Romanovich Andrei
 */
@Entity
@Table(name="TerminalAdvance")
public class TerminalAdvancedSettings implements Serializable {
    @Column(name="id")
    @Id
    private String terminalId;
    @Column
    private double longitude;
    @Column
    private double latitude;
    @Column
    private int rampant;
    @Column(name = "voice_alert")
    private int voiceAlert;
    @Column
    private int braille;
    @Column
    private String comment;
    @Column(name = "only_for_personal")
    private boolean onlyForPersonal;
    @Column(name = "rebootPeriod")
    private Integer rebootPeriod;

    public String getTerminalId() {
        return terminalId;
    }
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getRampant() {
        return rampant;
    }
    public void setRampant(int rampant) {
        this.rampant = rampant;
    }

    public int getVoiceAlert() {
        return voiceAlert;
    }
    public void setVoiceAlert(int voiceAlert) {
        this.voiceAlert = voiceAlert;
    }

    public int getBraille() {
        return braille;
    }
    public void setBraille(int braille) {
        this.braille = braille;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isOnlyForPersonal() {
        return onlyForPersonal;
    }
    public void setOnlyForPersonal(boolean onlyForPersonal) {
        this.onlyForPersonal = onlyForPersonal;
    }

    @Override
    public String toString() {
        return "Terminal: " + getTerminalId() + " [longitude: " + getLongitude() + "; latitude" + getLatitude() + (getRampant() == 1 ? "; rampant is present" :
                "; rampant is not present") + (getVoiceAlert() == 1 ? "; voice alert is present" : "; voice alert is not present") + (getBraille() == 1 ?
                "; braille is present]" : "; braille is not present]");
    }

    public Integer getRebootPeriod() {
        return rebootPeriod;
    }

    public void setRebootPeriod(Integer rebootPeriod) {
        this.rebootPeriod = rebootPeriod;
    }
}
