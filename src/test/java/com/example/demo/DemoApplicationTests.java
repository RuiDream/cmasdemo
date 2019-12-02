package com.example.demo;

import com.example.demo.model.DiseaseModel;
import com.example.demo.model.DrugModel;
import com.example.demo.model.PatientModel;
import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.DrugRepository;
import com.example.demo.repository.PatientRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    DrugRepository drugRepo;

    @Autowired
    DiseaseRepository dieaRepo;

    @Autowired
    PatientRepository patiRepo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testfindByName(){
        DrugModel drug = drugRepo.findByName("心理治疗");
        System.out.println(drug);
        System.out.println(JsonSimple.toJson(drug));
//        DiseaseModel dis = dieaRepo.findByName("抑郁发作");
//        System.out.println(dis);
    }

    @Test
    public void testsaveDrug(){
        DrugModel drug = new DrugModel("3","2","3","4","1","2","3","4","5");
        DrugModel d = drugRepo.save(drug);
        if(d==null)
            System.out.println("创建失败");
        System.out.println(JsonSimple.toJson(d));

    }

    @Test
    public void testFindByLabel(){
        List<PatientModel> p = (List<PatientModel>) patiRepo.findAll();
        System.out.println(p);
    }

}
