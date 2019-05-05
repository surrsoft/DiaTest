package ru.surrsoft.baaz.suite.terms;

/**
 * Это обобщение для:
 * <p>
 * {@link Tooptprimit} - обобщение для 8 примитивных типов: byte, short, int, long, float, double,
 * boolean, char
 * <p>
 * {@link Tooptsprimit} - примитивы + их обертки + String
 * <p>
 * {@link Tooptsimpl} - не генерик, не примитив, не массив (например "String")
 * <p>
 * {@link Tooptpopul} - не генерик, не примитив, не обертка примитива, не String, не массив, не wildcard
 * <p>
 * {@link Tooparr} - простой java-массив
 * <p>
 * {@link Tooptgeneric} - генерик тип
 * <p>
 * {@link Tooptpar} - параметр генерик типа
 * <p>
 * {@link Tooptwildcard} - wildcard
 * <p>
 * {@link Tooptcollect} - oбобщение для типизированной коллекции или простого java-массива
 *
 *
 */
public class Tooptype {
    private Tooptype() {
    }
}
