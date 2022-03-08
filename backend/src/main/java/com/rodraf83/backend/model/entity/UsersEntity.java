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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", length = 60)
    @NotBlank(message = "{username.field.required}")
    private String username;

    @Column(name = "email", length = 30)
    @NotBlank(message = "{email.field.required}")
    @Email(message = "{email.not.valid}")
    private String email;

    @Column(name = "password", length = 15)
    @NotBlank(message = "{password.field.required}")
    private String password;

    @Column(name = "createdat", updatable = false)
    private LocalDate createdAt;

    @PrePersist
    private void prePersist() {
        setCreatedAt(LocalDate.now());
    }

}
