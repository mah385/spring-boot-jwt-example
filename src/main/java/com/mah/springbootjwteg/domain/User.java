package com.mah.springbootjwteg.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)", updatable = false)
    private String id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

}
