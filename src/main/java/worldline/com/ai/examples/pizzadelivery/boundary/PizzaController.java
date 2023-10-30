package worldline.com.ai.examples.pizzadelivery.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import worldline.com.ai.examples.pizzadelivery.model.Pizza;
import worldline.com.ai.examples.pizzadelivery.model.PizzaIngredient;
import worldline.com.ai.examples.pizzadelivery.service.DataService;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    private DataService dataService;

    @GetMapping("/pizzas")
    public List<Pizza> getPizzas() {
        return dataService.getPizzas();
    }

    @GetMapping("/ingredients")
    public List<PizzaIngredient> getIngredients() {
        return dataService.getPizzaIngredients();
    }
}