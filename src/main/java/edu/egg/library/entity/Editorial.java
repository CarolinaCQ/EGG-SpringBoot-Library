
package edu.egg.library.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;


@Entity
@Table(name="editorial")
@SQLDelete(sql="UPDATE editorial SET low_editorial=true WHERE id_editorial=?")
public class Editorial implements Serializable {
    @Id
    @Column(name="id_editorial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name_editorial", length = 150, nullable = false)
    private String name;
    
    @Column(name="low_editorial", nullable = false)
    private Boolean low;

    public Editorial() {
    }

    public Editorial(Long id, String name, Boolean low) {
        this.id = id;
        this.name = name;
        this.low = low;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLow() {
        return low;
    }

    public void setLow(Boolean low) {
        this.low = low;
    }
    
    
}
