package com.example.springbootrestapi.api.meal.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.springbootrestapi.api.meal.model.Meal;

@Mapper
public interface MealMapper {
	List<Meal> selectAllMeal();
    Meal selectMeal(String mealMenuNo);
    
    List<Meal> selectMealHistoryListByMealMenuGbn(HashMap<String, String> param);
    int selectTotalCountByMealMenuGbn(HashMap<String, String> param);
    
    int insertMeal(List<Meal> list);    
    int updateMeal(Meal Meal);    
    int deleteMeal(Meal Meal);    
    
	List<Meal> GetTodayMeal();
	List<Meal> GetWeekMeal();
	List<Meal> findDuplicatedMeal(List<Meal> meal);
	
}