package com.example.myproject.vo;

import lombok.Data;
import org.apache.commons.collections4.KeyValue;

import java.util.List;

@Data
public class RecordData {

    private String databaseName;
    private String tableName;
    private List<KeyValue> keyValues;
    private boolean ignoreIfExist;
    private boolean replaceIfExist;

}
