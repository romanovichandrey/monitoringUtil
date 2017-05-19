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
@Table(name="currency")
public class Currency implements Serializable {
    @Column
    @Id
    private String alfa3;
    @Column
    private String num3;

    public String getAlfa3() {
        return alfa3;
    }
    public void setAlfa3(String alfa3) {
        this.alfa3 = alfa3;
    }
    public String getNum3() {
        return num3;
    }
    public void setNum3(String num3) {
        this.num3 = num3;
    }

}
