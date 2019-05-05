package ru.surrsoft.baaz.w265w_configer.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Это аннотация для полей из типа которых нужны только константы
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface N2269_IsConstantsOfClass {
    Class clazz();

    /**
     * Тип константы
     * @return --
     */
    Class clazzConstant();

}
