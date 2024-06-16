package com.hypad.buysell.dao;

import com.hypad.buysell.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AuthDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuthDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean compareData(String name, String password, String gmail){
        String sql = "SELECT COUNT(*) FROM userData WHERE name = :name AND password = :password AND gmail = :gmail";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("password", password)
                .addValue("gmail", gmail);

        try{
            int count = jdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
            return count>0;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public void saveUser(User user){
        String sql = "INSERT INTO userData (name, password, gmail)" +
                " VALUES(:name, :password, :gmail)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword())
                .addValue("gmail", user.getGmail());
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

    public void updateUser(Long id, String newName, String newPass, String newGmail){
        String sql = "UPDATE userData SET name = :name, password = :password, gmail = :gmail WHERE id = :id";
        SqlParameterSource parameterSource  = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("name",newName)
                .addValue("password",newPass)
                .addValue("gmail",newGmail);
        jdbcTemplate.update(sql,parameterSource);
    }
}
