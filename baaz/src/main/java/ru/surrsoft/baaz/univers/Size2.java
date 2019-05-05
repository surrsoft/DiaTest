package ru.surrsoft.baaz.univers;

import android.view.ViewGroup;

/**
 * Представление для {@link ViewGroup.LayoutParams#MATCH_PARENT},
 * {@link ViewGroup.LayoutParams#WRAP_CONTENT}
 * <p/>
 * #version 1 19.06.2016  #id [[w278w]]
 */
public class Size2 {
    /**
     * Хранит null или одно из {@link ViewGroup.LayoutParams#MATCH_PARENT},
     * {@link ViewGroup.LayoutParams#WRAP_CONTENT}
     */
    public int val;

    public Size2() {
    }

    public Size2(int val) {
        this.val = val;
    }

    public Size2 matchParent(){
        this.val = ViewGroup.LayoutParams.MATCH_PARENT;
        return this;
    }

    public Size2 wrapContent(){
        this.val = ViewGroup.LayoutParams.WRAP_CONTENT;
        return this;
    }
}
