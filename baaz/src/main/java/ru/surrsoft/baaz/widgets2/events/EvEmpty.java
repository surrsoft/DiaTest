package ru.surrsoft.baaz.widgets2.events;

/**
 * Техника [moru]
 *
 */

public class EvEmpty {
    /**
     * [gefp] - идентификатор источника события
     */
    public String gefp;

    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public EvEmpty(String gefp) {
        this.gefp = gefp;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public String toString() {
        return "EvEmpty{" +
                "gefp=" + gefp +
                '}';
    }
}
