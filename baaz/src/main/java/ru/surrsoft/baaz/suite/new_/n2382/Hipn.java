package ru.surrsoft.baaz.suite.new_.n2382;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.surrsoft.baaz.cls_c.G67G_Reflect;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.suite.terms.w359w.EPead;
import ru.surrsoft.baaz.suite.terms.w359w.TIvip_main;
import ru.surrsoft.baaz.tclasses.TField_01;
import ru.surrsoft.baaz.univers.ESort;
import ru.surrsoft.baaz.univers.Uehd;

/**
 * [[hipn]]
 * <p>
 * //````````````````````````````````````````````
 * ОПИСАНИЕ-1:
 * f(t)->m
 * <p>
 * где
 * t - SomeClass.class;
 * f - {@link #make(Class)};
 * m - {@link Paow}
 * <p>
 * //````````````````````````````````````````````
 * ОПИСАНИЕ-2:
 * Выполняет проход по описательной части класса (^[vtru]) "t" на всю глубину его дерева.
 * Грубо говоря - делает класс "плоским". На выходе получаем {@link Paow} .
 * Обладает защитой от "зацикливания".
 * <p>
 * //````````````````````````````````````````````
 * ИСПОЛЬЗОВАНИЕ:
 * настроить билдерами, вызвать {@link #make(Class)} передав класс который нужно обработать. На
 * выходе получаем тип {@link Paow} который является оберткой над массивом из Ahaz
 * <p>
 * //````````````````````````````````````````````
 * ОПЦИИ:
 * {@link #buOnlyAnn(Class)};
 * {@link #buSortByAnn(Class, ESort)}
 * <p>
 * СВЯЗИ: терминология {@link TIvip_main}
 * <p>
 * [[N2382]], [[w382w]], [[hipn]]
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class Hipn implements Uehd {

  private Ahaz[] ahazs;
  private int mLevel;
  //=== защита от зацикливания
  private HashMap<Class, Void> hm1;
  private HashMap<Class, Void> hm2;
  //===
  private Class mAnn;
  private Class mSortAnn;
  private ESort mSortAscDesc;
  private boolean vdin43;


  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public Hipn() {

  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Будут рассматриваться только _поля помеченные аннотацией (1)
   *
   * @param annotationClass (1) --
   * @return --
   */
  public Hipn buOnlyAnn(Class annotationClass) {
    mAnn = annotationClass;
    return this;
  }

  /**
   * Отсортировать результат по аннотации (1)
   *
   * @param annOrder (1) -- любая аннотация обладающая методом "int ariw()"
   * @param ascDesc  (2) -- направление сортировки
   * @return --
   */
  public Hipn buSortByAnn(Class annOrder, ESort ascDesc) {
    mSortAnn = annOrder;
    mSortAscDesc = ascDesc;
    return this;
  }

  //make
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @param clazz (1) --
   * @return --
   */
  public Paow make(Class clazz) {
    //--- [vdin]
    Vdin_01.log("создание [paow] для class=" + clazz.getSimpleName() + "; [paow] - выпрямленное " +
      "('плоское') представление [wira]", Vdin_01.E.START, this);
    if (mAnn != null) {
      Vdin_01.log("^ опция: смотрятся только поля с аннотацией "
        + mAnn, Vdin_01.E.MIDDLE, this);
    }
    if (mSortAnn != null) {
      Vdin_01.log("^ опция: выполняется сортировка в соответствии с аннотацией "
          + mSortAnn + "; направление сортировки: " + mSortAscDesc,
        Vdin_01.E.MIDDLE, this);
    }
    //---
    ahazs = new ru.surrsoft.baaz.suite.new_.n2382.Ahaz[]{};
    mLevel = -1;
    hm1 = new HashMap<Class, Void>();
    hm2 = new HashMap<Class, Void>();
    //==
    Vdin_01.log("info: запуск рекурсивного метода", Vdin_01.E.MIDDLE, this);
    recurs(clazz, null, null);                  //<=== рекурсия; заполняется ahazs
    //-
    if (!vdin43) {
      Vdin_01.log("info: зацикливаний типов не встречено", Vdin_01.E.MIDDLE, this);
    } else {
      vdin43 = false;
    }
    //== сортировка
    if (mSortAnn != null && ahazs.length > 1) {
      Vdin_01.log("variant: сортировка", Vdin_01.E.MIDDLE, this);
      mtSort();
    }
    //==
    Paow paow = new Paow();
    paow.ahazsSet(ahazs);
    Vdin_01.log("info: количество элементов в [paow] - " + ahazs.length, Vdin_01.E.MIDDLE, this);
    Vdin_01.log("", Vdin_01.E.END, this);
    return paow;
  }


  //``````````````````````````````````````````````````````````````````````````````````````````````
  private void recurs(Class clazz, Field fieldParent, ru.surrsoft.baaz.suite.new_.n2382.Ahaz ahazParent) {
    mLevel++;
    hm1.put(clazz, null);
    for (Field eField : clazz.getFields()) {
      if (mAnn != null && !eField.isAnnotationPresent(mAnn)) {
        continue;
      }
      if (G67G_Reflect.isMnae(eField)) {
        addElem(
          clazz,
          eField,
          fieldParent,
          prmIsTsnw(false),
          mLevel,
          EPead.MNAE,
          ahazParent
        );
      } else if (G67G_Reflect.isMwuw(eField)) {
        ru.surrsoft.baaz.suite.new_.n2382.Ahaz ahaz = addElem(
          clazz,
          eField,
          fieldParent,
          true,
          mLevel,
          EPead.MWUW,
          ahazParent
        );
        Class type = TField_01.dboeGet(eField);
        if (!hm1.containsKey(type)) {
          recurs(type, eField, ahaz);       //<=== RECURSION
          mLevel--;
        }
        hm2.put(type, null);
      } else if (G67G_Reflect.isUeun(eField)) {
        ru.surrsoft.baaz.suite.new_.n2382.Ahaz parent = addElem(
          clazz,
          eField,
          fieldParent,
          true,
          mLevel,
          EPead.UEUN,
          ahazParent
        );
        Class type = G67G_Reflect.getTypeParametrOfGeneric(eField);
        if (!hm1.containsKey(type)) {
          recurs(type, eField, parent); //<=== RECURSION
          mLevel--;
        } else {
          vdin43 = true;
          Vdin_01.log("случай: дан отпор зацыкливанию (type = " + type.getSimpleName() +
            ")", Vdin_01.E.MIDDLE, this);
        }
        hm2.put(type, null);
      }
    }
    for (Map.Entry<Class, Void> elem : hm2.entrySet()) {
      hm1.put(elem.getKey(), null);
    }
  }


  /**
   * Сортировка {@link #ahazs}  в зависимости от {@link #mSortAnn}
   * <p>
   * ДЛЯ ПОИСКА: [сортировка], [order]
   */
  private void mtSort() {
    List<ru.surrsoft.baaz.suite.new_.n2382.Ahaz> list = Arrays.asList(ahazs);
    Collections.sort(list, new Comparator<ru.surrsoft.baaz.suite.new_.n2382.Ahaz>() {
      @Override
      public int compare(ru.surrsoft.baaz.suite.new_.n2382.Ahaz lhs, ru.surrsoft.baaz.suite.new_.n2382.Ahaz rhs) {
        if (lhs.field.isAnnotationPresent(mSortAnn) && rhs.field.isAnnotationPresent(mSortAnn)) {
          Integer orderLhs = G67G_Reflect.<Integer>getValueAnnotationField(lhs.field, mSortAnn, "value");
          Integer orderRhs = G67G_Reflect.<Integer>getValueAnnotationField(rhs.field, mSortAnn, "value");
          int l = orderLhs == null ? 0 : orderLhs;
          int r = orderRhs == null ? 0 : orderRhs;
          int res = 0;
          if (l > r) res = 1;
          if (l < r) res = -1;
          if (mSortAscDesc == ESort.DESC) res = res * -1;
          return res;
        }
        return 0;
      }
    });
    ahazs = list.toArray(new ru.surrsoft.baaz.suite.new_.n2382.Ahaz[list.size()]);
  }

  /**
   * @param cls
   * @param field
   * @param fieldParent
   * @param isTsnw
   * @param level
   * @param et
   * @param ojParent
   * @return
   */
  private ru.surrsoft.baaz.suite.new_.n2382.Ahaz addElem(Class cls,
                                                         Field field,
                                                         Field fieldParent,
                                                         boolean isTsnw,
                                                         int level,
                                                         EPead et,
                                                         ru.surrsoft.baaz.suite.new_.n2382.Ahaz ojParent) {
    ru.surrsoft.baaz.suite.new_.n2382.Ahaz n = new ru.surrsoft.baaz.suite.new_.n2382.Ahaz();
    n.akef = cls;
    n.field = field;
    n.fieldParent = fieldParent;
    n.level = level;
    n.isTsnw = isTsnw;
    n.pead = et;
    n.ahazParent = ojParent;
    ahazs = ArrayUtils.add(ahazs, n);
    return n;
  }

  private boolean prmIsTsnw(boolean b) {
    return b;
  }

}
