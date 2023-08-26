package br.com.agls.foodservice.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private UUID id;

    @NotBlank
    private String name;

    public Food(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
