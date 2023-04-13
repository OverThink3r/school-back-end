package com.example.schoolapi.controllers;

import com.example.schoolapi.models.SubjectModel;
import com.example.schoolapi.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/subject")
public class SubjectController {

  @Autowired
  SubjectService subjectService;

  @GetMapping()
  public ArrayList<SubjectModel> getAllSubjects(){
    return subjectService.getAllSubjects();
  }

  @GetMapping("/{id}")
  public SubjectModel getSubjectById(@PathVariable("id") Long id){
    return subjectService.getSubjectById(id);
  }

  @PostMapping()
  public SubjectModel saveSubject(@RequestBody SubjectModel subject ){
    return subjectService.saveSubject(subject);
  }

  @PutMapping("/{id}")
  public SubjectModel updateSubject(@RequestBody SubjectModel subject, @PathVariable("id") Long id){
    return subjectService.updateSubject(subject, id);
  }

  @DeleteMapping("/{id}")
  public void deleteSubject(@PathVariable("id") Long subjectId){
    subjectService.deleteSubject(subjectId);
  }
}
