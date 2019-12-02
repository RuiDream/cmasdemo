package com.example.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class PatientModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String sex;
    private String age;
    private String apartment;
    private String past_history;
    private String family_history;
    private String allergy_history;
    private String disease_reason;
    private String disease_result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPast_history() {
        return past_history;
    }

    public void setPast_history(String past_history) {
        this.past_history = past_history;
    }

    public String getFamily_history() {
        return family_history;
    }

    public void setFamily_history(String family_history) {
        this.family_history = family_history;
    }

    public String getAllergy_history() {
        return allergy_history;
    }

    public void setAllergy_history(String allergy_history) {
        this.allergy_history = allergy_history;
    }

    public String getDisease_reason() {
        return disease_reason;
    }

    public void setDisease_reason(String disease_reason) {
        this.disease_reason = disease_reason;
    }

    public String getDisease_result() {
        return disease_result;
    }

    public void setDisease_result(String disease_result) {
        this.disease_result = disease_result;
    }
}
