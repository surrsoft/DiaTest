package ru.surrsoft.baaz.univers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import ru.surrsoft.baaz.SomeException;

/**
 * [[ichx]] - структура вида "список" с _элементами вида _ключ/_заголовок/_значение.
 * Используется для отображения набора _параметров (каждый из параметров имеет _заголовок и _значение)
 * <p>
 * ПОНЯТИЯ
 * <li> _элемент или _elem - сущность вида _ключ/_заголовок/_значение </li>
 * <li> _ключ или _key - модельная сущность ([ievz]) элемента. _Ключи должны быть уникальны  </li>
 * <li> _заголовок или _title - отображаемая сущность ([uxir]) _заголовка _параметра   </li>
 * <li> _значение или _value - отображаемая сущность ([uxir]) _значения _параметра   </li>
 * <li> _параметр или _param - сущность состоящая из _заголовка и _значения, например "Имя: Евгений", где "Имя" это
 * _заголовок, "Евгений" - это _значение</li>
 * <p>
 * <p>
 * _key являются элементами итератора
 * <p>
 * [zipc]
 */
public class Ichx implements Iterable<String> {

    private final LinkedHashMap<String, String> _titles;
    private final LinkedHashMap<String, String> _values;
    private final ArrayList<String> _keys;

    //==============================================================================================
    public Ichx() {
        _keys = new ArrayList<>();
        _titles = new LinkedHashMap<>();
        _values = new LinkedHashMap<>();
    }

    /**
     * Добавление нового _элемента
     *
     * @param _key   (1) --
     * @param _title (2) --
     * @param _value (3) -- начальное значение
     */
    public void addElem(String _key, String _title, String _value) {
        if (_keys.indexOf(_key) != -1) {
            throw new SomeException("(debug) _ключ " + _key + " уже используется");
        }
        _keys.add(_key);
        _titles.put(_key, _title);
        _values.put(_key, _value);
    }

    public String getTitleByKey(String key){
        return _titles.get(key);
    }

    public String getValueByKey(String key){
        return _values.get(key);
    }

    //iterator
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return _keys.size() > 0 && index < _keys.size();
            }

            @Override
            public String next() {
                return _keys.get(index++);
            }

            @Override
            public void remove() {

            }
        };
    }


}
