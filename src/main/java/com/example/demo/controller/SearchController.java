package com.example.demo.controller;

import com.example.demo.bean.JsonSimple;
import com.example.demo.bean.SubmitSearchRequest;
import com.example.demo.model.DiseaseModel;
import com.example.demo.model.DrugModel;
import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.DrugRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping("/search")
public class SearchController {
   //@Autowired
    //private VideoService videoService;


    @Autowired
    DrugRepository drugRepo;

    @Autowired
    DiseaseRepository dieaseRepo;


    @GetMapping("/getvideo")
    @ResponseBody
    public String getVideo(HttpServletRequest request) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Long uid = (Long)request.getSession().getAttribute("user_id");
        return "true";
    }

    @PostMapping(value = "/submitsearch")
    @ResponseBody
    public String submitLable(HttpServletRequest request, @RequestBody SubmitSearchRequest slr) throws Exception{
        System.out.println("开始查询");
        String name = slr.getName();
        ObjectMapper mapper = new ObjectMapper();
        Long uid = (Long) request.getSession().getAttribute("user_id");
        System.out.println(name);
        DrugModel drug = drugRepo.findByName(name);
        if(drug == null){
            DiseaseModel dis = dieaseRepo.findByName(name);
            return JsonSimple.toJson(dis);
        }
        System.out.println(JsonSimple.toJson(drug));
        return JsonSimple.toJson(drug);
        //return "hello";
    }

}
