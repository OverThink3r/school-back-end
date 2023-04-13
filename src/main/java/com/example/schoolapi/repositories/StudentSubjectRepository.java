package com.example.schoolapi.repositories;

import com.example.schoolapi.models.StudentSubject;
import com.example.schoolapi.models.SubjectModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public interface StudentSubjectRepository extends CrudRepository<StudentSubject, Long> {

  ArrayList<StudentSubject> findAllByStudent_Id(Long studentId);
  ArrayList<StudentSubject> findAllBySubject_Id(Long subjectId);



//  @Query("SELECT ss.subject, ss.grade from StudentSubject ss WHERE ss.student.id = :studentId")
//  ArrayList<HashMap<SubjectModel, Integer>> getAllSubjectsByStudentId(@Param("studentId") Long studentId);
}
