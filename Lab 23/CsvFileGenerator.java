import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileGenerator {
    public static void main(String[] args) {
        String filePath = "loginData.csv";
        String[] data = {
            "username,password",
            "validUser,validPass",
            "invalidUser,validPass",
            "validUser,invalidPass",
            ",",
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("CSV file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}