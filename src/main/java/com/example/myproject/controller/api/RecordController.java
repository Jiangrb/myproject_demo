package com.example.myproject.controller.api;

import com.example.myproject.services.RecordService;
import com.example.myproject.vo.Record;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("数据库操作API")
@RestController
@RequestMapping("/api/record")
public class RecordController extends BaseController {

    @Autowired
    private RecordService recordService;

    @ApiOperation(value = "创建表", notes = "")
    @ApiImplicitParam(name = "record", value = "表接口配置", required = true, dataType = "Record")
    @PostMapping
    public String createRecord(@RequestBody Record record) {
        boolean addResult = recordService.add(record);
        return addResult ? "成功创建" + record.getTableName() : "失败";
    }

}
