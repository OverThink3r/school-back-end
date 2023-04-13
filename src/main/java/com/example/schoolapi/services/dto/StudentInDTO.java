package com.example.schoolapi.services.dto;

import com.example.schoolapi.models.StudentModel;
import lombok.Data;

@Data
public class StudentInDTO {
  private StudentModel student;
  private Long studentId;
}
