package Calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        LOGGER.info("The program is starting");
        ParserEquation parser = (ParserEquation) context.getBean("parserEquation");
        parser.resultExpression();
        LOGGER.info("The END");
    }
}
