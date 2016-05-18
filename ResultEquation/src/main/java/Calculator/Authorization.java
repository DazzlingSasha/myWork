package Calculator;

import Calculator.jdbc.User;
import Calculator.jdbc.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Authorization {
    private static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class);
    private UserDao userDao;
    private int idUser;
    private static List<User> allUser;

    public Authorization(UserDao userDao) {
        this.userDao = userDao;
        allUser = userDao.allLoginAndPassOfUsers();
    }

    private Scanner scanner = new Scanner(System.in);

    public int checks() {
        boolean isLogin = false;
        boolean isPass = false;
        while (!isLogin && !isPass) {
            System.out.println("Authorization enter 'start'. If you are not registered write 'reg' and press enter");
            System.out.println("For exit enter: 'exit'");
            String enter = scanner.next().replace(" ", "");
            if (("exit").equals(enter)) {
                break;
            }

            if (("reg").equals(enter)) {
                register();
            }
            if (("start").equals(enter)) {
                while (!isLogin) {
                    System.out.println("Enter your login. If you are not registered write 'reg'");
                    String login = scanner.next().replace(" ", "");
                    isLogin = checksLogin(login);

                    if (("reg").equals(enter)) {
                        register();
                    }
                }

                while (!isPass) {
                    System.out.println("Authorization. Enter your password");
                    String pass = scanner.next().replace(" ", "");

                    isPass = checksPass(pass);
                }
            }
        }

        LOGGER.info("Authentication is successful");
        return idUser;
    }

    private void register() {
        boolean isLogin = true;
        boolean isPass = false;
        String login = null;
        String pass = null;

        LOGGER.info("New user");
        while (isLogin || isPass) {
            while (isLogin) {
                int count = 0;
                System.out.println("Enter your login.");
                login = scanner.next().replace(" ", "");
                for (User anAllUser : allUser) {
                    if (login.equals(anAllUser.getLogin())) {
                        idUser = anAllUser.getId();
                        System.out.println("User with that login exists. Enter another");
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    isPass = true;
                    isLogin = false;
                }
            }
            while (isPass) {
                System.out.println("Enter your pass. Password consists of 3 to 16 characters");
                pass = scanner.next().replace(" ", "");

                if (pass.length() > 3 && pass.length() <= 16) {
                    isPass = false;
                } else {
                    System.out.println("Incorrectly typed your password");
                }

            }
        }
        System.out.println("Enter your full name");
        String name = scanner.next().replace(" ", "");
        userDao.insert(new User(login, pass, name));
        allUser = userDao.allLoginAndPassOfUsers();
        LOGGER.info("Add new user");
    }

    private boolean checksLogin(String login) {
        boolean isLogin = false;

        for (User anAllUser : allUser) {
            if (login.equals(anAllUser.getLogin())) {
                idUser = anAllUser.getId();
                isLogin = true;
                break;
            }
        }

        if (!isLogin) {
            LOGGER.info("The introduction of user with the same LOGIN: " + login + " does not exist");
            return false;
        } else {
            return true;
        }
    }

    private boolean checksPass(String pass) {
        boolean isPass = false;

        for (User anAllUser : allUser) {
            if (pass.equals(anAllUser.getPass()) && anAllUser.getId() == idUser) {
                isPass = true;
                break;
            }
        }
        if (!isPass) {
            LOGGER.info("The introduction of user with the same LOGIN: " + pass + " does not exist");
            return false;
        } else {
            return true;
        }
    }

}
