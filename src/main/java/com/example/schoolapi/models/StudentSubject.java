package com.example.schoolapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StudentSubject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @ManyToOne(cascade = CascadeType.REMOVE)
  @ManyToOne()
  @JoinColumn(name = "student_id")
  private StudentModel student;

//  @ManyToOne(cascade = CascadeType.REMOVE)
  @ManyToOne()
  @JoinColumn(name = "subject_id")
  private SubjectModel subject;

  @Column(nullable = true, columnDefinition = "INTEGER NULL")
  private Integer grade;
}

