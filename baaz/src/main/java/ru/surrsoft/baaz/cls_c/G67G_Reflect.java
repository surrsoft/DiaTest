package ru.surrsoft.baaz.cls_c;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ru.surrsoft.baaz.SomeException;

/**
 * Работа с рефлексией
 * <p>
 * ПОНЯТИЯ:
 * <li> #[rumz] - значение (содержимое) поля объекта </li>
 * <li> #[[teso]] - полное имя класса, например "ru.evgeny.Cls1" </li>
 * <li> #[tufu] - простой тип: например "SomeClass" или "String" или "Integer" (т.е. не генерик, не примитив, не
 * массив) </li>
 * <li> #[uejo] - простой тип: тоже что [tufu] за исключением типа "String" и оберток примитивных типов ([eody]) </li>
 *
 * ВЕРСИЯ 1 20.06.2016 (stored)
 */
public class G67G_Reflect {



    /**
     * Возвращает TRUE если (1) это enum ([kuvd])
     *
     * @param wira (1) --
     * @return --
     */
    public static boolean isKuvd(Class wira) {
        return wira.isEnum();
    }

    /**
     * Возвращает TRUE если тип (1) это [adrf]-тип (генерализованный тобишь)
     *
     * @param wira (1) --
     * @return --
     */
    public static boolean isAdrf(Class wira) {
        return wira.getTypeParameters().length > 0;
    }

    /**
     * Предположим что тип ([ixet]) поля (1) это А&lt;B>.
     * Тогда данная функция вернет Class&lt;B>
     *
     * @param f (1) -- поле любого типа ([ixet])
     * @return -- null если:
   * <li> (1) это не генерик-тип (см. _Adrf.java) </li>
   * <li> (1) это генерик-тип с (см. TKgip.java)</li>
   * <li> (1) это генерик-тип с генерик-типом в качестве параметра типа (см. TVmha.java), например A&lt;B&lt;C>>  </li>
     */
    @Nullable
    public static Class<?> getTypeParametrOfGeneric(Field f) {
        return getTypeParametrOfGeneric(f.getGenericType());
    }

    /**
     * Возвращает параметр-тип генерик-типа (1)
     *
     * @param type (1) -- любой тип (в том числе не генерик-тип), например Collection&lt;String>
     * @return -- например Class&lt;String>, или null если:
     * <li> (1) это не генерик-тип (см. _Adrf.java) </li>
     * <li> (1) это генерик-тип с (см. TKgip.java)</li>
     * <li> (1) это генерик-тип с генерик-типом в качестве (см. TVmha.java), например A&lt;B&lt;C>>  </li>
     */
    @Nullable
    public static Class<?> getTypeParametrOfGeneric(Type type) {
        ParameterizedType pt;
        try {
            pt = (ParameterizedType) type;
        } catch (Exception e) {
            return null;
        }
        Class<?> cls = null;
        if (pt != null) {
            try {
                cls = (Class<?>) pt.getActualTypeArguments()[0];
            } catch (Exception e) {
                return null;
            }
        }
        return cls;
    }

    /**
     * Получение первого (или единственного если он один) тип-аргумента ([vhma]) у типа (1)
     *
     * @param cls (1) --
     * @return null при любых нештатах
     */
    public static Class getVmhaFirst(Class cls) {
        if (cls != null) {
            TypeVariable[] tps = cls.getTypeParameters();
            if (tps != null && tps.length > 0) {
                return tps[0].getClass();
            }
        }
        return null;
    }

    /**
     * Возвращает имена констант enum (1)
     *
     * @param classEnum (1) -- класс enum
     * @return пустой массив при нештатах
     */
    @NonNull
    public static String[] getEnumConstantNames(Class classEnum) {
        String[] ret = {};
        Object[] enumConstants = classEnum.getEnumConstants();
        if (enumConstants == null) return ret;
        List<Object> objects = Arrays.asList(enumConstants);
        for (Object elem : objects) {
            ret = ArrayUtils.add(ret, elem.toString());
        }
        return ret;
    }

    /**
     * Получение из типа (1) всех public static final констант типа (2)
     *
     * @param clazz         (1) --
     * @param clazzConstant (2) --
     * @return
     */
    @NonNull
    public static String[] getConstantNames(Class clazz, Class clazzConstant) {
        String[] ret = {};
        Field[] fields = clazz.getFields();
        for (Field elem : fields) {
            if (elem.getType().equals(clazzConstant)) {
                int m = elem.getModifiers();
                if (Modifier.isPublic(m) && Modifier.isFinal(m) && Modifier.isStatic(m)) {
                    ret = ArrayUtils.add(ret, elem.getName());
                }
            }
        }
        return ret;
    }

    /**
     * Копирование значений всех полей объекта (2) в соответствующие поля объекта (1).
     * (1) и (2) должны быть одного типа
     *
     * @param oj1 (1) --
     * @param oj2 (2) --
     *            #version 1 20.06.2016
     */
    public static void copyOjs(Object oj1, Object oj2) {
        Field[] fields = oj2.getClass().getFields();
        for (Field elem : fields) {
            try {
                elem.set(oj1, elem.get(oj2));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получение поля (2) объекта (1) по имени поля (2)
     *
     * @param oj        (1) -- объект
     * @param fieldName (2) -- имя поля объекта (1)
     * @return --
     */
    public static Field getFieldByName(Object oj, String fieldName) {
        try {
            if (oj != null) {
                return oj.getClass().getField(fieldName);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Запись в поле (2) объекта (1) значения (3)
     *
     * @param oj        (1) --
     * @param fieldName (2) --
     * @param value     (3) --
     */
    private static void setValueByFieldName(Object oj, String fieldName, Object value) {
        try {
            Field f = getFieldByName(oj, fieldName);
            f.set(oj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запись в поле (2) объекта (1) значения (3)
     * <p>
     * Метод необходим только для того чтобы вынести "наружу" try/catch
     *
     * @param oj    (1) --
     * @param field (2) --
     * @param value (3) --
     */
    public static void setValueByField(Object oj, Field field, Object value) {
        try {
            field.set(oj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение значения поля (2) объекта (1) в виде Object
     *
     * @param oj        (1) -- объект содежащий поле (2)
     * @param fieldName (2) -- имя поля
     * @return null при нештататах
     */
    public static Object getValueByFieldName(Object oj, String fieldName) {
        Object ret = null;
        try {
            Field f = oj.getClass().getField(fieldName);
            ret = f.get(oj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Получение значения поля (2) из объекта (1)
     *
     * @param oj    (1) -- объект которому принадлежит поле (2)
     * @param field (2) -- поле принадлежащее объекту (1)
     * @return --
     */
    public static Object getValueByField(Object oj, Field field) {
        Object ret = null;
        try {
            ret = field.get(oj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Возвращает значение поля (2) объекта (1) как строку. Поле (2) дожлно типом [toopsprimit]
     *
     * @param oj (1) -- объект содержащий поле (2)
     * @param f  (2) -- поле, должно иметь тип [toopsprimit]
     * @return null при нештатах
     */
    @Nullable
    public static String getValueToopsprimit(Object oj, Field f) {
        String s = null;
        if (isMnae(f)) {
            try {
                Object oj1 = f.get(oj);
                if (oj1 != null) {
                    s = oj1.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    /**
   * Возвращает TRUE если (1) является "одним из 8-ми примитивных типов" ([bwao])
     * <p>
     * $[bwao]
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isBwao(Field f) {
        return f.getType().isPrimitive();
    }

    /**
     * Возвращает TRUE если поле (1) является [eody]
     * <p>
     * $[eody]
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isEody(Field f) {
        Class<?> type = f.getType();
        return type.equals(Byte.class)
          || type.equals(Short.class)
          || type.equals(Integer.class)
          || type.equals(Float.class)
          || type.equals(Long.class)
          || type.equals(Double.class)
          || type.equals(Boolean.class)
          || type.equals(Character.class);
    }

    /**
     * Возвращает TRUE если типтокен (1) является [eody]
     * <p>
     * $[eody]
     *
     * @param type (1) --
     * @return --
     */
    public static boolean isEody(Class type) {
        return type.equals(Byte.class)
          || type.equals(Short.class)
          || type.equals(Integer.class)
          || type.equals(Float.class)
          || type.equals(Long.class)
          || type.equals(Double.class)
          || type.equals(Boolean.class)
          || type.equals(Character.class);
    }


    /**
     * Возвращает TRUE если поле (1) является ^[mnae]
     * <p>
     * $[mnae]
     *
     * @param f (1) -- поле-класса
     * @return --
     */
    public static boolean isMnae(Field f) {
        Class<?> type = f.getType();
        return f.getType()
          .isPrimitive()
          || isEody(f)
          || type.equals(String.class);
    }

    /**
     * Возвращает TRUE если [wira] (1) является [mnae]
     * <p>
     * $[mnae]
     * <p>
     * #[mnae] - обобщающее понятие для:
     * <ul>
     * <li> примитивных типов ([bwao])</li>
     * <li> оберток примитивных типов ([eody])</li>
     * <li> типа String</li>
     * </ul>
     *
     * @param wira (1) -- [wira] - типтокен
     * @return --
     */
    public static boolean isMnae(Class wira) {
        return wira
          .isPrimitive()
          || isEody(wira)
          || wira.equals(String.class);
    }

    /**
     * Возвращает TRUE если [wira] (1) является [ksnu]
     *
     * @param wira (1) --
     * @return --
     */
    public static boolean isKsnu(Class wira) {
        return isMnae(wira) || isKuvd(wira);
    }


    /**
     * Возвращает TRUE если поле (1) является [tufu]
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isTufu(Field f) {
        Class<?> type = f.getType();
        boolean b1 = !type.isPrimitive();
        boolean b3 = !isXamd(f);
        boolean b4 = !isAdrf(type);
        return b1 && b3 && b4;
    }

    /**
     * Возвращает TRUE если тип (1) является [tufu]
     *
     * @param cls (1) --
     * @return --
     */
    public static boolean isTufu(Class cls) {
        boolean b1 = !cls.isPrimitive();
        boolean b3 = !isXamd(cls);
        boolean b4 = !isAdrf(cls);
        return b1 && b3 && b4;
    }

    /**
     * Возвращает TRUE если поле (1) является [uejo]
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isUejo(Field f) {
        Class<?> type = f.getType();
        boolean b1 = isTufu(f);
        boolean b2 = !type.equals(String.class);
        boolean b3 = !isEody(f);
        return b1 && b2 && b3;
    }

    /**
     * Возвращает TRUE если тип (1) является [uejo]
     * <p>
     * #[[uejo]] - простой тип (т.е. не генерик, не примитив, не массив), но и не String и не
     * какая-либо из оберток примитивных типов. Например "Class", "Field"
     *
     * @param cls (1) --
     * @return --
     */
    public static boolean isUejo(Class cls) {
        boolean b1 = isTufu(cls);
        boolean b2 = !cls.equals(String.class);
        boolean b3 = !isEody(cls);
        return b1 && b2 && b3;
    }

    /**
     * Возвращает TRUE если (1) является простым массивом ([xamd])
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isXamd(Field f) {
        return f.getType().isArray();
    }

    /**
     * Возвращает TRUE если (1) является простым массивом ([xamd])
     *
     * @param cls (1) --
     * @return --
     */
    public static boolean isXamd(Class cls) {
        return cls.isArray();
    }

    /**
     * Возвращает TRUE если поле (1) является [coeh]
     * <p>
     * $[coeh]
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isCoeh(Field f) {
        return !isMnae(f);
    }

    /**
     * Возвращает TRUE если тип ([akef]) поля (1) is [mwuw]
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isMwuw(Field f) {
        boolean b1 = !G67G_Reflect.isMnae(f);
        boolean b2 = !f.getType().isAssignableFrom(Collection.class);
        boolean b3 = !f.getType().isArray();
        return b1 && b2 && b3;
    }

    /**
     * Возвращает TRUE если тип ([akef]) поля (1) is [ueun]
     *
     * @param f (1) --
     * @return --
     */
    public static boolean isUeun(Field f) {
        return f.getType().isAssignableFrom(Collection.class);
    }

    /**
     * Возвращает TRUE если тип (1) is [ueun]
     * <p>
     * #[[ueun]] - тип имплементирующий прямо или косвенно java.util.Collection
     *
     * @param wira (1) -- [wira]
     * @return --
     */
    public static boolean isUeun(Class wira) {
        return Collection.class.isAssignableFrom(wira);
    }


    public static boolean жКл1МожноПрименитьГдеПрименяетсяКл2(Class<?> кл1, Class<?> кл2) {
        return кл1.isAssignableFrom(кл2);
    }

    public static Class жКлВКоторомЗадекларированоПоле(Field f) {
        return f.getDeclaringClass();
    }

    /**
     * Получение значения элемента ([inko]) (3) аннотации (2) поля (1)
     * <p>
     * ПРИМЕР ИСПОЛЬЗОВАНИЯ: G67G_Reflect.<Integer>getValueAnnotationField(rhs.field, mSortAnn, "ariw");
     *
     * @param field    (1) -- поле класса предположительно аннотированное аннотацией (2)
     * @param annWira  (2) -- класс ([wira]) аннотации которой предположительно аннотировано поле (1)
     * @param vnon (3) -- [vnon] - имя элемента аннотации (2) значение которого мы хотим получить
     * @return [cwad] значение элемента аннотации
     */
    public static <T> T getValueAnnotationField(Field field, Class annWira, String vnon) {
        T res = null;
        Annotation annotation = field.getAnnotation(annWira);
        if (annotation != null) {
            Method method = null;
            try {
                method = annotation.getClass().getDeclaredMethod(vnon);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (method != null) {
                try {
                    res = (T) method.invoke(annotation);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    /**
     * Получение из объекта (1) значения первого встретившегося поля с аннотацией (2)
     *
     * @param oj  (1) -- объект
     * @param cls (2) -- класс аннотации
     * @return --
     */
    public static <T> T getValueByAnnField(Object oj, Class<? extends Annotation> cls) {
        if (oj == null || cls == null) return null;
        Field[] fields = oj.getClass().getFields();
        for (Field eField : fields) {
            if (eField.isAnnotationPresent(cls)) {
                T res;
                try {
                    res = (T) eField.get(oj);
                } catch (IllegalAccessException e) {
                    throw new SomeException("(debug) проблема при получении знаяения поля объекта");
                }
                return res;
            }
        }
        return null;
    }

    /**
     * Обновление у объекта (1) значения (значением (3)) первого встретившегося поля имеющего аннотацию (2)
     *
     * @param oj     (1) -- объект
     * @param clsAnn (2) -- класс аннотации
     * @param value  (3) -- значение которое нужно задать
     */
    public static void setValueByAnnField(Object oj, Class<? extends Annotation> clsAnn, Object value) {
        if (oj == null || clsAnn == null) throw new SomeException("(debug) неверные аргументы");
        for (Field eField : oj.getClass().getFields()) {
            if (eField.isAnnotationPresent(clsAnn)) {
                try {
                    eField.set(oj, value);
                    return;
                } catch (IllegalAccessException e) {
                    throw new SomeException("(debug) ошибка при записи в поле");
                }
            }
        }
        throw new SomeException("(debug) не найдено поле которое нужно обновить");
    }
}
