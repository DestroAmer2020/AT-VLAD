
import java.io.Serializable;

public class WeatherData implements Serializable {
    private String time;
    private double temperature;
    private boolean isHumid;

    public WeatherData() {}

    public WeatherData(String time, double temperature, boolean isHumid) {
        this.time = time;
        this.temperature = temperature;
        this.isHumid = isHumid;
    }

    // Getters and Setters
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isHumid() {
        return isHumid;
    }

    public void setHumid(boolean humid) {
        isHumid = humid;
    }
}
