package com.example.springbootrestapi.api.meal.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Alias("meal")
public class Meal {
	
    private String rstrntNo;
    private String rstrntNm;
    private String mealMenuNo;
    private String mealMenuYmd;
    private String mealMenuGroup;
    private String mealMenuGbn;
    private String mealMenuContent;
    private String mealMenuCreYmdt;
    private String mealMenuDelYmdt;
    private String memo;
    
}
