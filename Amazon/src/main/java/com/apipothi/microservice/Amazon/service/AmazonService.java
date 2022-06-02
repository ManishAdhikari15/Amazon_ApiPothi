package com.apipothi.microservice.Amazon.service;

import com.apipothi.microservice.Amazon.model.AmazonProduct;
import com.apipothi.microservice.Amazon.model.WarehouseProduct;
import com.apipothi.microservice.Amazon.repository.AmazonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmazonService {

    @Autowired
    public AmazonRepo dao;

    public List<AmazonProduct> saveAll(List<AmazonProduct> allList){

        List<AmazonProduct> amazonProducts = dao.saveAll(allList);
        return amazonProducts;

    }
}
