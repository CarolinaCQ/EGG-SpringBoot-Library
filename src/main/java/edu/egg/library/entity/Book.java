
package edu.egg.library.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name="book")
@SQLDelete(sql="UPDATE book SET low_book = true WHERE id_book=?")
public class Book implements Serializable {
    
    @Id
    @Column(name="id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "isbn_book", nullable = false, unique = true)
    private String isbn;

    @Column(name = "title_book", length = 150, nullable = false)
    private String title;

    @Column(name = "date_book", columnDefinition="year", nullable = false)
    private Integer year;

    @Column(name = "copies_book")
    private Integer copies;

    @Column(name = "borrowed_copies_book")
    private Integer borrowedCopies;

    @Column(name = "remaining_copies_book")
    private Integer remainingCopies;

    @Column(name = "low_book")
    private Boolean low;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_author", referencedColumnName = "id_author", nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_editorial", referencedColumnName = "id_editorial", nullable = false)
    private Editorial editorial;

    public Book() {
    }

    public Book(Long id, String isbn, String title, Integer year, Integer copies, Integer borrowedCopies, Integer remainingCopies, Boolean low, Author author, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.copies = copies;
        this.borrowedCopies = borrowedCopies;
        this.remainingCopies = remainingCopies;
        this.low = low;
        this.author = author;
        this.editorial = editorial;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getBorrowedCopies() {
        return borrowedCopies;
    }

    public void setBorrowedCopies(Integer borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    public Integer getRemainingCopies() {
        return remainingCopies = copies - borrowedCopies;
    }

    public void setRemainingCopies(Integer remainingCopies) {
        this.remainingCopies = remainingCopies;
    }

    public Boolean getLow() {
        return low;
    }

    public void setLow(Boolean low) {
        this.low = low;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    
    
}
