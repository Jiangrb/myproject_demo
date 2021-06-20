package com.example.myproject.services;

import com.example.myproject.vo.Record;
import com.example.myproject.vo.RecordField;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean add(Record record) {
        validateRecord(record);
        String sql = buildRecoredCreateSql(record);
        jdbc.update(sql);
        return true;
    }

    private void validateRecord(Record record) {
        //TODO
        /**
         * 1. 校验类型
         * 2.不同类型，长度校验
         * 3.主键校验
         */
    }


    public String buildRecoredCreateSql(Record record) {

        String sql = "CREATE TABLE IF NOT EXISTS `" + record.getTableName() + "`( $$$" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci comment='" + record.getAnotherName() + "';";
        List<RecordField> recordDetail = record.getRecordDetail();
        StringBuffer sb = new StringBuffer();
        List<String> primaryKeys = Lists.newArrayList();
        String fieldsSetting = null;
        for (RecordField o : recordDetail) {
            sb.append("`" + o.getName() + "`").append(" ")
                    .append(o.getType()).append("(").append(o.getLength()).append(") ");
            String defaultValue = o.getDefaultValue();
            if (StringUtils.isNotBlank(defaultValue)) {
                sb.append("default ").append(defaultValue).append(" ");
            }
            String comment = StringUtils.trimToEmpty(o.getComment());
            sb.append("comment ").append("'" + comment + "'").append(",");
            if (o.isPrimaryKey()) {
                primaryKeys.add(o.getName());
            }
        }
        if (CollectionUtils.isNotEmpty(primaryKeys)) {
            sb.append(" PRIMARY KEY (`" + Joiner.on("`,`").join(primaryKeys) + "`)");
            fieldsSetting = sb.toString();
        } else {
            fieldsSetting = sb.substring(0, sb.length());
        }
        sql = sql.replace("$$$", fieldsSetting);
        return sql;
    }


}
