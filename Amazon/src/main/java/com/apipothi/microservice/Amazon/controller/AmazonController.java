package com.apipothi.microservice.Amazon.controller;

import com.apipothi.microservice.Amazon.model.AmazonProduct;
import com.apipothi.microservice.Amazon.model.WarehouseResponse;
import com.apipothi.microservice.Amazon.service.AmazonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class AmazonController {

    @Autowired
    public AmazonService service;

    @Autowired
    public WebClient webClient;
  //  public static List<WarehouseResponse> list=new ArrayList<>();

    @GetMapping("/findAll")
    public List<WarehouseResponse> getAllProducts(){
        List<WarehouseResponse>  list = this.webClient.get().uri("http://localhost:7001/getAll").retrieve().bodyToFlux(WarehouseResponse.class).collectList().block();
        System.out.println("list"+list);

        List<String> collect = list.stream().filter(p -> p.getWproductprice() > 10000).collect(Collectors.mapping(WarehouseResponse::getWproductname, Collectors.toList()));
        System.out.println(collect.stream().collect(Collectors.joining(",")));
       return list;
    }

    @GetMapping("/find/{id}")
    public WarehouseResponse singleProd(@PathVariable("id") int id){
        WarehouseResponse result = this.webClient.get().uri("http://localhost:7001/get/" + id).retrieve().bodyToMono(WarehouseResponse.class).block();

        return result;

    }

    @GetMapping("/save")
    public List<AmazonProduct> saveAmazon(){
        List<WarehouseResponse> list1 =this.webClient.get().uri("http://localhost:7001/getAll").retrieve().bodyToFlux(WarehouseResponse.class).collectList().block();
        System.out.println(list1);
        List<WarehouseResponse> res=getAllProducts();
        List<AmazonProduct> collect = res.stream().map(AmazonController::mapToAmazon).collect(Collectors.toList());
        System.out.println(list1);

//        List<AmazonProduct> newAmazonList=new ArrayList<>();
//        for(AmazonProduct a:collect){
//            a.setAmazonProductPrice(a.getAmazonProductPrice()*1.157);
//            newAmazonList.add(a);
//        }

        List<AmazonProduct> aa= collect.stream().map(e->{
           e.setAmazonProductPrice(e.getAmazonProductPrice()*1.068);
            return e;
        }).collect(Collectors.toList());



        List<AmazonProduct>
                amazonProducts = service.saveAll(aa);
        return amazonProducts;
    }

    public static AmazonProduct mapToAmazon(WarehouseResponse prod){
        return new AmazonProduct(prod.getId(),prod.getWproductid(),prod.getWproductname(),prod.getWproductprice());
    }
}
