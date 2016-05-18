package Calculator.jdbc;

import java.util.List;

public interface SQLMethod<T> {
    void insert(T element);
    void delete(int id);
    void update(T element, int id);
    List<T> allList();

}
