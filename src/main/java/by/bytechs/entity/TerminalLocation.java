package by.bytechs.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Romanovich Andrei
 */
@Entity
@Table(name = "TerminalLocation")
public class TerminalLocation implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String city;
    @Column
    private String region;
    @Column
    private String area;

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
