package br.com.smtech.controlegastos.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "date_registration", nullable = false, updatable = false)
    protected Date dateRegistration;

    @Column(name = "date_update", nullable = false)
    protected Date dateUpdate;

    public Model(Long id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        dateRegistration = new Date();
        prePersistAndUpdate();
    }

    @PreUpdate
    public void preUpdate() {
        prePersistAndUpdate();
    }

    protected void prePersistAndUpdate() {
        dateUpdate = dateUpdate == null ? new Date() : dateUpdate;
    }
}
