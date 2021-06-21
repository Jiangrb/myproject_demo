package com.example.myproject.services;

import com.example.myproject.vo.RecordData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecordDataService {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean add(RecordData recordData) {
        //TODO

        return false;
    }
}
