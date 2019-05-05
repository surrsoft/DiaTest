package ru.surrsoft.baaz.univers;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.surrsoft.baaz.cls_c.G67G_Strings;

/**
 * Билдер строк
 * <p>
 * #version 1 01.12.2016  #id [[w403w]]
 * <p>
 * МЕТКИ: [neoi]
 */
public class BuString {

    private StringBuilder mSb;
    private String mDivider;
    private ArrayList<String> mWrapLs = new ArrayList<>();
    private ArrayList<String> mWrapRs = new ArrayList<>();
    private boolean mDisableValidVerify;
    private ArrayList<String> mWrapResultLs = new ArrayList<>();
    private ArrayList<String> mWrapResultRs = new ArrayList<>();

    public BuString() {
        mSb = new StringBuilder();
    }

    /**
     * Используется для разделения добавляемых подстрок
     *
     * @param d (1) --
     * @return --
     */
    public BuString divider(String d) {
        mDivider = d;
        return this;
    }

    /**
     * Указанными здесь символами будут "оборачиваться" добавляемые {@link #appendWrap(String)}
     * строки.
     * Можно вызывать несколько раз
     *
     * @param l_nullable (1) -- можно указать null
     * @param r_nullable (2) -- можно указать null
     * @return --
     */
    public BuString addWraper(String l_nullable, String r_nullable) {
        mWrapLs.add(l_nullable);
        mWrapRs.add(r_nullable);
        return this;
    }

    /**
     * Новые подстроки будут добавляться даже если они не проходят
     * функцию {@link G67G_Strings#isValid(CharSequence)}
     *
     * @return --
     */
    public BuString disableValidVerify() {
        mDisableValidVerify = true;
        return this;
    }


    public BuString prependWrap(String s) {
        boolean b = G67G_Strings.isValid(s);
        if (mDisableValidVerify) b = s != null;
        if (b) {
            if (mDivider != null && mSb.length() > 0) mSb.insert(0, mDivider);
            for (String elem : mWrapRs) if (elem != null && elem.length() > 0) mSb.insert(0, elem);
            if (s != null && s.length() > 0) mSb.insert(0, s);
            for (String elem : mWrapLs) if (elem != null && elem.length() > 0) mSb.insert(0, elem);
        }
        return this;
    }

    /**
     * Просто добавление, без оборачинвания, вне зависимости от того вызывались wrap... методы или нет
     *
     * @param s (1) --
     * @return --
     */
    public BuString append(String s) {
        if (s == null || s.length() == 0) return this;
        boolean b = G67G_Strings.isValid(s);
        if (mDisableValidVerify) b = s != null;
        if (b) {
            if (mDivider != null && mSb.length() > 0) {
                mSb.append(mDivider);
            }
            mSb.append(s);
        }
        return this;
    }

    /**
     * Добавляет подстроку оборачивая ее обертками заданными через {@link #addWraper(String, String)}
     * <p>
     * По умолчанию новые подстроки добавляются только если они проходят функцию
     * {@link G67G_Strings#isValid(CharSequence)} - см. {@link #disableValidVerify()}
     * <p>
     *
     * @param s (1) --
     * @return --
     */
    public BuString appendWrap(String s) {
        boolean b = G67G_Strings.isValid(s);
        if (mDisableValidVerify) b = s != null;
        if (b) {
            if (mDivider != null && mSb.length() > 0) mSb.append(mDivider);
            mSb.append(wrapAndAppend(s));
        }
        return this;
    }

    /**
     * Оборачивает результат в (1) и (2). Можно вызывать несколько раз
     *
     * @param l_nullable (1) --
     * @param r_nullable (2) --
     * @param if0        (3) -- текущий метод выполняет свою функцию только если if0 == TRUE
     * @return --
     */
    public BuString addWraperResult(String l_nullable, String r_nullable, boolean if0) {
        if (if0) {
            mWrapResultLs.add(l_nullable);
            mWrapResultRs.add(r_nullable);
        }
        return this;
    }

    public BuString appendPair(String key, String value) {
        append("\"" + key + "\":\"" + value + "\"");
        return this;
    }

    @Override
    public String toString() {
        String s = mSb.toString();
        boolean b = mDisableValidVerify || G67G_Strings.isValid(s);
        if (b) {
            for (String elem : mWrapResultLs)
                if (elem != null && elem.length() > 0) mSb.insert(0, elem);
            for (String elem : mWrapResultRs)
                if (elem != null && elem.length() > 0) mSb.append(elem);
            s = mSb.toString();
        }
        return s;
    }

    @NonNull
    private String wrapAndAppend(String s) {
        StringBuilder sb03 = new StringBuilder();
        for (String elem : mWrapLs) sb03.append(elem);
        sb03.append(s);
        for (String elem : mWrapRs) sb03.append(elem);
        return sb03.toString();
    }

}
