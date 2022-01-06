package br.com.smtech.controlegastos.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.smtech.controlegastos.api.dto.UserDTO;
import br.com.smtech.controlegastos.api.dto.UserListDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Suleiman Moraes
 *
 */
@SqlResultSetMapping(name = UserListDTO.USER_LIST_DTO_MAPPING, classes = {
        @ConstructorResult(targetClass = UserListDTO.class, columns = {
                @ColumnResult(name = "id", type = Long.class),
                @ColumnResult(name = "name", type = String.class),
                @ColumnResult(name = "username", type = String.class),
                @ColumnResult(name = "cpf", type = String.class),
                @ColumnResult(name = "enabled", type = Boolean.class) }) })
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
// TODO
// public class User implements IUser {
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 150)
    private String password;

    private boolean enabled = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    private Person person;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
            @JoinColumn(name = "id_permission") })
    private List<Permission> authorities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Month> months;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categorys;

    public User(Long id) {
        this.id = id;
    }

    @Builder(builderMethodName = "builder")
    public User(Long id, String username, String password, Person person, List<Permission> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.person = person;
        this.authorities = authorities;
        this.enabled = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.accountNonExpired = true;
    }

    public static User create(UserDTO object) {
        return new ModelMapper().map(object, User.class);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
