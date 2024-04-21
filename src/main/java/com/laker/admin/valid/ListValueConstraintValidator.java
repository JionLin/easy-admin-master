package com.laker.admin.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author johnny
 * @Classname ListValueConstraintValidator
 * @Description
 * @Date 2024/4/21 10:39
 */

public class ListValueConstraintValidator implements ConstraintValidator<ListValue,String> {

    private Set<String> set = new HashSet<>();

    /**
     * 初始化方法
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        String[] vals = constraintAnnotation.vals();
        for (String val : vals) {
            set.add(val);
        }
    }

    /**
     * 判断是否校验成功
     *
     * @param value   需要校验的值
     * @param context 需要校验的上下文环境信息
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}