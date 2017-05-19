package by.bytechs.entity.terminalParameters;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Designed for storing terminal parameters
 * @author Romanovich Andrei
 */
@Entity
@Table
public class TerminalParameters implements Serializable {
    @Id
    @Column(nullable = false)
    private Integer parameterID;
    @Column(nullable = false)
    private String parameterName;
    @OneToMany(mappedBy = "terminalParameters", fetch = FetchType.LAZY)
    private List<DateChangeTerminalParameters> dateChangeTerminalParametersList;
    @OneToMany(mappedBy = "terminalParameters", fetch = FetchType.LAZY)
    private List<DateChangeTerminalParametersHistory> dataChangeTerminalParametersHistories;

    public Integer getParameterID() {
        return parameterID;
    }

    public void setParameterID(Integer parameterID) {
        this.parameterID = parameterID;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
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
