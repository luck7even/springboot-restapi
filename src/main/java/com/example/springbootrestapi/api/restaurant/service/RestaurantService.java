package com.example.springbootrestapi.api.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootrestapi.api.restaurant.mapper.RestaurantMapper;
import com.example.springbootrestapi.api.restaurant.model.Restaurant;

@Service
@Transactional
public class RestaurantService {
	
    @Autowired
    RestaurantMapper restaurantMapper;
    
	public List<Restaurant> selectAllRestaurant() {
		return restaurantMapper.selectAllRestaurant();
	}
	
	public Restaurant selectRestaurant(String rstrntNo) {
		return restaurantMapper.selectRestaurant(rstrntNo);
	}

	public int insertRestaurant(Restaurant restaurant) {
		return restaurantMapper.insertRestaurant(restaurant);
	}
	
	public int updateRestaurant(Restaurant restaurant) {
		return restaurantMapper.updateRestaurant(restaurant);
	}
	
	public int deleteRestaurant(Restaurant restaurant) {
		return restaurantMapper.deleteRestaurant(restaurant);
	}
}