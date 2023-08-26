package br.com.agls.foodservice.service;

import br.com.agls.foodservice.entity.Food;
import br.com.agls.foodservice.exceptions.EntityNotFoundException;
import br.com.agls.foodservice.exceptions.InternalServerErrorException;
import br.com.agls.foodservice.infra.repository.interfaces.FoodRepository;
import br.com.agls.foodservice.service.interfaces.FoodService;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Override
    public void save(Food food) {
        try {
            this.foodRepository.save(food);
            log.info("Food save Successfully id {}", food.getId());
        } catch (ConstraintViolationException e) {
            String message = String.format("A constraint violation happened, error message: %s", e.getMessage());
            log.error(message);
            throw new br.com.agls.foodservice.exceptions.ConstraintViolationException(message);
        } catch (Exception e) {
            String message = String.format("An unknown error occurred on the server: %s", e.getLocalizedMessage());
            log.error(message);
            throw new InternalServerErrorException(message);
        }
    }

    @Override
    public Food findById(String id) {
        try {
            log.info("Fetching data to food with id {}", id);
            return this.foodRepository.findById(id);
        }catch (EmptyResultDataAccessException e) {
            String message = String.format("No result was found to id %s", id);
            log.error(message);
            throw new EntityNotFoundException(message);
        }
    }
}
