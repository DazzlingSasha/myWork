package Calculator.Module;

public class Power implements Multitasks {
    @Override
    public double execute(double a, double b) {
        b = (b < 0) ? 1/(b * -1) : b;
        return Math.pow(a, b);
    }
}
