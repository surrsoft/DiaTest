package ru.surrsoft.baaz.w265w_configer.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Это аннотация для строк предназначенных для хранения name enum-ов
 * <p/>
 * Пример использования:
 * <pre>
 *     {@code
 * @ N2269_IsEnum(clazzEnum = TextUtils.TruncateAt.class)
 * public String ellipsize = null;
 *     }
 * </pre>
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface N2269_IsEnum {
    /**
     * Класс enum-a
     *
     * @return --
     */
    Class clazzEnum();
}
