package com.example.demo.controller;

import com.example.demo.bean.JsonSimple;
import com.example.demo.bean.SubmitSearchRequest;
import com.example.demo.model.PatientModel;
import com.example.demo.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/find")
public class FindPatientController {
    @Autowired
    PatientRepository patientRepo;


    @PostMapping(value = "/PatientInfo")
    @ResponseBody
    public String findPatientInfo(HttpServletRequest request, @RequestBody SubmitSearchRequest slr) throws Exception{
        System.out.println("开始查询病人信息");
        String name = slr.getName();
        ObjectMapper mapper = new ObjectMapper();
        Long uid = (Long) request.getSession().getAttribute("user_id");
        System.out.println(name);
        List<PatientModel> patient = (List<PatientModel>) patientRepo.findAll();
        System.out.println(JsonSimple.toJson(patient));
        return JsonSimple.toJson(patient);
    }

}
