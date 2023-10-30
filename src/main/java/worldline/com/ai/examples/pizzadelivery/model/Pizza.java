package worldline.com.ai.examples.pizzadelivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pizza {

    private String name;
    private List<PizzaIngredient> ingredients;

    public Pizza(String name, List<PizzaIngredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<PizzaIngredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;

        // accept two pizzas as equal if either name or ingredients are equal
        // but accept ingredients as equal if they have the same elements independent of the order
        // special case with duplicates in the lists is ignored, we assume unique elements
        return Objects.equals(name, pizza.name) &&
                ingredients.size() == pizza.ingredients.size() &&
                ingredients.containsAll(pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }
}
