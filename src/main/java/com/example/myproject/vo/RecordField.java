package com.example.myproject.vo;

import lombok.Data;

@Data
public class RecordField {

    private String name;
    private String type;
    private int length;
    private String comment;
    private boolean primaryKey;
    private String defaultValue;


}
