package com.example.demo.model;


import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type="cure")
public class CureRelation {
    @Id
    private Long id;

    public CureRelation(Long id, DiseaseModel startNode, DrugModel endNode) {
        this.id = id;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    /**
     * 定义关系的起始节点 == StartNode
     */

    @StartNode
    private DiseaseModel startNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 定义关系的终止节点 == EndNode
     */

    @EndNode
    private DrugModel endNode;

    public DiseaseModel getStartNode() {
        return startNode;
    }

    public void setStartNode(DiseaseModel startNode) {
        this.startNode = startNode;
    }

    public DrugModel getEndNode() {
        return endNode;
    }

    public void setEndNode(DrugModel endNode) {
        this.endNode = endNode;
    }
}
