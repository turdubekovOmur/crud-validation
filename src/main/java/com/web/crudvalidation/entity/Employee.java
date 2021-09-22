package com.web.crudvalidation.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last name should have at least 2 characters")
    private String lastName;

    @Email
    @NotBlank
    private String emailId;

    @NotNull
    @Size(min = 2, message = "Passport should have at least 2 characters")
    private String passportNumber;

}
