package ru.surrsoft.baaz.univers;



/**
 * #version 1 17.06.2016  #id [[w276w]]
 */
public interface IApply {
    void apply_w282w(Object view);

    /**
     * Используется чтобы применить "перекрывающие" настройки (_наслоение или _ontoper - см. w265w) когда необходимо.
     * Вызывается к объекту после его получения из БД.
     */
    void commit_w282w();
}
