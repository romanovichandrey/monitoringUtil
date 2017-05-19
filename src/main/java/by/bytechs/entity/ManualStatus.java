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
@Table(name="ManualStatus")
public class ManualStatus implements Serializable {
    @Id
    @Column(name="idStatus")
    private Integer status;
    @Column(name="Descr")
    private String Descr;
    @Column(name="isOk")
    private Integer isOk;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean ok(){
        if (isOk==null) return false;
        if (!isOk.equals(1)) return false;
        return true;
    }



    @Override
    public String toString() {
        return Descr;
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
        result = prime * result + status;
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
        if (!(obj instanceof ManualStatus)) {
            return false;
        }
        ManualStatus other = (ManualStatus) obj;
        if (!status.equals(other.status)) {
            return false;
        }
        return true;
    }

    public Integer getIsOk() {
        return isOk;
    }

    public void setIsOk(Integer isOk) {
        this.isOk = isOk;
    }

    public String getDescr() {
        return Descr;
    }

    public void setDescr(String descr) {
        Descr = descr;
    }

}
