package mipt.bit.prdis.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherList {
    private List<Weather> weathers;

    public WeatherList() {
        this.weathers = new ArrayList<>();
    }

    public WeatherList(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}
