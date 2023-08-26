package br.com.agls.foodservice.service.interfaces;

import br.com.agls.foodservice.entity.Food;

public interface FoodService {
    void save(Food food);

    Food findById(String id);

}
