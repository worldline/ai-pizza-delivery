package worldline.com.ai.examples.pizzadelivery.service;


import org.springframework.stereotype.Service;
import worldline.com.ai.examples.pizzadelivery.model.Pizza;
import worldline.com.ai.examples.pizzadelivery.model.PizzaIngredient;
import worldline.com.ai.examples.pizzadelivery.util.DataParser;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DataService {

    private final DataParser dataParser;

    public DataService(DataParser dataParser)
    {
        this.dataParser = dataParser;
    }

    public List<Pizza> getPizzas() {
        // Read the PizzaHeaven.data file and parse it into Pizza objects
        List<String> pizzaHeavenData = dataParser.parseData("PizzaHeaven.data");
        List<Pizza> pizzas = pizzaHeavenData.stream()
                .map(line -> line.split(":"))
                .map(fields -> new Pizza(fields[0].trim(), convert(fields[1].split(","))))
                .toList();

        return pizzas;
    }

    public List<PizzaIngredient> getPizzaIngredients() {
        // Read the PizzaIngredients.data file and parse it into PizzaIngredient objects
        List<String> pizzaIngredientsData = dataParser.parseData("PizzaIngredients.data");
        List<PizzaIngredient> pizzaIngredients = pizzaIngredientsData.stream()
                .map(line -> line.split(","))
                .flatMap(Stream::of)
                .map(field -> new PizzaIngredient(field.trim()))
                .toList();

        return pizzaIngredients;
    }

    private List<PizzaIngredient> convert(String[] fields) {
        List<PizzaIngredient> ingredients = new LinkedList<>();

        for (String ingredient : fields) {
            ingredients.add(new PizzaIngredient(ingredient.trim()));
        }

        return ingredients;
    }
}