package com.example.myproject.enums;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum RecordFiledTypeEnum {

    TINYINT("TINYINT"),
    SMALLINT("SMALLINT"),
    MEDIUMINT("MEDIUMINT"),
    INT("INT"),
    INTEGER("INTEGER"),
    BIGINT("BIGINT"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DECIMAL("DECIMAL"),
    DATE("DATE"),
    TIME("TIME"),
    YEAR("YEAR"),
    DATETIME("DATETIME"),
    TIMESTAMP("TIMESTAMP"),
    CHAR("CHAR"),
    VARCHAR("VARCHAR"),
    TINYBLOB("TINYBLOB"),
    TINYTEXT("TINYTEXT"),
    BLOB("BLOB"),
    TEXT("TEXT"),
    MEDIUMBLOB("MEDIUMBLOB"),
    MEDIUMTEXT("MEDIUMTEXT"),
    LONGBLOB("LONGBLOB"),
    LONGTEXT("LONGTEXT");

    private String type;

    private static Map<String, RecordFiledTypeEnum> typeMap;

    private synchronized static Map<String, RecordFiledTypeEnum> getTypeMap() {
        if (null != typeMap) {
            Map<String, RecordFiledTypeEnum> map = Maps.newHashMap();
            for (RecordFiledTypeEnum typeEnum : RecordFiledTypeEnum.values()) {
                map.put(typeEnum.getType(), typeEnum);
            }
            typeMap = map;
        }
        return typeMap;
    }

    public static boolean validateExist(String type) {
        if (StringUtils.isNoneBlank(type)) {
            return getTypeMap().containsKey(type.toUpperCase());
        }
        return false;
    }


}
