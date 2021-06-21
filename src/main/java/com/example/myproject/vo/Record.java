package com.example.myproject.vo;

import lombok.Data;

import java.util.List;

@Data
public class Record {

    private String databaseName;
    private String tableName;
    private String anotherName;
    private List<RecordField> recordDetail;
    private List<RecordIndex> indexs;

}
