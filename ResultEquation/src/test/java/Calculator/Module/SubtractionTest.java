package Calculator.Module;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SubtractionTest {
    private static Subtraction subtraction;

    @Before
    public void setUp() throws Exception {
        subtraction = new Subtraction();
    }

    @Test
    public void testExecute() throws Exception {
        double a = -5;
        double b = 2;

        double expected = subtraction.execute(a, b);
        double actual = -7.0d;
        assertThat(expected, is(actual));
    }
}