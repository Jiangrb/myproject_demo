package com.example.myproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WhereCondition {

    private String key;
    private String operator;
    private String value;
}
