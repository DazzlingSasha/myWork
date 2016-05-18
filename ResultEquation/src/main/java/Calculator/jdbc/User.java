package Calculator.jdbc;

public class User {
    private int id;
    private String login;
    private String pass;
    private String fio;

    public User(){}

    public User(int id, String login, String pass, String fio) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.fio = fio;
    }

    public User(String login, String pass, String fio) {
        this.login = login;
        this.pass = pass;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getFio() {
        return fio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "(id: "+ getId() +
                " login: "+ getLogin()+
                " pass: "+ getPass() +
                " FIO: " +getFio()+")";
    }
}
