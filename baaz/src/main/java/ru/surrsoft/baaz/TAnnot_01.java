package ru.surrsoft.baaz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Утилитные методы для работы с аннотациями
 * <p>
 * ВЕРСИЯ 1 2019-02-03 (stored)
 */
public class TAnnot_01 {

  /**
   * Возвращает TRUE если поле (2) аннотировано аннотацией (1).
   * Если (1) или (2) == null то возвращает FALSE
   *
   * @param annClass (1) --
   * @param field    (2) --
   * @return --
   */
  public static boolean isPresent(Class<? extends Annotation> annClass, Field field) {
    return annClass != null && field != null && field.isAnnotationPresent(annClass);
  }

  /**
   * Возвращает TRUE если класс (2) аннотирован аннотацией (1).
   * Если (1) или (2) == null то возвращает FALSE
   *
   * @param annClass (1) --
   * @param cls      (2) --
   * @return --
   */
  public static boolean isPresent(Class<? extends Annotation> annClass, Class<?> cls) {
    return annClass != null && cls != null && cls.isAnnotationPresent(annClass);
  }

  /**
   * Получение аннотации (1) из поля (2)
   *
   * @param annClass (1) -- класс аннотации
   * @param field    (2) --
   * @param <A>      --
   * @return --
   */
  public static <A extends Annotation> A get(Class<A> annClass, Field field) {
    if (annClass != null && field != null) {
      return field.getAnnotation(annClass);
    }
    return null;
  }

  /**
   * Получение всех аннотаций поля (1)
   *
   * @param field (1) --
   * @return null при нештатах
   */
  public static Annotation[] getAll(Field field) {
    if (field == null) {
      return null;
    }
    //---
    return field.getDeclaredAnnotations();
  }

  /**
   * Получение объекта-аннотации типа (1) класса (2)
   *
   * @param annClass (1) --
   * @param cls      (2) --
   * @param <A>      --
   * @return --
   */
  public static <A extends Annotation> A get(Class<A> annClass, Class<?> cls) {
    if (annClass != null && cls != null) {
      return cls.getAnnotation(annClass);
    }
    return null;
  }

  /**
   * Массив аннотаций (1) строкой
   *
   * @param annotations (1) --
   * @return --
   */
  public static String asString(Annotation[] annotations) {
    if (annotations != null) {
      StringBuilder stRet = new StringBuilder();
      String stDiv = "";
      for (Annotation ann : annotations) { //LOOP
        stRet.append(stDiv).append(ann.toString());
        stDiv = ";\n";
      } //LOOP
      return stRet.toString();
    }
    return null;
  }


}
