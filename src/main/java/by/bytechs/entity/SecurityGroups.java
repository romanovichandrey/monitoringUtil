package by.bytechs.entity;

import javax.persistence.*;

/**
 * @author Romanovich Andrei
 */
@Entity
@Table(name="SecurityGroups")
public class SecurityGroups {
    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String securityGroups;
    @Column
    private String city;
    public String getSecurityGroups() {
        return securityGroups;
    }
    public void setSecurityGroups(String securityGroups) {
        this.securityGroups = securityGroups;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SecurityGroups)) {
            return false;
        }
        SecurityGroups other = (SecurityGroups) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
