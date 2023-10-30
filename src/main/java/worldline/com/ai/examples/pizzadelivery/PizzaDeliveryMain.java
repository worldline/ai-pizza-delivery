package worldline.com.ai.examples.pizzadelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import worldline.com.ai.examples.pizzadelivery.util.DataParser;

@SpringBootApplication
@RestController
public class PizzaDeliveryMain {

    public static void main(String[] args) {
        SpringApplication.run(PizzaDeliveryMain.class, args);
    }

    @Bean
    public DataParser getDataParser() {
        return new DataParser();
    }
}