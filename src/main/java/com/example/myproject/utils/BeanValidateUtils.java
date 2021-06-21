package com.example.myproject.utils;


import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
public class BeanValidateUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidateResultVO validateEntity(T obj, Class... groupClasses) {
        ValidateResultVO result = new ValidateResultVO();
        Set<ConstraintViolation<T>> set = validator.validate(obj, groupClasses);
        if (set != null && !set.isEmpty()) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    public static <T> ValidateResultVO validateProperty(T obj, String propertyName, Class... groupClasses) {
        ValidateResultVO result = new ValidateResultVO();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName);
        if (set != null && !set.isEmpty()) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = Maps.newHashMap();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    public static <T> void validateErrorThenThrowException(T validateBean, String logFlag, Class... groupClasses) {
        if (null == validateBean) {
            String error = logFlag + "-->校验的实体为空！";
            log.info(error);
            throw new RuntimeException(error);
        }
        ValidateResultVO resultVO = null;
        if ((validateBean instanceof List) && !(validateBean instanceof ValidableList)) {
            resultVO = validateEntity(new ValidableList<>((List) validateBean), groupClasses);
        } else {
            resultVO = validateEntity(validateBean, groupClasses);
        }
        if (resultVO.isHasErrors()) {
            String error = resultVO.getErrorMessageDetail();
            log.info("【{}-->校验】失败:{}", logFlag, error);
            throw new RuntimeException(error);
        }
    }

    public static <T> void validateErrorThenThrowException(T validateBean, Class... groupClasses) {
        validateErrorThenThrowException(validateBean, "", groupClasses);
    }
}

