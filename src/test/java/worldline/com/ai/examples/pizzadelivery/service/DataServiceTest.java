package worldline.com.ai.examples.pizzadelivery.service;

import org.junit.jupiter.api.Test;
import worldline.com.ai.examples.pizzadelivery.model.Pizza;
import worldline.com.ai.examples.pizzadelivery.model.PizzaIngredient;
import worldline.com.ai.examples.pizzadelivery.util.DataParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class DataServiceTest {

    @Test
    public void testGetPizzas() {
        // Create a mock DataParser class
        DataParser dataParserMock = mock(DataParser.class);

        // Set up the mock DataParser to return a list of pizzas
        List<String> pizzas = List.of(
                "Hawai: Tomato, Pineapple, Mozzarella",
                "Pepperoni: Tomato, Mozzarella, Pepperoni"
        );

        List<Pizza> expectedPizzas = List.of(
                new Pizza("Hawai", List.of(
                        new PizzaIngredient("Tomato"),
                        new PizzaIngredient("Pineapple"),
                        new PizzaIngredient("Mozzarella")
                )),
                new Pizza("Pepperoni", List.of(
                        new PizzaIngredient("Tomato"),
                        new PizzaIngredient("Mozzarella"),
                        new PizzaIngredient("Pepperoni")
                ))
        );

        when(dataParserMock.parseData("PizzaHeaven.data")).thenReturn(pizzas);

        // Create an instance of DataService with the mock DataParser
        DataService dataService = new DataService(dataParserMock);

        // Call the getPizzas method
        List<Pizza> result = dataService.getPizzas();

        // Assert that the result matches the expected list of pizzas
        assertEquals(expectedPizzas.size(), result.size());
        assertTrue("both lists contain the same pizzas", expectedPizzas.containsAll(result));
    }

     @Test
    public void testGetPizzasMixedIngredientOrder() {
        // Create a mock DataParser class
        DataParser dataParserMock = mock(DataParser.class);

        // Set up the mock DataParser to return a list of pizzas
        List<String> pizzas = List.of(
                "Hawai: Pineapple, Mozzarella, Tomato",
                "Pepperoni: Mozzarella, Tomato, Pepperoni"
        );

        List<Pizza> expectedPizzas = List.of(
                new Pizza("Hawai", List.of(
                        new PizzaIngredient("Pineapple"), new PizzaIngredient("Mozzarella"), new PizzaIngredient("Tomato"))
                ),
                new Pizza("Pepperoni", List.of(
                        new PizzaIngredient("Mozzarella"), new PizzaIngredient("Tomato"), new PizzaIngredient("Pepperoni"))
                )
        );

        when(dataParserMock.parseData("PizzaHeaven.data")).thenReturn(pizzas);

        // Create an instance of DataService with the mock DataParser
        DataService dataService = new DataService(dataParserMock);

        // Call the getPizzas method
        List<Pizza> result = dataService.getPizzas();

        // Assert that the result matches the expected list of pizzas
        assertEquals(expectedPizzas.size(), result.size());
        assertTrue("both lists contain the same pizzas", expectedPizzas.containsAll(result));
    }

    @Test
    public void testGetPizzaIngredients() {
        // Create a mock DataParser class
        DataParser dataParserMock = mock(DataParser.class);

        // Set up the mock DataParser to return a list of pizza ingredients
        List<String> pizzaIngredients = List.of("Tomato", "Mozzarella", "Oregano", "Banana Peels");

        List<PizzaIngredient> expectedPizzaIngredients = List.of(
                new PizzaIngredient("Tomato"),
                new PizzaIngredient("Mozzarella"),
                new PizzaIngredient("Oregano"),
                new PizzaIngredient("Banana Peels")
        );

        when(dataParserMock.parseData("PizzaIngredients.data")).thenReturn(pizzaIngredients);

        // Create an instance of DataService with the mock DataParser
        DataService dataService = new DataService(dataParserMock);

        // Call the getPizzaIngredients method
        List<PizzaIngredient> result = dataService.getPizzaIngredients();

        // Assert that the result matches the expected list of pizza ingredients
        assertEquals(expectedPizzaIngredients, result);
    }
}