package ru.surrsoft.baaz.widgets2.events;

/**
 * Техника [moru]
 *
 */

public class EvBoolean {
    /**
     * [[gefp]] - идентификатор источника события
     */
    public String gefp;

    /**
     *
     */
    public Boolean value;

    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public EvBoolean(String gefp, Boolean value) {
        this.gefp = gefp;
        this.value = value;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public String toString() {
        return "EvBoolean{" +
                "gefp=" + gefp +
                ", value=" + value +
                '}';
    }
}
