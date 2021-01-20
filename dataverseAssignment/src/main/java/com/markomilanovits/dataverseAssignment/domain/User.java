package com.markomilanovits.dataverseAssignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USERS")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public static final int NAME_MAXLENGTH = 50;
    public static final int PHONE_MAXLENGTH = 14;
    public static final int PASSWORD_MAXLENGTH = 200;
    public static final int PATH_MAXLENGTH = 200;

    @Id
    @Column(name = "userID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName", length = NAME_MAXLENGTH, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = NAME_MAXLENGTH, nullable = false)
    private String lastName;

    @Column(name = "email", length = NAME_MAXLENGTH, nullable = false)
    private String email;

    @Column(name = "phone", length = PHONE_MAXLENGTH, nullable = false)
    private String phone;

    @Column(name = "company", length = NAME_MAXLENGTH, nullable = false)
    private String company;

    @Column(name = "password", length = PASSWORD_MAXLENGTH, nullable = false)
    private String password;

    @Column(name = "photoPath", length = PATH_MAXLENGTH, nullable = true)
    private String photoPath;

}
