package ru.surrsoft.baaz.tclasses;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Утилитные методы для работы с типом "java.lang.Class"
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class TClass_01 {

  /**
   * Получение [wira] объекта (1)
   * <p>
   * #[[wira]] - это объект типа java.lang.Class отражающий какой либо реальный объект
   *
   * @param oj (1) --
   * @return --
   */
  public static Class getWira(Object oj) {
    return oj.getClass();
  }

  /**
   * Получение из (1) полного имени класса ([teso]), например "ru.evgeny.Cls1"
   *
   * @param wira (1) --
   * @return например "ru.evgeny.Cls1"
   */
  public static String getTeso(Class wira) {
    return wira.getName();
  }

  /**
   * Возвращает все поля (публичные и приватные) только текущего класса/интерфейса (1)
   * (т.е. вверх по иерархии наследования заглядывание не идет)
   *
   * @param wira (1) --
   * @return --
   */
  public static Field[] getFields(Class wira) {
    return wira.getDeclaredFields();
  }

  /**
   * Отличается от А тем что:
   * а) будут возвращены только публичные поля;
   * б) будут возвращены поля не только текущего класса/интерфейса (1), но
   * и поля классов-предков класса/интерфейса (1)
   *
   * @param wira (1) --
   * @return --
   */
  public static Field[] getFields_B(Class wira) {
    return wira.getFields();
  }

  /**
   * Отличается от А только тем что в результат попадают только ПУБЛИЧНЫЕ поля
   * <p>
   * ОПИСАНИЕ А:
   * Возвращает все поля (публичные и приватные) только текущего класса/интерфейса (1)
   * (т.е. вверх по иерархии наследования заглядывание не идет)
   *
   * @param wira (1) --
   * @return --
   */
  public static Field[] getFields_C(Class wira) {
    if (wira == null) return null;
    //--
    Field[] ret = {};
    Field[] fields = TClass_01.getFields(wira);
    for (Field eField : fields) { //LOOP
      int mod = eField.getModifiers();
      if (Modifier.isPublic(mod)) {
        ret = TArray_01.add(ret, eField);
      }
    } //LOOP
    return ret;
  }

  /**
   * От С отличается только тем что в результат не попадут поля не имеющие аннотации (2)
   * <p>
   * ОПИСАНИЕ: возвращает все ПУБЛИЧНЫЕ поля текущего класса/интерфейса (1) обладающие аннотацией
   * (2).
   * Вверх по иерархии наследования заглядывание не идет.
   *
   * @param wira       (1) --
   * @param annotation (2) --
   * @return --
   */
  public static Field[] getFields_D(Class wira, Class<? extends Annotation> annotation) {
    if (wira == null || annotation == null) return null;
    //---
    Field[] fields = getFields_C(wira);
    Field[] ret = {};
    for (Field eField : fields) {
      if (eField.isAnnotationPresent(annotation)) {
        ret = ArrayUtils.add(ret, eField);
      }
    }
    return ret;
  }


  /**
   * Возвращает длину объекта-массива (1) ([xamd])
   *
   * @param xamd (1) -- объект который должен быть массивом ([xamd])
   * @return --
   */
  public static int getXamdLength(Object xamd) {
    return java.lang.reflect.Array.getLength(xamd);
  }

  /**
   * Извлечение из объекта-массива (1) элемента по индексу (2). Полезен когда мы знаем что (1)
   * это [xamd], но не владеем информацией о его типе
   *
   * @param xamd  (1) -- объект который должен обязательно быть массивом ([xamd])
   * @param index (2) -- индекс, 0+
   * @return --
   */
  public static Object getXamdElem(Object xamd, int index) {
    return java.lang.reflect.Array.get(xamd, index);
  }

  /**
   * Вовзращает TRUE если поле по имени (2) присутствует среди публичных полей класса (1), с
   * учетом всех предков (1)
   *
   * @param cls         (1) --
   * @param stFieldName (2) --
   * @return --
   */
  public static boolean isFieldExist(Class<?> cls, String stFieldName) {
    Field[] fields = getFields_B(cls);
    for (Field eField : fields) {
      if (eField.getName().equals(stFieldName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Взвращает TRUE если класс (1) и класс (2) это один и тот же класс
   *
   * @param wira1 (1) -- [wira]
   * @param wira2 (2) -- [wira]
   * @return --
   */
  public static boolean isClass(Class<?> wira1, Class<?> wira2) {
    return wira1 == wira2;
  }


  /**
   * Взвращает TRUE если класс (1) является суперклассом класса (2)
   *
   * @param wira1 (1) -- [wira]
   * @param wira2 (2) -- [wira]
   * @return --
   */
  public static boolean isClassSuper(Class<?> wira1, Class<?> wira2) {
    return wira1.isAssignableFrom(wira2);
  }

  /**
   * Ищется любое поле (публичное, приватное и т.д.) (2) класса (1). Поле ищется только в классе
   * (1), вверх по иерархии заглядывание не идет
   *
   * @param cls       (1) --
   * @param fieldName (2) --
   * @return NULL если не найдено
   */
  public static Field getField(Class cls, String fieldName) {
    if (cls != null && fieldName != null) {
      try {
        return cls.getDeclaredField(fieldName);
      } catch (NoSuchFieldException e) {
      }
    }
    return null;
  }

  /**
   * Ищется ПУБЛИЧНОЕ поле (2) в классе (1) и во всех его супертипах
   * <p>
   * Отличие от А - ищет только публичные поля, но не только в классе (1) а и во всех его
   * супертипах
   *
   * @param cls       (1) --
   * @param fieldName (2) --
   * @return NULL если не найдено
   */
  public static Field getField_B(Class cls, String fieldName) {
    if (cls != null && fieldName != null) {
      try {
        return cls.getField(fieldName);
      } catch (NoSuchFieldException e) {
      }
    }
    return null;
  }

  /**
   * Получение значения-поля ([rumz])(3) из объекта (2) принадлежащего типу (1)
   * <p>
   * Поле (публичное, приватное и т.д.) ищется только в пределах содержимого класса (1) (вверх по
   * иерархии заглядывание не идет)
   *
   * @param cls       (1) --
   * @param oj        (2) --
   * @param fieldName (3) --
   * @return --
   */
  public static Object getRumz(Class cls, Object oj, String fieldName) {
    if (cls != null && oj != null && fieldName != null) {
      Field field = TClass_01.getField(cls, fieldName);
      if (field != null) {
        return TField_01.getRumz_B(field, oj);
      }
    }
    return null;
  }
}
