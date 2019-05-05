package ru.surrsoft.baaz.widgets2.events;

/**
 * Техника [moru]
 *
 */

public class EvInt {
    /**
     * [gefp] - идентификатор источника события
     */
    public String gefp;

    /**
     *
     */
    public Integer value;

    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public EvInt(String gefp, Integer value) {
        this.gefp = gefp;
        this.value = value;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public String toString() {
        return "EvInt{" +
                "gefp=" + gefp +
                ", value=" + value +
                '}';
    }
}
