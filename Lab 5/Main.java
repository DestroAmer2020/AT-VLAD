

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Check if there are arguments for number of elements
        int numberOfElements = args.length > 0 ? Integer.parseInt(args[0]) : 10;

        try {
            // Step 1: Serialization-Deserialization with JSON
            serializeDeserializeJSON();

            // Step 2: Generate Random Objects and Process with Streams
            List<WeatherData> weatherDataList = generateRandomWeatherData(numberOfElements);
            processWeatherData(weatherDataList);

            // Step 3: Serialization-Deserialization with XML
            serializeDeserializeXML();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serializeDeserializeJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize from JSON
        WeatherData weatherData = objectMapper.readValue(new File("input.json"), WeatherData.class);

        // Modify fields
        weatherData.setTemperature(75.0); // Example modification
        objectMapper.writeValue(new File("output.json"), weatherData);
    }

    private static void serializeDeserializeXML() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        // Deserialize from XML
        WeatherData weatherData = xmlMapper.readValue(new File("input.xml"), WeatherData.class);

        // Modify fields
        weatherData.setHumid(false); // Example modification
        xmlMapper.writeValue(new File("output.xml"), weatherData);
    }

    private static List<WeatherData> generateRandomWeatherData(int count) {
        Random random = new Random();
        return IntStream.range(0, count)
            .mapToObj(i -> new WeatherData("time" + i, random.nextDouble() * 100, random.nextBoolean()))
            .collect(Collectors.toList());
    }

    private static void processWeatherData(List<WeatherData> weatherDataList) {
        // Sort using two fields
        List<WeatherData> sortedList = weatherDataList.stream()
            .sorted(Comparator.comparing(WeatherData::getTemperature)
                              .thenComparing(WeatherData::getTime))
            .collect(Collectors.toList());

        // Filter by two fields
        List<WeatherData> filteredList = sortedList.stream()
            .filter(data -> data.getTemperature() > 50 && data.isHumid())
            .collect(Collectors.toList());

        // Collect to List with main fields
        List<String> mainFieldsList = filteredList.stream()
            .map(data -> "Time: " + data.getTime() + ", Temperature: " + data.getTemperature())
            .collect(Collectors.toList());

        // Print results
        mainFieldsList.forEach(System.out::println);
    }
}