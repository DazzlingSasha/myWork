package Calculator.Module;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PowerTest {
    private static Power power;

    @Before
    public void setUp() throws Exception {
        power = new Power();
    }

    @Test
    public void testExecute() throws Exception {
        double a = -2;
        double b = 8;

        double expected = power.execute(a, b);
        double actual = 256.0d;
        assertThat(expected, is(actual));
    }
}