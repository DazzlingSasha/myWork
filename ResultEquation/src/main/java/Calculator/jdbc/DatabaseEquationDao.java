package Calculator.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseEquationDao implements SQLMethod<DatabaseEquation> {

    private JdbcTemplate jdbcTemplate;

    public DatabaseEquationDao() {
    }

    public DatabaseEquationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(DatabaseEquation databaseEquation) {
        String sql = "INSERT INTO database_equation (id_user, equation, result) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, databaseEquation.getIdUser(), databaseEquation.getEquation(), databaseEquation.getResult());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM database_equation WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(DatabaseEquation databaseEquation, int id) {
        String sql = "UPDATE database_equation SET id_user=?, equation=?, result=? WHERE id=?";
        jdbcTemplate.update(sql, databaseEquation.getIdUser(), databaseEquation.getEquation(), databaseEquation.getResult(), id);
    }

    @Override
    public List<DatabaseEquation> allList() {
        String sql = "SELECT * FROM database_equation";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public List<DatabaseEquation> allEquationUser(int userID) {
        String sql = "SELECT * FROM database_equation WHERE id_user=?";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id_user", userID);
        return jdbcTemplate.query(sql, new AllEquationUserMapper(), userID);
    }
    private static final class AllEquationUserMapper implements RowMapper<DatabaseEquation> {
        @Override
        public DatabaseEquation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            DatabaseEquation databaseEquation = new DatabaseEquation();
            databaseEquation.setEquation(resultSet.getString("equation"));
            databaseEquation.setResult(resultSet.getDouble("result"));
            return databaseEquation;
        }
    }
    private static final class UserRowMapper implements RowMapper<DatabaseEquation> {
        @Override
        public DatabaseEquation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            DatabaseEquation databaseEquation = new DatabaseEquation();
            databaseEquation.setId(resultSet.getInt("id"));
            databaseEquation.setId(resultSet.getInt("id_user"));
            databaseEquation.setEquation(resultSet.getString("equation"));
            databaseEquation.setResult(resultSet.getDouble("result"));
            return databaseEquation;
        }
    }
}
