package com.example.myproject.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.text.MessageFormat;


@Getter
@Slf4j
public enum GsonUtil {

    SINGLE(new GsonBuilder().serializeNulls().create()),
    HTML(new GsonBuilder().disableHtmlEscaping().create()),
    LOWER_CASE(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()),

    /**
     * 格式化输出
     */
    PRETTY_JSON(new GsonBuilder().serializeNulls().setPrettyPrinting().create()),
    SIMPLE(new Gson());

    private final Gson gson;

    GsonUtil(Gson gson) {
        this.gson = gson;
    }

    public Gson getGson() {
        return gson;
    }

    @Deprecated
    public static void dumper(Object object, String flag) {
        String info = MessageFormat.format(
                "\n▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼{0}▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼" +
                        "\n{1}" +
                        "\n▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲{0}▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲",
                flag, PRETTY_JSON.gson.toJson(object));
        log.info(info);
    }

    @Deprecated
    public static void dumper(Object object) {
        dumper(object, "");
    }

    public static String toJson(Object object) {
        return GsonUtil.SINGLE.getGson().toJson(object);
    }

    public static String toJson(GsonUtil gsonType, Object object) {
        return gsonType.getGson().toJson(object);
    }

    public static String toJson(String dateFormatPattern, Object object) {
        return new GsonBuilder().setDateFormat(dateFormatPattern).create().toJson(object);
    }


    public static <T> T from(String json, Type type) {
        return GsonUtil.SINGLE.getGson().fromJson(json, type);
    }

    public static <T> T from(String json, String dateFormatPattern, Type type) {
        return new GsonBuilder().setDateFormat(dateFormatPattern).create().fromJson(json, type);
    }

    public static <T> T from(GsonUtil gsonType, String json, Type type) {
        return gsonType.getGson().fromJson(json, type);
    }


}
