package br.com.agls.foodservice.infra.objectmapper;

import br.com.agls.foodservice.entity.Food;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class FoodObjectMapper implements RowMapper<Food> {


    @Override
    public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Food.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .build();
    }
}
