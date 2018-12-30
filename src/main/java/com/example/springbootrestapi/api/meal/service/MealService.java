package com.example.springbootrestapi.api.meal.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootrestapi.api.meal.mapper.MealMapper;
import com.example.springbootrestapi.api.meal.model.Meal;

@Service
@Transactional
public class MealService {
	
    @Autowired
    MealMapper MealMapper;
    
	public List<Meal> selectAllMeal() {
		return MealMapper.selectAllMeal();
	}
	
	public Meal selectMeal(String mealMenuNo) {
		return MealMapper.selectMeal(mealMenuNo);
	}
	
	public List<Meal> selectMealHistoryListByMealMenuGbn(HashMap<String, String> param) {
		return MealMapper.selectMealHistoryListByMealMenuGbn(param);
	}

	public int insertMeal(List<Meal> meal) {
		return MealMapper.insertMeal(meal);
	}
	
	public int updateMeal(Meal meal) {
		return MealMapper.updateMeal(meal);
	}
	
	public int deleteMeal(Meal meal) {
		return MealMapper.deleteMeal(meal);
	}
	
	public List<Meal> GetTodayMeal() {
		return MealMapper.GetTodayMeal();
	}
	
	public List<Meal> GetWeekMeal() {
		return MealMapper.GetWeekMeal();
	}

	public List<Meal> findDuplicatedMeal(List<Meal> meal) {
		return MealMapper.findDuplicatedMeal(meal);
	}

	public int selectTotalCountByMealMenuGbn(HashMap<String, String> param) {
		return MealMapper.selectTotalCountByMealMenuGbn(param);
	}



}