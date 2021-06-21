package com.example.myproject.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RecordBaseInfo {

    @NotBlank(message = "databaseName必填")
    private String databaseName;
    @NotBlank(message = "tableName必填")
    private String tableName;
}
