package by.bytechs.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Romanovich Andrei
 */
@Entity
@Table(name = "DeviceType")
public class Marka implements Serializable {
    @Column(name = "DeviceType")
    private String marka;
    @Column(name = "type")
    @Enumerated
    private TerminalType type;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idMarka;
    @Column(name = "cassette_capacity")
    private int cassetteCapacity;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public TerminalType getType() {
        return type;
    }

    public void setType(TerminalType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return marka;
    }

    public int getIdMarka() {
        return idMarka;
    }

    public void setIdMarka(int idMarka) {
        this.idMarka = idMarka;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idMarka;
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Marka)) {
            return false;
        }
        Marka other = (Marka) obj;
        if (idMarka != other.idMarka) {
            return false;
        }
        return true;
    }

    public int getCassetteCapacity() {
        return cassetteCapacity;
    }

    public void setCassetteCapacity(int cassetteCapacity) {
        this.cassetteCapacity = cassetteCapacity;
    }
}
