package by.bytechs.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Romanovich Andrei
 */
@Entity
@Table(name="TerminalCbu")
public class TerminalCbu {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String cbu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "terminalCbu")
    private List<Terminal> terminals;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCbu() {
        return cbu;
    }
    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }
    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    public List<Terminal> getATMs() {
        List<Terminal> allTerminals = getTerminals();
        List<Terminal> atmList = new ArrayList<>();
        for (Terminal terminal : allTerminals) {
            if (terminal.getMarka().getType() == TerminalType.BANKOMAT) {
                atmList.add(terminal);
            }
        }
        return atmList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerminalCbu that = (TerminalCbu) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(cbu != null ? !cbu.equals(that.cbu) : that.cbu != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cbu != null ? cbu.hashCode() : 0);
        return result;
    }
}
