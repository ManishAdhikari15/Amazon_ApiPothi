package com.apipothi.microservice.Amazon.repository;

import com.apipothi.microservice.Amazon.model.AmazonProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazonRepo extends JpaRepository<AmazonProduct,Integer> {
}
