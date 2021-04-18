package mipt.bit.prdis.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Optional<WeatherEntity> weatherEntity = weatherRepository.findById(new WeatherEntityId(date, city));
        return weatherEntity.map(WeatherEntity::getWeather).orElseGet(() -> getWeatherProperties(date, city));
    }

    private Weather getWeatherProperties(String date, String city) {
        String url = "http://api.weatherapi.com/v1/history.json?key=" + apiKey + "&q=" + city + "&dt=" + date;
        JsonWeatherResponse response = restTemplate.getForObject(url, JsonWeatherResponse.class);
        if (response == null) {
            return new Weather();
        }
        return response.forecast.forecastDay.get(0).weather;
    }

    public WeatherList getWeather(int days, String city) {
        return new WeatherList(IntStream.range(0, days)
                .mapToObj(i -> getWeatherDaysBefore(i, city))
                .collect(Collectors.toList()));
    }
}
