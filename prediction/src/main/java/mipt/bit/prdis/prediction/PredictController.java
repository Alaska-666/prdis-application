package mipt.bit.prdis.prediction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictController {
    private final PredictService predictService;

    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping("/")
    public Double getPredictedDollarExchangeRate() {
        return predictService.getPredictedDollarExchangeRate();
    }
}
