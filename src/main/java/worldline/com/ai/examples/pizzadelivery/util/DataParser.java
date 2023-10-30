package worldline.com.ai.examples.pizzadelivery.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public List<String> parseData(String fileName) {
        List<String> data = new ArrayList<>();

        // read the content of a text file from the Java resources folder and store it in the data list
        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get("src", "main", "resources", fileName).toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}