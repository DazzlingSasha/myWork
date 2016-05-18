package Calculator.Module;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SummationTest {
    private static Summation summation;

    @Before
    public void setUp() throws Exception {
        summation = new Summation();
    }

    @Test
    public void testExecute() throws Exception {
        double a = -5;
        double b = 20;

        double expected = summation.execute(a, b);
        double actual = 15.0d;
        assertThat(expected, is(actual));
    }
}