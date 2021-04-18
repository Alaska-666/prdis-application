package mipt.bit.prdis.currency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public CurrencyService currencyService(CurrencyRepository currencyRepository) {
        return new CurrencyService(currencyRepository);
    }
}