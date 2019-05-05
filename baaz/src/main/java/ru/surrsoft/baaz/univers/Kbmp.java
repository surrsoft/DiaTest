package ru.surrsoft.baaz.univers;

/**
 * ЦЕЛЬ: для передачи параметра в метод
 *
 * ВЕРСИЯ 1 1.0 2019-02-07 (stored)
 */
public class Kbmp<T> {
    private Object[] arr = {null};

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````


    public Kbmp() {
    }

    public Kbmp(T oj) {
        arr[0] = oj;
    }

    //
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public void set(T oj) {
        arr[0] = oj;
    }

    public T get() {
        return (T) arr[0];
    }
}
