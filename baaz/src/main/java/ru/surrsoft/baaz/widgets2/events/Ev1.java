package ru.surrsoft.baaz.widgets2.events;

/**
 *
 */
public class Ev1 {

    private int mEventId;
    private long mEventType;
    private boolean mValBoolean;
    private Object mVal;

    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public Ev1() {
    }

    public Ev1(long eventType, int eventId) {
        mEventId = eventId;
        mEventType = eventType;
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Ev1 eventType(long eventType) {
        mEventType = eventType;
        return this;
    }

    public Ev1 eventId(int eventId) {
        mEventId = eventId;
        return this;
    }

    public Ev1 valBoolean(boolean b) {
        mValBoolean = b;
        return this;
    }


    //``````````````````````````````````````````````````````````````````````````````````````````````
    public int getEventId() {
        return mEventId;
    }

    public long getEventType() {
        return mEventType;
    }

    public boolean getValBoolean() {
        return mValBoolean;
    }

    public Object getVal() {
        return mVal;
    }

    @Override
    public String toString() {
        return "Ev1{" +
                "mEventId=" + mEventId +
                ", mEventType=" + mEventType +
                ", mValBoolean=" + mValBoolean +
                '}';
    }
}
