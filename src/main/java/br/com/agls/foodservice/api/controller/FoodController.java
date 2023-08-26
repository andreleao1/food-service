package br.com.agls.foodservice.api.controller;

import br.com.agls.foodservice.api.dto.FoodRequestDTO;
import br.com.agls.foodservice.entity.Food;
import br.com.agls.foodservice.service.interfaces.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid FoodRequestDTO foodDTO) {
        this.foodService.save(new Food(foodDTO.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
