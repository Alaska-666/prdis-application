package mipt.bit.prdis.weather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherService weatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        return new WeatherService(weatherRepository, restTemplate);
    }
}