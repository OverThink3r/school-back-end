package com.example.schoolapi.controllers;

import com.example.schoolapi.models.StudentModel;
import com.example.schoolapi.models.StudentSubject;
import com.example.schoolapi.services.StudentService;
import com.example.schoolapi.services.dto.SubjectStudentDTO;
import com.example.schoolapi.services.dto.UpdateGradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/student")
public class StudentController {
  /*
   * BASE URL: http://localhost:8080/api/student
   */

  @Autowired
  StudentService studentService;

  @GetMapping()
  public ArrayList<StudentModel> getAllStudents(){
    return studentService.getAllStudents();
  }

  @GetMapping("/{id}")
  public StudentModel getStudentById(@PathVariable("id") Long id){
    return studentService.getStudentById(id);
  }

  @PostMapping()
  public StudentModel saveStudent(@RequestBody StudentModel student ){
    return studentService.saveStudent(student);
  }

  @PutMapping("/{id}")
  public StudentModel updateStudent(@RequestBody StudentModel student, @PathVariable("id") Long id){
    return studentService.updateStudent(student, id);
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable("id") Long studentId){
    studentService.deleteStudent(studentId);
  }

  @PostMapping("/{id}/addSubjects")
  public void attachSubjects(@PathVariable("id") Long studentId, @RequestBody ArrayList<Long> subjectIds){
    studentService.attachSubjects(studentId, subjectIds);
  }

  @PostMapping("/{studentId}/addSubject")
  public void attachSubject(@PathVariable("studentId") Long studentId, @RequestBody SubjectStudentDTO dto){
    studentService.attachSubject(studentId, dto.getSubjectId(), dto.getGrade());
  }

  @GetMapping("/{id}/getSubjects")
  public ArrayList<StudentSubject> getSubjectsByStudent(@PathVariable("id") Long id){
    return studentService.getSubjectsByStudent(id);
  }

  @PatchMapping("/updateGrade")
  public void updateGrade(@RequestBody UpdateGradeDTO dto){
    studentService.rateSubject(dto.getGrade(), dto.getStudentSubjectId());
  }

  @DeleteMapping("/deleteStudentSubject/{studentSubjectId}")
  public void deleteStudentSubject(@PathVariable("studentSubjectId") Long studentSubjectId){
    studentService.deleteStudentSubject(studentSubjectId);
  }
}
