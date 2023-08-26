package br.com.agls.foodservice.infra.repository;

import br.com.agls.foodservice.entity.Food;
import br.com.agls.foodservice.infra.objectmapper.FoodObjectMapper;
import br.com.agls.foodservice.infra.repository.interfaces.FoodRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class FoodRepositoryImpl implements FoodRepository {

    JdbcTemplate jdbcTemplate;

    public FoodRepositoryImpl (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Food food) {
        this.jdbcTemplate.update("INSERT INTO food (id, name) VALUES (?,?)", food.getId().toString(), food.getName());
    }

    @Override
    public Food findById(String id) {
        return this.jdbcTemplate.queryForObject("SELECT id, name FROM food WHERE id = ?", new FoodObjectMapper(), id);
    }
}
