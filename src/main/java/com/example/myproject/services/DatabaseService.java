package com.example.myproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;


@Service
@Slf4j
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean createDatabase(String databaseName) {
        String createDatabaseSqlTemplate = "CREATE DATABASE IF NOT EXISTS {0} DEFAULT CHARSET utf8 COLLATE utf8_general_ci;";
        String sql = MessageFormat.format(createDatabaseSqlTemplate, databaseName);
        try {
            jdbc.update(sql);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public List<String> getDatabaseList() {
        String sql = "SHOW DATABASES";
        List<String> maps = jdbc.queryForList(sql, String.class);
        return maps;
    }
}
