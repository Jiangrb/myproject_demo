package com.example.myproject.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Data
public class RecordData extends RecordBaseInfo {

    @Valid
    @NotEmpty(message = "keyValues 不能为空")
    private List<Map<String, Object>> keyValues;
    private boolean ignoreIfExist;
    private boolean replaceIfExist;

}
