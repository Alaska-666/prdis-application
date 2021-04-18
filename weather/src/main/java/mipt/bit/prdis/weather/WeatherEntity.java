package mipt.bit.prdis.weather;

import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;

@Entity
@Configurable
public class WeatherEntity {
    @EmbeddedId
    private WeatherEntityId id;
    @OneToOne(cascade= CascadeType.ALL)
    private Weather weather;

    public WeatherEntity(WeatherEntityId id, Weather weather) {
        this.id = id;
        this.weather = weather;
    }

    public WeatherEntity() {}

    public Weather getWeather() {
        return weather;
    }

    public WeatherEntityId getId() {
        return id;
    }
}
