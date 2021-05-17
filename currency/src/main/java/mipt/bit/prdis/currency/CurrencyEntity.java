package mipt.bit.prdis.currency;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="date")
    private String date;

    @Column(name="rate")
    private double rate;

    public CurrencyEntity(String date, double rate) {
        this.date = date;
        this.rate = rate;
    }

    public CurrencyEntity() {}

    public double getRate() {
        return rate;
    }

    public String getData() {
        return date;
    }
}
