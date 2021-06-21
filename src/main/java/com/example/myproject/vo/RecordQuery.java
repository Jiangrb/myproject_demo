package com.example.myproject.vo;

import lombok.Data;

import java.util.List;

@Data
public class RecordQuery extends RecordBaseInfo {

    List<String> fields;

    List<WhereCondition> whereConditons;


}
