package com.hypad.buysell.dao;

import com.hypad.buysell.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuthDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    public void saveUser(User user){
        String sql = "INSERT INTO userData (name, password)" +
                " VALUES(:name, :password)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword());
        jdbcTemplate.update(sql,parameterSource);
    }

    public void deleteUser(Long id){
        String sql = "DELETE FROM userData WHERE id = :id ";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        jdbcTemplate.update(sql,parameterSource);
    }

    public List<User> findUserById(Long id){
        String sql = "SELECT * FROM userData WHERE id = :id ";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        return jdbcTemplate.query(sql,parameterSource,new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findAllUsers(){
        String sql = "SELECT * FROM userData ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findUserByName(String name){
        String sql = "SELECT * FROM userData WHERE LOWER(name) = LOWER(:name)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name",name);
        return jdbcTemplate.query(sql,sqlParameterSource,new BeanPropertyRowMapper<>(User.class));
    }
}
