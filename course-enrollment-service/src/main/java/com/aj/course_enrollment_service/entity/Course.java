package com.aj.course_enrollment_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Duration cannot be empty")
    @Pattern(regexp = "^[0-9]", message = "Duration should be a number in terms of days")
    private Integer duration;
    private String author;

}
