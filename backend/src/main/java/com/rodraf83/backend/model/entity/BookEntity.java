package com.rodraf83.backend.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "tittle", length = 160)
    @NotBlank(message = "{tittle.field.required}")
    private String tittle;

    @Column(name = "author", length = 160)
    @NotBlank(message = "{author.field.required}")
    private String author;

    @Column(name = "createdby", length = 60)
    private String createdBy;

    @Column(name = "createdat", updatable = false)
    private LocalDate createdAt; 

    @PrePersist
    public void prePersist() {       
        setCreatedBy("admin");
        setCreatedAt(LocalDate.now());
    }

}
