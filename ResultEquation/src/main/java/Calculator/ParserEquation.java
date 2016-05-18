package Calculator;

import Calculator.Module.*;
import Calculator.jdbc.DatabaseEquation;
import Calculator.jdbc.DatabaseEquationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ParserEquation {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParserEquation.class);
    private int bracketLeft = 0;
    private int bracketRight = 0;
    private int levelExpression = 0;
    private static boolean fail = false;
    private FeaturesCalculator methods;

    private Map<String, Integer> levels;
    private Authorization authorization;
    private DatabaseEquationDao databaseEquation;

    public ParserEquation(FeaturesCalculator methods, Authorization authorization, DatabaseEquationDao databaseEquation) {
        this.methods = methods;
        this.authorization = authorization;
        this.databaseEquation = databaseEquation;
        LOGGER.info("Loading capacity calculator successfully");
    }

//    public void setDatabaseEquation(DatabaseEquationDao databaseEquation) {
//        this.databaseEquation = databaseEquation;
//    }

    public void setMapValue(Levels mapValue) {
        this.levels = mapValue.getMethods();
        LOGGER.info("Loading levels equation successfully");
    }

    //    public void setAuthorization(Authorization authorization) {
//        this.authorization = authorization;
//    }
    private static boolean isExit = true;
    private static boolean isAuthorization = true;
    private static int idUser = 0;

    public void resultExpression() {
        Scanner scanner = new Scanner(System.in);

        double result = 0;
        if (isAuthorization) {
            idUser = authorization.checks();
            isAuthorization = false;
        }

        while (isExit) {
            System.out.println("Enter your Equation:");

            String input = scanner.next();

            List<String> allElements = arrangeElements(input);
            List<String> list = findLittleExpression(allElements);
            result = resultEquation(list);

            if (!fail) {
                LOGGER.info("The number of operations = " + (stepResult - 1));
                LOGGER.info("The program has worked successfully. Result: " + result);
                databaseEquation.insert(new DatabaseEquation(idUser, input, result));
                startOver(idUser);
            } else {
                LOGGER.error("The program has worked FAIL. Result: " + result + " is not correctly");
                startOver(idUser);
            }
        }
        scanner.close();
    }

    private void startOver(int idUser) {

        while (isExit) {
            System.out.println("To continue, enter 'next' or to view all of your equation enter 'show'");
            System.out.println("For exit enter: 'exit'");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            if (("exit").equals(next)) {
                isExit = false;
            }
            if (("show").equals(next)) {
                List<DatabaseEquation> list = databaseEquation.allEquationUser(idUser);
                list.forEach(System.out::println);
            }
            if (("next").equals(next)) {
                System.out.println("For enter new Equation");
                resultExpression();
            }
        }
    }

    public List<String> arrangeElements(String input) {
        String withoutSpaces = input.replaceAll(" ", "").replaceAll(",", ".");
        List<String> listElements = new ArrayList<>();
        StringBuilder elementString = new StringBuilder();

        for (int i = 0; i < withoutSpaces.length(); i++) {
            String element = withoutSpaces.charAt(i) + "";
            //check on the number of open parentheses
            if (("(").equals(element)) {
                bracketLeft++;
            }
            if ((")").equals(element)) {
                bracketRight++;
            }
            //check on the negative number verification
            boolean isNegativeNumber = negativeNumberVerification(input, i);

            if (levels.containsKey(element) && isNegativeNumber) {
                //setting the highest level the expression
                if (levelExpression < levels.get(element)) {
                    levelExpression = levels.get(element);
                }

                if (!elementString.toString().equals("")) {
                    listElements.add(elementString.toString());
                }
                elementString = new StringBuilder();
                listElements.add(element);
            } else {
                elementString.append(element);
            }

        }

        if (elementString.length() != 0) {
            listElements.add(elementString.toString());
        }
        checkBracket();
        return listElements;
    }

    private void checkBracket() {
        if (bracketLeft != bracketRight) {
            LOGGER.error("Check write is opening closed brackets. '(' = " + bracketLeft + " ')' = " + bracketRight);
        }
    }

    public boolean negativeNumberVerification(String input, int index) {
        boolean isNegative;
        if (index == 0) {
            isNegative = input.charAt(0) != '-';
        } else {
            isNegative = (!(input.charAt(index) == '-' && levels.containsKey(input.charAt(index - 1) + "") &&
                    input.charAt(index - 1) != ')'));
        }
        return isNegative;
    }

    public List<String> findLittleExpression(List<String> allElements) {
        int start = 0;
        int end;

        while (bracketLeft != 0) {
            for (int i = 0; i < allElements.size(); i++) {
                if (("(").equals(allElements.get(i))) {
                    start = i;
                }
                if ((")").equals(allElements.get(i))) {
                    end = i;
                    List<String> miniInput = new ArrayList<>();
                    int begin = start + 1;

                    for (int index = begin; index < end; index++) {
                        miniInput.add(allElements.get(index));
                    }

//                    miniInput.forEach(System.out::print);

                    double miniResult = resultEquation(miniInput);

                    allElements.set(start, miniResult + "");

                    for (int deleteIndex = start; deleteIndex < end; deleteIndex++) {
                        allElements.remove(begin);
                    }
//                    allElements.forEach(System.out::print);
                    break;
                }
            }
            bracketLeft--;
        }
        return allElements;
    }

    private int stepResult = 1;

    public double resultEquation(List<String> allElements) {
        int step = levelExpression;
        while (step != 0) {
            for (int i = 0; i < allElements.size(); i++) {

                if (levels.containsKey(allElements.get(i))) {
                    double result = 0;
                    if (levels.get(allElements.get(i)) == step) {
                        double a = correctlyValues(allElements, i - 1);
                        double b = correctlyValues(allElements, i + 1);

                        for (Map.Entry<String, Multitasks> entry : methods.getMethods().entrySet()) {
                            if (entry.getKey().equals(allElements.get(i))) {
                                result = entry.getValue().execute(a, b);
                                break;
                            }
                        }
                        String castResult = "" + result;

                        LOGGER.info("Result " + stepResult++ + " operations: " + a + " " + allElements.get(i) + " " + b + " = " + castResult);
                        allElements.set(i - 1, castResult);
                        allElements.remove(i);
                        allElements.remove(i);
                        i = i - 1;
                    }
                }
            }
            step--;
        }

        return correctlyValues(allElements, 0);
    }

    private double correctlyValues(List<String> allElements, int index) {
        try {
            return Double.parseDouble(allElements.get(index));
        } catch (java.lang.NumberFormatException e) {
            if ("(".equals(allElements.get(0)) || "(".equals(allElements.get(0))) {
                checkBracket();
            }
            LOGGER.error("Correct the mistake in the place: " + allElements.get(index));
            LOGGER.error("Check the correctly written example? " + e);
            fail = true;
            return -1;
        }
    }
}
