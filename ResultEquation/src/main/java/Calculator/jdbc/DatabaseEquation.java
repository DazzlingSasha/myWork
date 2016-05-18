package Calculator.jdbc;

public class DatabaseEquation {
    private int id;
    private int idUser;
    private String equation;
    private double result;

    public DatabaseEquation() {
    }

    public DatabaseEquation(int idUser, String equation, double result) {
        this.idUser = idUser;
        this.equation = equation;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DatabaseEquation{" +
                ", equation='" + equation + '\'' +
                ", result=" + result +
                '}';
    }
}
