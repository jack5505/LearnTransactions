package com.ofb.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addProduct(String name){
        String sql = "INSERT INTO product values (?,1)";
        jdbcTemplate.update(sql,name);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public void addProduct(int index, String name){
        String sql = "INSERT INTO product values (?,?)";
        jdbcTemplate.update(sql,name,index);
    }


}
