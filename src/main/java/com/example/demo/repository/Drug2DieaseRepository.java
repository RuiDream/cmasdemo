package com.example.demo.repository;

import com.example.demo.model.DrugModel;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface Drug2DieaseRepository extends Neo4jRepository<DrugModel,String> {
   DrugModel findByName(@Param("name") String name);
}
