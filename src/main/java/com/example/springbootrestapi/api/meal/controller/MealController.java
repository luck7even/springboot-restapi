package com.example.springbootrestapi.api.meal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootrestapi.api.meal.model.Meal;
import com.example.springbootrestapi.api.meal.service.MealService;

@RestController
@RequestMapping("/restaurant/meal")
public class MealController {

	@Autowired
	private MealService mealService;
	
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> selectAllMeal() {

		HashMap<String, Object> resultMap = new HashMap<>();

		try {
			List<Meal> allMeal = mealService.selectAllMeal();
			if (allMeal.isEmpty()) {
				resultMap.put("code", "Success");
				resultMap.put("message", "");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			resultMap.put("body", allMeal);
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value ="/{mealMenuNo}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> selectMeal(@PathVariable("mealMenuNo")String mealMenuNo) {

		HashMap<String, Object> resultMap = new HashMap<>();

		try {
			Meal meal = mealService.selectMeal(mealMenuNo);
			if (meal == null) {
				resultMap.put("code", "InvalidMealNo");
				resultMap.put("message", "유효하지 않은 식사메뉴 번호입니다");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			resultMap.put("body", meal);
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value ="/history", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> selectMealHistoryListByMealMenuGbn(@RequestBody HashMap<String, String> param) {

		HashMap<String, Object> resultMap = new HashMap<>();
		HashMap<String, Object> listMap = new HashMap<>();
		
		if(!param.containsKey("pageNumber"))
			param.put("pageNumber", "1");
		
		if(!param.containsKey("rowsPerPage"))
			param.put("rowsPerPage", "10");
		
		try {
			
			int totalCount = mealService.selectTotalCountByMealMenuGbn(param);
			List<Meal> allMeal = new ArrayList<Meal>();
			
			if (totalCount > 0) {
				allMeal = mealService.selectMealHistoryListByMealMenuGbn(param);
			}
			
			resultMap.put("code", "Success");
			resultMap.put("message", "성공");
		
			listMap.put("totalCount", totalCount);
			listMap.put("responseCount", allMeal.size());
			listMap.put("totalPageCount", Math.round((float)totalCount/10));
			listMap.put("currentPageNumber", Integer.parseInt(param.get("pageNumber")));
			listMap.put("list", allMeal);
			
			resultMap.put("body", listMap);
			
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> insertMeal(@RequestBody List<Meal> meal) {
		HashMap<String, Object> resultMap = new HashMap<>();
		
		try {
			List<Meal> duplicatedMeal = mealService.findDuplicatedMeal(meal);
			if(duplicatedMeal.isEmpty()){
				int result = mealService.insertMeal(meal);
				if(result == 0) {
					resultMap.put("code", "InvalidMealNo");
					resultMap.put("message", "유효하지 않은 식사메뉴 번호입니다");
					return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
				}
			} else{
				resultMap.put("code", "duplicatedMeal");
				resultMap.put("message", "동일한 식사정보가 있습니다.");
				resultMap.put("body", duplicatedMeal);
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> updateMeal(@RequestBody Meal meal) {
		HashMap<String, Object> resultMap = new HashMap<>();

		try {
			int result = mealService.updateMeal(meal);
			if(result == 0) {
				resultMap.put("code", "InvalidMealNo");
				resultMap.put("message", "유효하지 않은 식사메뉴 번호입니다");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			resultMap.put("code", "Success");
			resultMap.put("message", "");
			//resultMap.put("body", mealService.selectMeal(meal.getMealMenuNo()));
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> deleteMeal(@RequestBody Meal meal) {
		HashMap<String, Object> resultMap = new HashMap<>();
		//HashMap<String, Object> resultParameter = new HashMap<>();

		try {
			int result = mealService.deleteMeal(meal);
			if(result == 0) {
				resultMap.put("code", "InvalidMealNo");
				resultMap.put("message", "유효하지 않은 식사메뉴 번호입니다");
				return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);
			}

			//resultParameter.put("MEAL_NO", meal_no);
			resultMap.put("code", "Success");
			resultMap.put("message", "");			
			//resultMap.put("body",resultParameter);
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "Fail");
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<HashMap<String, Object>>(resultMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
