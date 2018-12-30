package com.example.springbootrestapi.api.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.springbootrestapi.api.restaurant.model.Restaurant;

@Mapper
public interface RestaurantMapper {
	List<Restaurant> selectAllRestaurant();
    Restaurant selectRestaurant(String rstrntNo);
    int insertRestaurant(Restaurant restaurant);    
    int updateRestaurant(Restaurant restaurant);    
    int deleteRestaurant(Restaurant restaurant);    
}