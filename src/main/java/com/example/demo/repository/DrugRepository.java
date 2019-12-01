package com.example.demo.repository;

import com.example.demo.model.DrugModel;
import org.springframework.data.neo4j.repository.query.GraphRepositoryQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DrugRepository extends CrudRepository<DrugModel,String> {
    DrugModel findByName(String name);

}
