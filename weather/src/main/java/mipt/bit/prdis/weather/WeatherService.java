package mipt.bit.prdis.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeatherService {
    @Value("${api.weather.key}")
    private String apiKey;

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    private Weather getWeatherDaysBefore(int daysBefore, String city) {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().minusDays(daysBefore));
        List<Weather> weatherList = weatherRepository.findAll();
        for (Weather weather: weatherList) {
            if (weather.getCity().equals(city) && weather.getDate().equals(date)) {
                return weather;
            }
        }
        return getWeatherProperties(date, city);
    }

    private Weather getWeatherProperties(String date, String city) {
        String url = "http://api.weatherapi.com/v1/history.json?key=" + apiKey + "&q=" + city + "&dt=" + date;
        JsonWeatherResponse response = restTemplate.getForObject(url, JsonWeatherResponse.class);
        if (response == null) {
            return new Weather();
        }
        Weather weather = response.forecast.forecastDay.get(0).weather;
        weather.setCity(city);
        weather.setDate(date);
        weatherRepository.save(weather);
        weatherRepository.flush();
        return weather;
    }

    public WeatherList getWeather(int days, String city) {
        return new WeatherList(IntStream.range(0, days)
                .mapToObj(i -> getWeatherDaysBefore(i, city))
                .collect(Collectors.toList()));
    }
}
