package com.hung.albumphoto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
}
