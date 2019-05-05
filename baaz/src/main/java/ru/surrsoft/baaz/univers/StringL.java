package ru.surrsoft.baaz.univers;

import android.support.annotation.Nullable;

import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.w265w_configer.ann.N2269_IsEnum;

/**
 * Специальное представление строки адаптированное под шаблон проектирования [w265w]
 * #version 1 12.07.2016  #id [[w295w]]
 */
public class StringL {
    public String text;

    /**
     * Хранит имя константы из {@link EStrings}
     */
    @N2269_IsEnum(clazzEnum = EStrings.class)
    public String name_enum;

    public Langu text_langu;

    public StringL() {
    }

    public StringL(String text, String name_enum, Langu text_langu) {
        this.text = text;
        this.name_enum = name_enum;
        this.text_langu = text_langu;
    }

    public StringL(String name_enum) {
        this.name_enum = name_enum;
    }

    /**
     * Получение строки либо из {@link #text} либо из {@link #name_enum} либо из {@link #text_langu}
     * (с таким приоритетом как здесь перечислено)
     *
     * @return --
     */
    @Nullable
    public String getString() {
        if (text != null) {
            return text;
        } else if (name_enum != null) {
            EStrings estr;
            try {
                estr = EStrings.valueOf(name_enum);
            } catch (IllegalArgumentException e) {
                G67G.assert_(false, "обращение к несуществующей константе EStrings:enum");
                return null;
            }
            return estr.val();
        } else if (text_langu != null) {
            return text_langu.getString();
        }
        return null;
    }
}
