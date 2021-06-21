package com.example.myproject.controller.api;

import com.example.myproject.services.RecordDataService;
import com.example.myproject.vo.RecordData;
import com.example.myproject.vo.RecordQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("数据表记录操作API")
@RestController
@RequestMapping("/api/record/data")
public class RecordDataController extends BaseController {

    @Autowired
    private RecordDataService recordDataService;

    @ApiOperation(value = "新增表记录", notes = "")
    @ApiImplicitParam(name = "recordData", value = "表接口配置", required = true, dataType = "RecordData")
    @PostMapping
    public String createRecord(@RequestBody RecordData recordData) {
        return recordDataService.add(recordData);
    }

    @ApiOperation(value = "查询表记录", notes = "")
    @ApiImplicitParam(name = "recordQuery", value = "查询接口", required = true, dataType = "RecordQuery")
    @PostMapping("/query")
    public Map<String, Object> queryRecord(@RequestBody RecordQuery recordQuery) {
        return recordDataService.query(recordQuery);
    }

}
