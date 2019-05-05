package ru.surrsoft.baaz.w265w_configer;

import ru.surrsoft.baaz.configs.LinearLayoutConfigs;
import ru.surrsoft.baaz.configs.TextConfigs;
import ru.surrsoft.baaz.univers.LanguW;
import ru.surrsoft.baaz.widgets.dialogs.N2255_2_DialogUni;
import ru.surrsoft.baaz.widgets.switchers.N2302_TextViewSwitcher;

/**
 * Отправная точка
 * <p/>
 * #version 1 20.06.2016  #id [[w265Jw]]
 */
public enum N2265J_EForms {
    XARTICLES_CARDS_TAGS__LAY("id160619204400", LinearLayoutConfigs.class, "Статьи. Карточки. Теги : Слой"),
    XARTICLES_CARDS_TAGS__TEXT("id160616174100", TextConfigs.class, "Статьи. Карточки. Теги : Текст"),
    XDIALOGUNI_2_TEST("id160709114300", N2255_2_DialogUni.N2255_2_Configs.class, "ДИАЛОГ ТЕСТ"),
    LANG_STRINGS("w292w_languages", LanguW.class, "Локализованные строки"),
    TEST_STING_L("id160713190400", TextConfigs.class, "TEST_STING_L"),
    TEST_2("id160713193900", N2302_TextViewSwitcher.N2302_Configs.class, "TEST_2"),
    ;

    public final String descript;
    public final String key;
    public final Class clazz;

    N2265J_EForms(String key, Class clazz, String descript) {
        this.descript = descript;
        this.key = key;
        this.clazz = clazz;
    }
}
