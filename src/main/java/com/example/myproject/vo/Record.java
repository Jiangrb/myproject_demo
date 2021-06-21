package com.example.myproject.vo;

import lombok.Data;

import java.util.List;

@Data
public class Record extends RecordBaseInfo {


    private String anotherName;
    private List<RecordField> recordDetail;
    private List<RecordIndex> indexs;

}
