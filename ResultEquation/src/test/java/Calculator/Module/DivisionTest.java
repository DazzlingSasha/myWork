package Calculator.Module;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DivisionTest {
    Division division;

    @Before
    public void setUp() throws Exception {
        division = new Division();
    }

    @Test
    public void testExecute() throws Exception {
        double a = -5;
        double b = 2;

        double expected = division.execute(a, b);
        double actual = -2.5d;
        assertThat(expected, is(actual));
    }
}