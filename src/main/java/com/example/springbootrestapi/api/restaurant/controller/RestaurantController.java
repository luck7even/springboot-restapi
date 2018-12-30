package com.example.springbootrestapi.api.restaurant.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootrestapi.api.restaurant.model.Restaurant;
import com.example.springbootrestapi.api.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> selectAllRestaurant() {

		HashMap<String, Object> resultMap = new HashMap<>();

		try {
			List<Restaurant> allRestaurant = restaurantService.selectAllRestaurant();
			if (allRestaurant.isEmpty()) {
				resultMap.put("code", "Success");
				resultMap.put("message", "");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			resultMap.put("body", allRestaurant);
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value ="/{rstrntNo}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> selectRestaurant(@PathVariable("rstrntNo")String rstrntNo) {

		HashMap<String, Object> resultMap = new HashMap<>();

		try {
			Restaurant restaurant = restaurantService.selectRestaurant(rstrntNo);
			if (restaurant == null) {
				resultMap.put("code", "InvalidRstrntNo");
				resultMap.put("message", "유효하지 않은 식당 번호입니다");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			resultMap.put("body", restaurant);
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> insertRestaurant(@RequestBody Restaurant restaurant) {
		HashMap<String, Object> resultMap = new HashMap<>();

		try {
			int result = restaurantService.insertRestaurant(restaurant);
			if(result == 0) {
				resultMap.put("code", "InvalidRstrntNo");
				resultMap.put("message", "유효하지 않은 식당 번호입니다");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			//resultMap.put("body", restaurant);
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> updateRestaurant(@RequestBody Restaurant restaurant) {
		HashMap<String, Object> resultMap = new HashMap<>();

		try {
			int result = restaurantService.updateRestaurant(restaurant);
			if(result == 0) {
				resultMap.put("code", "InvalidRstrntNo");
				resultMap.put("message", "유효하지 않은 식당 번호입니다");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			//resultMap.put("body", restaurantService.selectRestaurant(restaurant.getRstrntNo()));
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> deleteRestaurant(@RequestBody Restaurant restaurant) {
		HashMap<String, Object> resultMap = new HashMap<>();
		//HashMap<String, Object> resultParameter = new HashMap<>();

		try {
			int result = restaurantService.deleteRestaurant(restaurant);
			if(result == 0) {
				resultMap.put("code", "InvalidRstrntNo");
				resultMap.put("message", "유효하지 않은 식당 번호입니다");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			//resultParameter.put("RSTRNT_NO", rstrnt_no);
			resultMap.put("code", "Success");
			resultMap.put("message", "삭제되었습니다.");			
			//resultMap.put("body",resultParameter );
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
