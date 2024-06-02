package com.example.demo.service;

import com.example.demo.entity.Individual;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndividualService {
    List<Individual> selectIndividualsById(int userId);

    void createIndividual(String identity,String phoneNum, String name, int userId);
}
