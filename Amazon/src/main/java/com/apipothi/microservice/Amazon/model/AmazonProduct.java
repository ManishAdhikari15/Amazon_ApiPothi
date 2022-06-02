package com.apipothi.microservice.Amazon.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "amazon_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AmazonProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String amazonProductId;
    private String amazonProductName;
    private double amazonProductPrice;
}
