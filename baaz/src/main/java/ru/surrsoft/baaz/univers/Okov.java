package ru.surrsoft.baaz.univers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import ru.surrsoft.baaz.SomeException;

/**
 * Утилитные функции для работы с [okov]
 *
 * ПОНЯТИЯ:
 * ^[okov] - множество целых чисел; все числа уникальны; числа располагаются в возрастающем порядке
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class Okov {

    /**
     * Преобразование [okov] => [novd]
     *
     * @param okov (1) -- [okov]; если элементы не будут в возрастающем порядке то будет ошибка
     * @return [novd]
     */
    public static Map<Long, Long> okovAsNovd(Set<Long> okov) {
        Map<Long, Long> novd = new LinkedHashMap<>();
        //---
        boolean bFirstInvoke = true;
        long gPredyduschee = 0L;
        long gStartDiap = 0L;
        long gCt = 1L;
        long gDiv = 0L;
        //---
        for (Long gCurrent : okov) {
            if (bFirstInvoke) {
                bFirstInvoke = false;
                gStartDiap = gCurrent;
            } else {
                gDiv = gCurrent - gPredyduschee;
                if (gDiv == 1) {
                    gCt++;
                }
                if (gDiv > 1) {
                    novd.put(gStartDiap, gCt);
                    gStartDiap = gCurrent;
                    gCt = 1L;
                }
                if (gDiv < 1) {
                    throw new SomeException("(debug); gDiv=" + gDiv + "; mvrc=" + okov);
                }
            }
            gPredyduschee = gCurrent;
        }
        if (okov.size() > 0) {
            novd.put(gStartDiap, gCt);
        }
        //---
        return novd;
    }

    /**
     * Получение [hken] на базе [okov] (1) на _последовательности (2)
     *
     * @param okov  (1) -- [okov]
     * @param gUaotAwui (2) -- [awui] - длина _последовательности
     * @return -- [hken] в формате [novd]
     */
    public static Map<Long, Long> uaotGetHkenAsNovd(Set<Long> okov, Long gUaotAwui) {
        Map<Long, Long> novd = new LinkedHashMap<>();
        if (gUaotAwui > 0) {
            if (gUaotAwui == 1) {
                novd.put(0L, 1L);
            } else {
                long gStart = 0;
                long gCount = 0;
                boolean b1 = false;
                for (long number = 0; number < gUaotAwui; number++) {
                    if (!okov.contains(number)) {
                        if (!b1) {
                            gStart = number;
                            gCount = 1;
                            b1 = true;
                        } else {
                            gCount++;
                        }
                    } else {
                        if (b1) {
                            novd.put(gStart, gCount);
                            b1 = false;
                            gCount = 0;
                        }
                    }
                }
                if (b1) {
                    novd.put(gStart, gCount);
                }
            }
        }
        return novd;
    }
}
