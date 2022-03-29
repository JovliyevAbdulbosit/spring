package com.security.jwtlearning.domen;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class UserTable  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fristName;
    private String lastName;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role" , joinColumns = {@JoinColumn(name="user_id" , referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id" , referencedColumnName = "role_name")})
    private Set<RoleUserEntity> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleUserEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleUserEntity> roles) {
        this.roles = roles;
    }
}

