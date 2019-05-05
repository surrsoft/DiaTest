package ru.surrsoft.baaz.univers;

import android.support.annotation.Nullable;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Глобальная коллекция локализованных строк
 * <p>
 * ИСПОЛЬЗОВАНИЕ:
 * <li> пример: EString._CANCEL.val() - будет получена строка на текущем языке выбранном в Android </li>
 * <li> пример: EString._CANCEL.val(ETextCase.UC) - получение строки в верхнем регистре </li>
 * <p>
 */
public enum EStrings {

  _OK("OK", "ОК"),
  _CANCEL("Cancel", "Отмена"),
  _COPY("Copy", "Копировать"),
  _PASTE("Paste", "Вставить"),
  _ERROR("Error", "Ошибка"),
  _DUBLICATE("Dublicate", "Дублировать"),
  _DUBLICATE_NEARBY_DOWN("Dublicate nearby down", "Дублировать рядом вниз"),
  _DUBLICATE_NEARBY_UP("Dublicate nearby up", "Дублировать рядом вверх"),
  _DUBLICATE_TOEND_LIST("Dublicate to end list", "Дублировать в конец списка"),
  _DUBLICATE_TOSTART_LIST("Dublicate to start list", "Дублировать в начало списка"),
  _DELETE("Delete", "Удалить"),
  _DELETE2("Deleting items", "Удаление элементов"),
  _DELETE3("%s items will be removed. Are you sure?", "%s элементов будут удалены. Вы уверены?"),
  _DELETE4("deletion", "удаление"),
  _SEARCH("Search", "Поиск"),
  _ADD("Add", "Создать"),
  _EDIT("Edit", "Редактировать"),
  _EDIT2("Edit", "Редактирование"),
  _EDIT3("Edit", "Изменить"),
  _CREATE("Create", "Создание"),
  _COUNT("Count", "Кол-во"),
  _SELECT("Select", "Выбрать"),
  _SELECT2("Select", "Выбор"),
  _SELECT3("select all", "выбрать все"),
  _SELECT_ALL("All", "Все"),
  _SELECT_INVERT("Invert", "Инвертировать"),
  _SELECT_INVERT_2("invert seleted", "инвертировать выбор"),
  _SELECT_DESELECT_ALL("Deselect all", "Сбросить выбор"),
  _STATE_1("State 1", "Состояние 1"),
  _STATE_2("State 2", "Состояние 2"),
  _STATE_3("State 3", "Состояние 3"),
  _SAVE("save", "сохранить"),
  _NO_MORE_ONE("Must not be selected more than 1 item", "Должно быть выбрано не более 1 элемента"),
  _NEED_ONE_SELECT("Must be selected 1 item", "Должен быть выбран 1 элемент"),
  _NEED_SELECTED("No selected items", "Не выбраны элементы"),
  _NO_SELECTED("No selected", "Не выбрано"),
  _ELEMS_COUNT("Items count: ", "Элементов: "),
  _SELECTED_COUNT("Selected: ", "Выбрано: "),
  _TYPE("Type", "Тип"),
  _TYPE2("Item type", "Тип элемента"),
  _LEVEL("Level", "Уровень"),
  _TYPE_TEXT("text", "текст"),
  _TYPE_NUMBER("number", "число"),
  _TYPE_DATE("date", "дата"),
  _TYPE_ONECHOICE("one choice list", "список одиночного выбора"),
  _TYPE_MULTCHOICE("multi choice list", "список множественного выбора выбора"),
  _NO_INTERNET("Absence/breakage of internet", "Отсутствие/обрыв интернета"),
  _NO_INTERNET_2("It seems there is no internet connection", "Кажется отсутствует интернет-подключение"),
  _NO_INTERNET_3("Download interrupted. Internet disconnection", "Загрузка прервана. Обрыв интернета"),
  _OR("or", "или"),
  _AND("and", "и"),
  _NOT("not", "не"),
  _ОТРИЦАНИЕ("NOT", "отрицание"),
  _FORMULA("formula", "формула"),
  _FILTERS("filters", "фильтры"),
  _YES("yes", "да"),
  _N_ITEMS("%s items", "%s записей"),
  _N_ITEMS_2("items: %s", "записей: %s"),
  _NO_DATA("no data", "данные отсутствуют"),
  _NO_DATE("without date", "без даты"),
  _ANY_DATE("any date", "любая дата"),
  _INVALID_NUMBER("invalid number", "неверное число"),
  _NEED_NUMBER_GREATER_OR_EQUAL_NIL("number must be greater than or equal to 0", "число дожно быть больше или равно 0"),
  _NEED_NUMBER_LESS("number must be less than ", "число дожно быть меньше "),
  _TEXT_NEED_NO_LINE_BREAKS("text must not contain line breaks symbols", "текст не должен содержать символов переноса строки "),
  _NAME("name", "имя"),
  _URL("url", "url"),


  _ОБРАБОТКА("working...", "обработка..."),

  _1_БОЛЬШЕ("greater", "больше"),
  _1_МЕНЬШЕ("less", "меньше"),
  _1_БОЛЬШЕ_ИЛИ_РАВНО("greater or equal", "больше или равно"),
  _1_МЕНЬШЕ_ИЛИ_РАВНО("less or equal", "меньше или равно"),
  _1_РАВНО("equal", "равно"),
  _1_НЕ_РАВНО("not equal", "не равно"),
  _1_СОДЕРЖИТ("contains", "содержит"),
  _1_НАЧИНАЕТСЯ_С("begins with", "начинается с"),
  _1_ЗАКАНЧИВАЕТСЯ_НА("ends on", "заканчивается на"),
  _1_ПУСТО("empty", "пусто"),
  _1_НЕ_ПУСТО("not empty", "не пусто"),

  _НЕ_ВЫБРАНО("no select", "не выбрано"),

  //region === billing
  _BILLING_SETUP_PROBLEM(
    "Sorry, is have problems an stage setup in-app billing. Please to verify settings your Google-account",
    "Извините, возникли проблемы на этапе настройки механизма встроенных покупок. " +
      "Проверьте пожайлуста настройки вашего Google-акаунта"
  ),
  _BILLING_INVENTORY_PROBLEM(
    "Sorry, is have problems an stage getting inventory. Please retry later",
    "Извините, возникли проблемы на этапе получения списка товаров. " +
      "Пожайлуста попробуйте повторить попытку позже"
  ),
  _BILLING_PURCHASE_PROBLEM(
    "Sorry, is have problems during purchase. Please retry later",
    "Извините, в ходе покупки возникли проблемы. " +
      "Пожайлуста попробуйте повторить попытку позже"
  ),
  _BILLING_DEBUG_1(
    "(DEBUG) SEE W473W",
    "(DEBUG) СМ. W473W"
  ),
  _BILLING_DEBUG_2(
    "(DEBUG) SEE W472W",
    "(DEBUG) СМ. W472W"
  ),
  //endregion

  //===
  N2286_2_1("Current is mode one selecting", "Сейчас режим одиночного выбора"),
  N2286_2_2("Current is mode editing", "Сейчас режим редактирования"),
  N1316_TYPE_HEADER("Header", "Заголовок"),
  N1316_TYPE_PLAIN("Plain text", "Простой текст"),
  N1316_LVL1("1", "1"),
  N1316_LVL2("2", "2"),
  N1316_LVL3("3", "3"),
  N1316_LVL4("4", "4"),
  N1316_LVL5("5", "5"),
  N1316_LVL6("6", "6"),
  N1316_SET_HEADERS("Appearance of headers", "Оформление заголовков"),
  N1316_SET_HEADERS_TITLE_DIALOG("Customize appearance of headers", "Настройка внешнего вида заголовков"),
  N1316_LVL_EXAMPLE("Header level", "Заголовок уровня"),

  N2438_ЗЕМУЧАСТОК("земельный участок", "земельный участок"),
  N2438_ЗДАНИЕ("здание", "здание"),
  N2438_ПОМЕЩЕНИЕ("помещение", "помещение"),
  N2438_СООРУЖЕНИЕ("инженерное сооружение", "инженерное сооружение"),

  G67G_001_001(ps("External storage is not available"),
    ps("Внешнее хранилище недоступно")),
  G67G_001_002(ps("Coping database file \"%s\" is not in storage"),
    ps("Файл базы данных для копирования \"%s\" отсутствует в хранилище")),
  G67G_001_003(ps("Coping database file \"%s\" is not readable"),
    ps("Файл базы данных для копирования \"%s\" нечитаем")),
  G67G_001_004(ps("Coping database file \"%s\" is not file - is folder"),
    ps("Файл базы данных для копирования \"%s\" не является файлом - является директорией")),
  G67G_001_005(ps("App database file \"%s\" is not exist"),
    ps("Файл базы данных приложения \"%s\" не существует")),
  G67G_001_006(ps("App database file \"%s\" is not readable"),
    ps("Файл базы данных приложения \"%s\" нечитаем")),
  G67G_001_007(ps("App database file \"%s\" is not file - is folder"),
    ps("Файл базы данных приложения \"%s\" не является файлом - является директорией"));


  public final String en;
  public final String ru;

  EStrings(String en, String ru) {
    this.en = en;
    this.ru = ru;
  }

  /**
   * Получение строки в соответствии с тем какая сейчас локаль на устройстве
   *
   * @return --
   */
  @NotNull
  public String val() {
    String ret = null;
    String language = Locale.getDefault().getLanguage();
    //---
    if (language.equals("en")) {
      //возвращаем содержимое поля "en" хранящего английский вариант
      ret = this.en;
    }
    if (language.equals("ru")) {
      //возвращаем содержимое поля "en" хранящего русский вариант
      ret = this.ru;
    }
    //---
    return ret == null ? "" : ret;
  }

  public String val(ETextCase e) {
    String s = val();
    switch (e) {
      case AS_IS:
        break;
      case UC:
        s = s.toUpperCase();
        break;
      case LC:
        s = s.toLowerCase();
        break;
      case FUC:
        s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        break;
    }
    return s;
  }

  /**
   * Добавление префикса и суфикса к строке (1)
   *
   * @param str (1) --
   * @return --
   */
  private static String ps(String str) {
    return "~!~ " + str + " ~!~";
  }

  /**
   * Превращает EStrings[] (1) в String[]
   *
   * @param estrings (1) --
   * @return
   */
  @Nullable
  public static String[] toStrings(EStrings[] estrings) {
    if (estrings == null) return null;
    String[] ret = {};
    for (EStrings elem : estrings) {
      ret = ArrayUtils.add(ret, elem.val());
    }
    return ret;
  }

  /**
   * Получение подмножества значений соответствующих (1)
   *
   * @param stEnek (1) -- [enek]
   * @return --
   */
  public static String[] getByEnek(String stEnek) {
    String[] ret = {};
    for (EStrings elem : EStrings.values()) {
      if (elem.name().contains(stEnek)) {
        ret = ArrayUtils.add(ret, elem.val());
      }
    }
    return ret;
  }

}
