package Calculator.Module;

import java.util.HashMap;
import java.util.Map;

public class Levels {
    private Map<String, Integer> levels = new HashMap<>();

    public Levels(Map<String, Integer> levels) {
        this.levels = levels;
    }

    public Map<String, Integer> getMethods() {
        return this.levels;
    }
}
