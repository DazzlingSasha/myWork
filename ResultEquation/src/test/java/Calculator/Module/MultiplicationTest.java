package Calculator.Module;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Konfetka on 15.05.2016.
 */
public class MultiplicationTest {
private static Multiplication multiplication;
    @Before
    public void setUp() throws Exception {
        multiplication = new Multiplication();
    }

    @Test
    public void testExecute() throws Exception {
        double a = 8;
        double b = -2;

        double expected = multiplication.execute(a, b);
        double actual = -16.d;
        assertThat(expected, is(actual));
    }
}