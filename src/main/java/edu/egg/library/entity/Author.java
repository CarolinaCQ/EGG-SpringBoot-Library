
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
@Table(name="author")
@SQLDelete(sql="UPDATE author SET low_author=true WHERE id_author=?")
public class Author implements Serializable {
    @Id
    @Column(name="id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name_author", length = 150, nullable = false)
    private String name;
    
    @Column(name="surname_author", length = 150, nullable = false)
    private String surname;
    
    @Column(name="low_author", columnDefinition="bool", nullable = false)
    private Boolean low;

    public Author() {
    }

    public Author(Long id, String name, String surname, Boolean low) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getLow() {
        return low;
    }

    public void setLow(Boolean low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", high=" + low + '}';
    }
    
    
    
}

