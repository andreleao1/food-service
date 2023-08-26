package br.com.agls.foodservice.infra.repository.interfaces;

import br.com.agls.foodservice.entity.Food;

public interface FoodRepository {
    void save(Food food);

    Food findById(String id);
}
