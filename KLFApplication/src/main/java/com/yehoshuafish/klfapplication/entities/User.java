package com.yehoshuafish.klfapplication.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class represents the User Entity of the database
 * 
 * @author Shuey
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.all", query = "select u from User u order by u.id"),
    @NamedQuery(name = "User.byUsername", query = "select u from User u where u.username = :username"),
    @NamedQuery(name = "User.editNameByUsername", query = "update User set name = :name where username = :username"),
    @NamedQuery(name = "User.deleteByUsername", query = "delete from User where username = :username"),
})
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name", nullable = true)
    private String name;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "hash", nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy = "user")
    Set<UserActivity> actions;
    
    public User() {
    }

    public User(String name, String username, String passwordHash) {
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
