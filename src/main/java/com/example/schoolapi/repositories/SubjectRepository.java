package com.example.schoolapi.repositories;

import com.example.schoolapi.models.SubjectModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectModel, Long> {
}
