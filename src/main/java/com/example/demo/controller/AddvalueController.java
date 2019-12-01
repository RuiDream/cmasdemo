package com.example.demo.controller;

import com.example.demo.bean.JsonSimple;
import com.example.demo.bean.SubmitSearchRequest;
import com.example.demo.bean.SubmitaddRequest;
import com.example.demo.model.DiseaseModel;
import com.example.demo.model.DrugModel;
import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.DrugRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/add")
public class AddvalueController {

    @Autowired
    DrugRepository drugRepo;

    @Autowired
    DiseaseRepository dieaseRepo;
    @PostMapping(value = "/addrelation")
    @ResponseBody
    public String submitadd(HttpServletRequest request, @RequestBody SubmitaddRequest slr) throws Exception{
        System.out.println("开始增加");
        String label1 = slr.getLabel1();
        String label2 = slr.getLabel2();
        String relation = slr.getRelation();
        String entity1 = slr.getEntity1();
        String entity2 = slr.getEntity2();
        ObjectMapper mapper = new ObjectMapper();
        Long uid = (Long) request.getSession().getAttribute("user_id");
        checkLabel(label1, entity1);
        checkLabel(label2, entity2);
        DiseaseModel dis = dieaseRepo.findByName(entity2);
        DrugModel drug = drugRepo.findByName(entity1);
        System.out.println(JsonSimple.toJson(drug));
        System.out.println(JsonSimple.toJson(dis));
        return "true";
//        drug.addCure(dis,new Random().nextLong());
//        dieaseRepo.save(dis);
//        drugRepo.save(drug);

//        DrugModel drug = drugRepo.findByName(entity1);
//        if(drug == null){
//            DiseaseModel dis = dieaseRepo.findByName(entity1);
//            return JsonSimple.toJson(dis);
//        }
//        System.out.println(JsonSimple.toJson(drug));
        //return JsonSimple.toJson(drug);
        //return "true";
    }

    private void checkLabel(String label2, String entity2) {
        if(label2=="药物"&&drugRepo.findByName(entity2)==null){
            //创建一个新药物节点
            DrugModel drug2 = new DrugModel(entity2,"none","none","none","none","none","none","none","none");

        }
        if(label2=="疾病"&&drugRepo.findByName(entity2)==null){
            //创建一个疾病节点
            DiseaseModel diseaseModel = new DiseaseModel(entity2,"none","none");
        }
    }

}
