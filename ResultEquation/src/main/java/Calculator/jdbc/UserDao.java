package Calculator.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements SQLMethod<User> {

    private JdbcTemplate jdbcTemplate;


    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDao() {
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users (login, pass, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getLogin(), user.getPass(), user.getFio());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(User user, int id) {
        String sql = "UPDATE users SET login=?, pass=?, name=? WHERE id=?";
        jdbcTemplate.update(sql, user.getLogin(), user.getPass(), user.getFio(), id);
    }

    @Override
    public List<User> allList() {
        String sql = "SELECT*FROM users";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
//        parameter.addValue("id", id);
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public List<User> allLoginAndPassOfUsers() {
        String sql = "SELECT id, login, pass FROM users";
        return jdbcTemplate.query(sql, new LoginAndPassRowMapper());
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPass(resultSet.getString("pass"));
            user.setFio(resultSet.getString("name"));
            return user;
        }
    }

    private static final class LoginAndPassRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPass(resultSet.getString("pass"));
            return user;
        }
    }


}
