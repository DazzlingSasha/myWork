package Calculator;

import Calculator.Module.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class FeaturesCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeaturesCalculator.class);

    private Map<String, Multitasks> methods = new HashMap<>();

    public FeaturesCalculator(Map<String, Multitasks> methods) {
        this.methods = methods;
    }

    public Map<String, Multitasks> getMethods() {
        return this.methods;
    }
}
