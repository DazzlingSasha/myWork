package Calculator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParserEquationTest {
    private static ParserEquation parser;
    private static List<String> allElements;
    private static List<String> list;
    ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    @Before
    public void setUp() throws Exception {
        parser = (ParserEquation) context.getBean("parserEquation");
    }

    @Test
    public void testNegativeNumberVerification1() throws Exception {
        String equation = "-2+4";
        boolean expected = parser.negativeNumberVerification(equation, 0);
        System.out.println(expected);
        assertThat(expected, is(false));
    }

    @Test
    public void testNegativeNumberVerification2() throws Exception {
        String equation = "(-2*2)";
        boolean expected = parser.negativeNumberVerification(equation, 0);
        System.out.println(expected);
        assertThat(expected, is(true));
    }

    @Test
    public void testResultExpression0() throws Exception {
        String equation = "3+4+30";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);

        double actual = 37.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression1() throws Exception {
        String equation = "3-(4*30)";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = -117.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression2() throws Exception {
        String equation = "123 .3 +34 *(12 - 10)";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 191.3d;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression3() throws Exception {
        String equation = "123,3+34*(12-10)";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 191.3d;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression4() throws Exception {
        String equation = "3+2*30+7";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 70.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression5() throws Exception {
        String equation = "(3-1)*(30+7)";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 74.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression6() throws Exception {
        String equation = "((3-1)^(0+7))";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 128.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression7() throws Exception {
        String equation = "((3-1)*(30+7))/2^2";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 18.5;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression8() throws Exception {
        String equation = "((3,-1.)*(30+7.))/2^2,";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 18.5;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression9() throws Exception {
        String equation = "-1*((3,-1.)*(30+7.))/2^2,";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = -18.5;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression10() throws Exception {
        String equation = "(-10*(3-1)^(0+7))";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = -1280.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression11() throws Exception {
        String equation = "-2+4";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 2.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression12() throws Exception {
        String equation = "(-2*2)";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = -4.0d;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression13() throws Exception {
        String equation = "3-(4*30)*(-1)";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = 123.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }

    @Test
    public void testResultExpression14() throws Exception {
        String equation = "3-(2*32)^((-1)*2)";
        allElements = parser.arrangeElements(equation);
        list = parser.findLittleExpression(allElements);
        double expected = parser.resultEquation(list);
        double actual = -5.0;
        System.out.println(expected);
        assertThat(expected, is(actual));
    }
}



