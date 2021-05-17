package mipt.bit.prdis.weather;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="weather")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name="date")
    private String date;

    @Column(name="city")
    private String city;

    @JsonProperty("maxtemp_c")
    @Column(name="maxtemp_c")
    public double maxTempC;

    @JsonProperty("mintemp_c")
    @Column(name="mintemp_c")
    public double minTempC;

    @JsonProperty("avgtemp_c")
    @Column(name="avgtemp_c")
    public double avgTempC;

    @JsonProperty("maxwind_mph")
    @Column(name="maxwind_mph")
    public double maxWindMph;

    @JsonProperty("totalprecip_mm")
    @Column(name="totalprecip_mm")
    public double totalPrecipMm;

    @JsonProperty("avgvis_km")
    @Column(name="avgvis_km")
    public double avgVisKm;

    public Weather(double maxTempC, double minTempC, double avgTempC, double maxWindMph, double totalPrecipMm, double avgVisKm) {
        this.maxTempC = maxTempC;
        this.minTempC = minTempC;
        this.avgTempC = avgTempC;
        this.maxWindMph = maxWindMph;
        this.totalPrecipMm = totalPrecipMm;
        this.avgVisKm = avgVisKm;
    }

    public Weather() {}

    @Override
    public String toString() {
        return "Weather{" +
                "maxTempC=" + maxTempC +
                ", minTempC=" + minTempC +
                ", avgTempC=" + avgTempC +
                ", maxWindMph=" + maxWindMph +
                ", totalPrecipMm=" + totalPrecipMm +
                ", avgVisKm=" + avgVisKm +
                '}';
    }

    public Double getMaxTempC() {
        return maxTempC;
    }

    public Double getMinTempC() {
        return minTempC;
    }

    public Double getAvgTempC() {
        return avgTempC;
    }

    public Double getMaxWindMph() {
        return maxWindMph;
    }

    public Double getTotalPrecipMm() {
        return totalPrecipMm;
    }

    public Double getAvgVisKm() {
        return avgVisKm;
    }

    public void setMaxTempC(double maxTempC) {
        this.maxTempC = maxTempC;
    }

    public void setMinTempC(double minTempC) {
        this.minTempC = minTempC;
    }

    public void setAvgTempC(double avgTempC) {
        this.avgTempC = avgTempC;
    }

    public void setMaxWindMph(double maxWindMph) {
        this.maxWindMph = maxWindMph;
    }

    public void setTotalPrecipMm(double totalPrecipMm) {
        this.totalPrecipMm = totalPrecipMm;
    }

    public void setAvgVisKm(double avgVisKm) {
        this.avgVisKm = avgVisKm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
