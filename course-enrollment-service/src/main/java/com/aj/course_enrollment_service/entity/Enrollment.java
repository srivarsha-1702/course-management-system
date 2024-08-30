package com.aj.course_enrollment_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enrollment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer e_id;
    private Integer emp_id;
    @NotEmpty(message = "Employee id cannot be empty")
    @Column(name = "course_id")
    private Integer course_id;
    @NotEmpty(message = "Course id cannot be empty")
    private Boolean status;
}
