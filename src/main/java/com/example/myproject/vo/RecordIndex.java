package com.example.myproject.vo;

import lombok.Data;

import java.util.List;

@Data
public class RecordIndex {

    private String indexName;
    private List<String> fields;

}
