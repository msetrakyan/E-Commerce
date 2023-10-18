package com.smartcode.ecommerce.model.roles;


import com.smartcode.ecommerce.util.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleEnum role;

}