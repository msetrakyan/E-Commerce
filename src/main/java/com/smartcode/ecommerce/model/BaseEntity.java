package com.smartcode.ecommerce.model;


import com.smartcode.ecommerce.util.CurrentUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column
    private Integer createdBy;

    @Column
    private Integer updatedBy;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        Integer id = CurrentUser.getId();
        if(id != null) {
            createdBy = id;
            updatedBy = id;
        }
    }



}
