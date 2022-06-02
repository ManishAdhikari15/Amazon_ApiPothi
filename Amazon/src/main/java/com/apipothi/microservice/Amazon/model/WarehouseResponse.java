package com.apipothi.microservice.Amazon.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponse {

    private int id;
    private String wproductid;
    private String wproductname;
    private long wproductprice;
}
