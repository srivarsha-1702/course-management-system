package com.aj.employee.service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty (message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;
    @NotEmpty (message = "Mobile number cannot be empty")
    @NotNull(message = "Mobile number cannot be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String mobileNumber;
    private String designation;
    private String department;


}
