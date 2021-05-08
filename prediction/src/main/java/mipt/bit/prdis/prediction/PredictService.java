package mipt.bit.prdis.prediction;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PredictService {
    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate;
    private static final int DAYS = 8;
    private static final String CITY = "Moscow";

    public PredictService(EurekaClient eurekaClient, RestTemplate restTemplate) {
        this.eurekaClient = eurekaClient;
        this.restTemplate = restTemplate;
    }

    private void addData(SimpleRegression regression, List<Weather> weatherData, List<Double> currencyData) {
        IntStream.range(0, DAYS)
                .forEach(i -> regression.addData(weatherData.get(i).getAvgTempC(), currencyData.get(i)));
    }

    public Double getPredictedDollarExchangeRate() {
        SimpleRegression regression = new SimpleRegression();
        List<Weather> weatherData = getWeather();
        List<Double> currencyData = getCurrency();
        addData(regression, weatherData, currencyData);
        return regression.predict(weatherData.get(0).getAvgTempC());
    }

    private List<Double> getCurrency() {
        String currencyServiceName = "CURRENCY";
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(currencyServiceName, false);
        String requestUrl = UriComponentsBuilder
                .fromUri(URI.create(instance.getHomePageUrl()))
                .queryParam("days", DAYS)
                .toUriString();
        ResponseEntity<List<Double>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Double>>(){});
        return response.getBody();
    }

    private List<Weather> getWeather() {
        String weatherServiceName = "WEATHER";
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(weatherServiceName, false);
        String requestUrl = UriComponentsBuilder
                .fromUri(URI.create(instance.getHomePageUrl()))
                .queryParam("days", DAYS)
                .queryParam("city", CITY)
                .toUriString();
        WeatherList response = restTemplate.getForObject(requestUrl, WeatherList.class);
        if (response == null) {
            return Collections.emptyList();
        }
        return response.getWeathers();
    }
}
