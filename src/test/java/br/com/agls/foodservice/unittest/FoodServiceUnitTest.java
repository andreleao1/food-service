package br.com.agls.foodservice.unittest;

import br.com.agls.foodservice.entity.Food;
import br.com.agls.foodservice.exceptions.ConstraintViolationException;
import br.com.agls.foodservice.exceptions.EntityNotFoundException;
import br.com.agls.foodservice.exceptions.InternalServerErrorException;
import br.com.agls.foodservice.infra.repository.interfaces.FoodRepository;
import br.com.agls.foodservice.service.FoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = FoodServiceUnitTest.class)
@ExtendWith(MockitoExtension.class)
public class FoodServiceUnitTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodServiceImpl foodService;

    private Food food;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.food = Food.builder().id(UUID.randomUUID()).name("TEST").build();
    }

    @Test
    @DisplayName("Save food")
    public void mustSaveAFoodWhenAValidFoodIsGiven() {
        this.food.setId(null);
        doNothing().when(this.foodRepository).save(Mockito.any(Food.class));

        Assertions.assertDoesNotThrow(() -> this.foodService.save(this.food));
        verify(this.foodRepository, times(1)).save(this.food);
    }

    @Test
    @DisplayName("Constraint violation")
    public void shouldThrowAConstraintViolationWhenAErrorOccurInTheSQLStatement() {
        doThrow(jakarta.validation.ConstraintViolationException.class).when(this.foodRepository).save(Mockito.any(Food.class));

        Throwable thrown = catchThrowable(() -> {
            this.foodService.save(this.food);
        });

        verify(this.foodRepository, times(1)).save(this.food);
        assertThat(thrown, instanceOf(ConstraintViolationException.class));
    }

    @Test
    @DisplayName("SQL statement error")
    public void shouldThrowAInternalServerErrorExceptionWhenAErrorOccurInTheSQLStatement() {
        doThrow(InternalServerErrorException.class).when(this.foodRepository).save(Mockito.any(Food.class));

        Throwable thrown = catchThrowable(() -> {
            this.foodService.save(this.food);
        });

        verify(this.foodRepository, times(1)).save(this.food);
        assertThat(thrown, instanceOf(InternalServerErrorException.class));
    }

    @Test
    @DisplayName("Find by id")
    public void shouldReturnAFoodWhenAValidIdWasGiven() {
        when(this.foodRepository.findById(this.food.getId().toString())).thenReturn(this.food);

        Food foodFound = this.foodService.findById(this.food.getId().toString());

        verify(this.foodRepository, times(1)).findById(this.food.getId().toString());
        assertThat(foodFound.getId(), is(equalTo(this.food.getId())));
        assertThat(foodFound.getName(), is(equalTo(this.food.getName())));
    }

    @Test
    @DisplayName("Food nonexistent by id")
    public void shouldThrowAEntityNotFoundExceptionWhenAErrorOccurInTheSQLStatement() {
        doThrow(EmptyResultDataAccessException.class).when(this.foodRepository).findById(this.food.getId().toString());

        Throwable thrown = catchThrowable(() -> {
            this.foodService.findById(this.food.getId().toString());
        });

        verify(this.foodRepository, times(1)).findById(this.food.getId().toString());
        assertThat(thrown, instanceOf(EntityNotFoundException.class));
    }
}
