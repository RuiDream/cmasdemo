package com.example.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class DiseaseModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String disease_id;
    private String disease_concept;

    public DiseaseModel(){

    }
    public DiseaseModel(String name,String disease_id,String disease_concept){
        this.name = name;
        this.disease_id = disease_id;
        this.disease_concept=disease_concept;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisease_id(String disease_id) {
        this.disease_id = disease_id;
    }

    public void setDisease_concept(String disease_concept) {
        this.disease_concept = disease_concept;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisease_id() {
        return disease_id;
    }

    public String getDisease_concept() {
        return disease_concept;
    }
}
