package com.wangquocthai.java4.lab4.user.entity;

import com.wangquocthai.java4.lab4.favorite.entity.Favorite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByIdOrEmail", query = "SELECT u FROM User u WHERE u.id = :username OR u.email = :username")
})
public class User {
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Password")
    private String password;
    @Column(name = "Fullname")
    private String fullname;
    @Column(name = "Email")
    private String email;
    @Column(name = "Admin")
    private Boolean admin = false;
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;
}
