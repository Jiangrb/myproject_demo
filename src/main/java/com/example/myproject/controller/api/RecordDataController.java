package com.example.myproject.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("record/data")
public class RecordDataController extends BaseController {


    @PostMapping
    public String addRecordData() {

        return null;
    }

}
