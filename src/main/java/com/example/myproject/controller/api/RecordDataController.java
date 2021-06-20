package com.example.myproject.controller.api;

import com.example.myproject.services.RecordDataService;
import com.example.myproject.vo.Record;
import com.example.myproject.vo.RecordData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/record/data")
public class RecordDataController extends BaseController {

    @Autowired
    private RecordDataService recordDataService;

    @ApiOperation(value = "新增表记录", notes = "")
    @ApiImplicitParam(name = "recordData", value = "表接口配置", required = true, dataType = "RecordData")
    @PostMapping
    public String createRecord(@RequestBody RecordData recordData) {
        boolean addResult = recordDataService.add(recordData);
        return addResult ? "成功新增记录" : "失败";
    }

}
