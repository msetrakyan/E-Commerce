package com.smartcode.ecommerce.model.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users")
@Component
@NoArgsConstructor
public class UserEntity {


    public UserEntity(String name, String lastname, Integer age, String username, String password, String email) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private Boolean isVerified;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String code;


}