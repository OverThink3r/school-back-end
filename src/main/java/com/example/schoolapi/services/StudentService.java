package com.example.schoolapi.services;

import com.example.schoolapi.models.StudentModel;
import com.example.schoolapi.models.StudentSubject;
import com.example.schoolapi.models.SubjectModel;
import com.example.schoolapi.repositories.StudentRepository;
import com.example.schoolapi.repositories.StudentSubjectRepository;
import com.example.schoolapi.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  SubjectRepository subjectRepository;

  @Autowired
  StudentSubjectRepository ssRepository;

  public StudentModel saveStudent(StudentModel student){
    student.setActive(true);
    return studentRepository.save(student);
  }

  public StudentModel getStudentById(Long studentId){
    return studentRepository.findById(studentId).orElse(null);
  }

  public ArrayList<StudentModel> getAllStudents(){
    return (ArrayList<StudentModel>) studentRepository.findAll();
  }

  public StudentModel updateStudent(StudentModel studentUpdated, Long studentId){
    StudentModel currentStudent = studentRepository.findById(studentId).orElse(null);
    if(currentStudent == null){
      return null;
    }
    currentStudent.setActive(studentUpdated.isActive());
    currentStudent.setEmail(studentUpdated.getEmail());
    currentStudent.setLastname(studentUpdated.getLastname());
    currentStudent.setFirstname(studentUpdated.getFirstname());
    currentStudent.setDateOfBirth(studentUpdated.getDateOfBirth());
    return studentRepository.save(currentStudent);
  }

  public void deleteStudent(Long idStudent){
    ArrayList<StudentSubject> relations = ssRepository.findAllByStudent_Id(idStudent);
    ssRepository.deleteAll(relations);
    studentRepository.deleteById(idStudent);
  }

  public void attachSubjects(Long studentId, List<Long> subjectIds) {
    StudentModel currentStudent = studentRepository.findById(studentId).orElse(null);
    if(currentStudent != null) {
      for(Long subjectId: subjectIds){
        SubjectModel subjectToAttach = subjectRepository.findById(subjectId).orElse(null);
//        currentStudent.getSubjects().add(subjectToAttach);
//        studentRepository.save(currentStudent);
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setStudent(currentStudent);
        studentSubject.setSubject(subjectToAttach);
        ssRepository.save(studentSubject);
      }

    }
  }

  public ArrayList<StudentSubject> getSubjectsByStudent(Long studentId){
    return ssRepository.findAllByStudent_Id(studentId);
  }

  public void rateSubject(int grade, Long studentSubjectId){
    StudentSubject studentSubject = ssRepository.findById(studentSubjectId).orElse(null);
    if(studentSubject != null){
      studentSubject.setGrade(grade);
      ssRepository.save(studentSubject);
    }
  }

  public void attachSubject(Long studentId, Long subjectId, Integer grade) {
    StudentModel currentUser = studentRepository.findById(studentId).orElse(null);
    SubjectModel currentSubject = subjectRepository.findById(subjectId).orElse(null);

    if(currentUser != null && currentSubject != null){
      StudentSubject studentSubject = new StudentSubject();
      studentSubject.setStudent(currentUser);
      studentSubject.setSubject(currentSubject);
      studentSubject.setGrade(grade);
      ssRepository.save(studentSubject);
    }
  }

  public void deleteStudentSubject(Long studentSubjectId){
    ssRepository.deleteById(studentSubjectId);
  }
}
