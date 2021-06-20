package com.example.myproject.controller.api;

import com.example.myproject.services.DatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("数据库操作API")
@RestController
@RequestMapping("/api/database")
public class DatabaseController extends BaseController {

    @Autowired
    private DatabaseService databaseService;

    @ApiOperation(value = "创建数据库", notes = "")
    @ApiImplicitParam(name = "databaseName", value = "数据库名称", required = true, dataType = "String")
    @PostMapping
    public String createDatabase(@RequestBody  String databaseName) {
        boolean createDatabaseResult = databaseService.createDatabase(databaseName);
        return "创建数据库：" + databaseName + (createDatabaseResult ? "成功" : "失败");
    }

    @ApiOperation(value = "查看数据库列表", notes = "")
    @GetMapping
    public List<String> createDatabase() {
        List<String> databaseList = databaseService.getDatabaseList();
        return databaseList;
    }

}
