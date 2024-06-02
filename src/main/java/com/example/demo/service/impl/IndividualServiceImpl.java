package com.example.demo.service.impl;

import com.example.demo.dao.IndividualMapper;
import com.example.demo.entity.Individual;
import com.example.demo.service.IndividualService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("individualService")
public class IndividualServiceImpl implements IndividualService {
    @Resource
    private IndividualMapper individualMapper;
    @Override
    public List<Individual> selectIndividualsById(int userId) {
        return individualMapper.selectIndividualsById(userId);
    }

    @Override
    public void createIndividual(String identity, String phoneNum, String name, int userId) {
        individualMapper.createIndividual(identity, phoneNum, name, userId);
    }
}
