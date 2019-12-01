package com.example.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class DrugModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String drug_idd;
    private String component;
    private String property;
    private String indication;
    private String use_amount;
    private String use_time;
    private String contraindication;
    private String side_effects;

    public DrugModel(){

    }

    public DrugModel(String name, String drug_idd, String component, String property, String indication, String use_amount, String use_time, String contraindication, String side_effects) {
        this.name = name;
        this.drug_idd = drug_idd;
        this.component = component;
        this.property = property;
        this.indication = indication;
        this.use_amount = use_amount;
        this.use_time = use_time;
        this.contraindication = contraindication;
        this.side_effects = side_effects;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrug_idd(String drug_idd) {
        this.drug_idd = drug_idd;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public void setUse_amount(String use_amount) {
        this.use_amount = use_amount;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDrug_idd() {
        return drug_idd;
    }

    public String getComponent() {
        return component;
    }

    public String getProperty() {
        return property;
    }

    public String getIndication() {
        return indication;
    }

    public String getUse_amount() {
        return use_amount;
    }

    public String getUse_time() {
        return use_time;
    }

    public String getContraindication() {
        return contraindication;
    }

    public String getSide_effects() {
        return side_effects;
    }
}
