package br.com.agls.foodservice.api.controller;

import br.com.agls.foodservice.api.dto.FoodRequestDTO;
import br.com.agls.foodservice.entity.Food;
import br.com.agls.foodservice.service.interfaces.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/{id}")
    public ResponseEntity<Food> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.foodService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid FoodRequestDTO foodDTO) {
        this.foodService.save(new Food(foodDTO.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
