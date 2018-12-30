package com.example.springbootrestapi.api.restaurant.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Alias("restaurant")
public class Restaurant {
	
    private String rstrntNo;
    private String rstrntNm;
    private String creYmdt;
    private String delYmdt;
    private String memo;
    
}