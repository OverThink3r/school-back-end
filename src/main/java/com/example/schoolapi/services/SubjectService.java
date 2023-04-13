package com.example.schoolapi.services;

import com.example.schoolapi.models.StudentSubject;
import com.example.schoolapi.models.SubjectModel;
import com.example.schoolapi.models.SubjectModel;
import com.example.schoolapi.repositories.StudentSubjectRepository;
import com.example.schoolapi.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService {
  
  @Autowired
  SubjectRepository subjectRepository;

  @Autowired
  StudentSubjectRepository ssRepository;

  public SubjectModel saveSubject(SubjectModel subject){
    return subjectRepository.save(subject);
  }

  public SubjectModel getSubjectById(Long subjectId){
    return subjectRepository.findById(subjectId).orElse(null);
  }

  public ArrayList<SubjectModel> getAllSubjects(){
    return (ArrayList<SubjectModel>) subjectRepository.findAll();
  }

  public SubjectModel updateSubject(SubjectModel subjectUpdated, Long subjectId){
    SubjectModel currentSubject = subjectRepository.findById(subjectId).orElse(null);
    if(currentSubject == null){
      return null;
    }
    currentSubject.setName(subjectUpdated.getName());
    currentSubject.setDescription(subjectUpdated.getDescription());
    return subjectRepository.save(currentSubject);
  }

  public void deleteSubject(Long idsubject){
    ArrayList<StudentSubject> relations = ssRepository.findAllBySubject_Id(idsubject);
    ssRepository.deleteAll(relations);
    subjectRepository.deleteById(idsubject);
  }
}
