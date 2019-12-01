package com.example.demo.repository;

import com.example.demo.model.DiseaseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends CrudRepository<DiseaseModel,String> {
    DiseaseModel findByName(String name);
}
