package mipt.bit.prdis.prediction;

import com.netflix.discovery.EurekaClient;
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
    public PredictService predictService(EurekaClient eurekaClient, RestTemplate restTemplate) {
        return new PredictService(eurekaClient, restTemplate);
    }
}