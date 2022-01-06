package br.com.smtech.controlegastos.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.smtech.controlegastos.api.dto.PersonDTO;
import br.com.smtech.controlegastos.library.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person extends Model {

    @Column(nullable = false)
    private Date birth;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String state;

    @Column(length = 100)
    private String countrie;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private User user;

    public Person(Long id) {
        this.id = id;
    }

    @Builder(builderMethodName = "builder")
    public Person(Long id, Date birth, String email, String name, String cpf, String city, String state,
            String countrie, User user, Address address) {
        this.id = id;
        this.birth = birth;
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.city = city;
        this.state = state;
        this.countrie = countrie;
        this.user = user;
        this.address = address;
    }

    public static Person create(PersonDTO object) {
        return new ModelMapper().map(object, Person.class);
    }

    public Person(String email, String name, String cpf, Date birth) {
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
    }

    @Override
    protected void prePersistAndUpdate() {
        super.prePersistAndUpdate();
    }
}
