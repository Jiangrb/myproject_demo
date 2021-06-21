package com.example.myproject.services;

import com.example.myproject.utils.BeanValidateUtils;
import com.example.myproject.vo.RecordData;
import com.example.myproject.vo.RecordQuery;
import com.example.myproject.vo.Tuple3;
import com.example.myproject.vo.WhereCondition;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RecordDataService {

    @Autowired
    private JdbcTemplate jdbc;

    public String add(RecordData recordData) {
        validateRecordData(recordData);
        return buildInsertDataSqlAndExecute(recordData);
    }

    private String buildInsertDataSqlAndExecute(RecordData recordData) {
        String tableName = recordData.getTableName();
        String databaseName = recordData.getDatabaseName();
        List<Map<String, Object>> keyValues = recordData.getKeyValues();
        int updateResult = 0;
        if (CollectionUtils.isNotEmpty(keyValues)) {
            List<String> fields = Lists.newArrayList(keyValues.get(0).keySet());
            StringBuilder sql = new StringBuilder();
            if (recordData.isIgnoreIfExist()) {
                sql.append("insert ignore into ");
            } else {
                sql.append("replace into ");
            }
            sql.append(databaseName).append(".`").append(tableName).append("` (")
                    .append(Joiner.on(",").join(fields)).append(") values ");
            List<Object> values = Lists.newArrayList();
            int index = 0;
            int dataNum = keyValues.size();
            for (Map<String, Object> map : keyValues) {
                sql.append("(");
                int i = 0;
                int size = map.size();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    sql.append("?");
                    values.add(entry.getValue());
                    i++;
                    if (i != size) {
                        sql.append(",");
                    }
                }
                index++;
                sql.append(")");
                if (index != dataNum) {
                    sql.append(",");
                } else {
                    sql.append(";");
                }
            }
            log.info("sql----------->" + sql.toString());
            updateResult = jdbc.update(sql.toString(), values.toArray());
        }
        return "新增执行结果" + updateResult;
    }

    private void validateRecordData(RecordData recordData) {
        //TODO
    }

    public Map<String, Object> query(RecordQuery recordQuery) {
        String databaseName = recordQuery.getDatabaseName();
        String tableName = recordQuery.getTableName();
        List<String> fields = recordQuery.getFields();
        String fieldString = "*";
        if (CollectionUtils.isNotEmpty(fields)) {
            fieldString = Joiner.on(",").join(fields);
        }
        StringBuilder sql = new StringBuilder("select ").append(fieldString).append(" from ")
                .append(databaseName).append(".`").append(tableName).append("` ");
        List<WhereCondition> querys = recordQuery.getWhereConditons();
        if (CollectionUtils.isNotEmpty(querys)) {
            sql.append(" where 1=1 ");
            querys.stream().forEach(o -> {
                sql.append(" and ").append(o.getKey()).append(o.getOperator()).append("'" + o.getValue() + "' ");
            });
        }
        log.info("query sql {}", sql.toString());
        return jdbc.queryForMap(sql.toString());

    }

}
