package com.example.schoolapi.services.dto;

import lombok.Data;

@Data
public class UpdateGradeDTO {
  private Long studentSubjectId;
  private int grade;
}
