package com.example.demo.controller;

import com.example.demo.entity.Individual;
import com.example.demo.entity.R;
import com.example.demo.service.IndividualService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.example.demo.utils.Md5Utils.agenerate;

@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value ="/individual")
public class IndividualController {
    @Autowired
    private IndividualService individualService;
    @RequestMapping(value="/select/userId")
    public List<Individual> selectIndividualsByUserId(@RequestParam("userId")int userId) {
        try{
            return individualService.selectIndividualsById(userId);
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
    @RequestMapping(value="/create")
    public R createIndividual(@RequestBody HashMap<String, String> idMap) {
        try {
            String identity = agenerate(idMap.get("identity"));//对身份证加密
            String phoneNum = idMap.get("phoneNum");
            String name = idMap.get("name");
            int userId = Integer.parseInt(idMap.get("userId"));
            individualService.createIndividual(identity, phoneNum, name, userId);
            return R.ok("create individual success");
        }catch (Exception e){
            return R.error(e.toString());
        }

    }
}
