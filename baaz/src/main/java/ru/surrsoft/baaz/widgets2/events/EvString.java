package ru.surrsoft.baaz.widgets2.events;

/**
 * Техника [moru]
 *
 */

public class EvString {
    /**
     * [gefp] - идентификатор источника события
     */
    public String gefp;

    /**
     *
     */
    public String value;

    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public EvString(String gefp, String value) {
        this.gefp = gefp;
        this.value = value;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public String toString() {
        return "EvString{" +
                "gefp=" + gefp +
                ", value=" + value +
                '}';
    }
}
