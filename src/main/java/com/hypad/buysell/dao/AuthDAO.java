package com.hypad.buysell.dao;

import com.hypad.buysell.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuthDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(User user){
        String sql = "INSERT INTO userData (name, password)" +
                " VALUES(:name, :password)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword());
        jdbcTemplate.update(sql,parameterSource);
    }

    public boolean compareData(String name, String password){
        String sql = "SELECT COUNT(*) FROM userData WHERE name = :name AND password = :password";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("password", password);

        try{
            int count = jdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
            return count>0;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public void deleteUser(Long id){
        String sql = "DELETE FROM userData WHERE id = :id ";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        jdbcTemplate.update(sql,parameterSource);
    }

}
