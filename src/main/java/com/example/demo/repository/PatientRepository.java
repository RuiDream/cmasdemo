package com.example.demo.repository;

import com.example.demo.model.PatientModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<PatientModel,String> {


    PatientModel findByName(String name);

    @Override
    Iterable<PatientModel> findAll();

}
