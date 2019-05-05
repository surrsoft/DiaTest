package ru.surrsoft.baaz.univers;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;

/**
 * Хранитель различных параметров могущих быть применимых например к TextView. Для применения
 * предназначен метод {@link #apply(TextView)}.
 * <p>
 * Содержит так же "транзитные" поля.
 * <p>
 * Может быть использован как альтернатива {@link BuilderTV}
 * <p>
 * #version 5 15-12-2016
 */
public class TwoColors {

    private int[] arr_int;

    /**
     * В this не используется - только для передачи в другие классы
     */
    private Drawable mDrawable = null;

    /**
     * Цвет для фона
     */
    @Nullable
    private Integer mColorBg = null;
    /**
     * Цвет для текста
     */
    @Nullable
    private Integer mColor = Color.BLACK;
    /**
     * Цвет для текста при нажатии
     */
    @Nullable
    private Integer colorPressed = null;
    /**
     * Толщина линии. В методе {@link #apply(TextView)} не применяется
     */
    @Nullable
    private Integer thDp;
    /**
     * Толщина линии при нажатии. В методе {@link #apply(TextView)} не применяется
     */
    @Nullable
    private Integer thDpPressed;
    /**
     * Цвет фона при нажатии
     */
    @Nullable
    private Integer colorBgPressed = null;
    private View.OnClickListener clickListener = null;
    /**
     * Шрифт
     */
    @Nullable
    private Typeface mTypeface = null;
    /**
     * Размер шрифта в sp
     */
    @Nullable
    private Integer size_sp = null;
    /**
     * Если TRUE то к тексту будет применено подчеркивание при вызове метода {@link #apply(TextView)}
     */
    private boolean isUnderline = false;
    /**
     * Если TRUE то к тексту будет применена жирность при вызове метода {@link #apply(TextView)}
     */
    private boolean isBold = false;
    /**
     * Если TRUE то к тексту будет применен наклон при вызове метода {@link #apply(TextView)}
     */
    private boolean isItalic = false;
    /**
     * Текст
     */
    @Nullable
    private String text = null;
    /**
     * Margins слева/сверху/справа/снизу
     */
    private int[] paddings_dp = new int[]{0, 0, 0, 0};

    //=== //constructors ===================================================================
    public TwoColors() {

    }

    public TwoColors(@Nullable Integer color, @Nullable Integer colorBg) {
        this.mColor = color;
        this.mColorBg = colorBg;
    }

    public TwoColors(@Nullable Integer color, @Nullable Integer colorBg, @Nullable Typeface typeface) {
        this.mColor = color;
        this.mColorBg = colorBg;
        this.mTypeface = typeface;
    }

    public TwoColors(@Nullable Integer color, @Nullable Integer colorBg, @Nullable Typeface typeface, @Nullable Integer size_sp) {
        this.mColor = color;
        this.mColorBg = colorBg;
        this.mTypeface = typeface;
        this.size_sp = size_sp;
    }

    public TwoColors(@Nullable Integer color, @Nullable Typeface typeface, @Nullable Integer size_sp) {
        this.mColor = color;
        this.mTypeface = typeface;
        this.size_sp = size_sp;
    }

    //==============================================================================================

    public int[] getArr_int() {
        return arr_int;
    }

    public TwoColors setArr_int(int[] arr_int) {
        this.arr_int = arr_int;
        return this;
    }

    @Nullable
    public Integer getColorBg() {
        return mColorBg;
    }

    public TwoColors colorBg(@Nullable Integer color) {
        this.mColorBg = color;
        return this;
    }

    @Nullable
    public Integer getColor() {
        return mColor;
    }

    public TwoColors color(@Nullable Integer c) {
        mColor = c;
        return this;
    }

    @Nullable
    public Integer getColorPressed() {
        return colorPressed;
    }

    /**
     * Цвет текста при нажатии
     *
     * @param colorPressed (1) --
     * @return --
     */
    public TwoColors colorPressed(@Nullable Integer colorPressed) {
        this.colorPressed = colorPressed;
        return this;
    }

    @Nullable
    public Integer getColorBgPressed() {
        return colorBgPressed;
    }

    /**
     * Цвет фона при нажатии. В методе {@link #apply(TextView)} не применяется
     *
     * @param colorBgPressed
     * @return
     */
    public TwoColors colorBgPressed(@Nullable Integer colorBgPressed) {
        this.colorBgPressed = colorBgPressed;
        return this;
    }

    @Nullable
    public Integer getThDp() {
        return thDp;
    }

    /**
     * В this не используется - только для передачи в другие классы
     *
     * @param dp
     * @return
     */
    public TwoColors setTh(@Nullable Integer dp) {
        this.thDp = dp;
        return this;
    }

    @Nullable
    public Integer getThDpPressed() {
        return thDpPressed;
    }

    /**
     * В this не используется - только для передачи в другие классы
     *
     * @param thDpPressed
     * @return
     */
    public TwoColors setThDpPressed(@Nullable Integer thDpPressed) {
        this.thDpPressed = thDpPressed;
        return this;
    }

    /**
     * Слушатель нажатия
     *
     * @return
     */
    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public TwoColors onclick(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }

    @Nullable
    public Typeface getTypeface() {
        return mTypeface;
    }

    public TwoColors typeface(@Nullable Typeface typeface) {
        this.mTypeface = typeface;
        return this;
    }

    @Nullable
    public Integer getFontSize_sp() {
        return size_sp;
    }

    public TwoColors fontSize(@Nullable Integer sp) {
        this.size_sp = sp;
        return this;
    }

    public boolean isBold() {
        return isBold;
    }

    public TwoColors bold() {
        this.isBold = true;
        return this;
    }

    public boolean isUnderline() {
        return isUnderline;
    }

    public TwoColors underline() {
        this.isUnderline = true;
        return this;
    }

    @Nullable
    public String getText() {
        return text;
    }

    public TwoColors text(@Nullable String text) {
        this.text = text;
        return this;
    }

    public int[] getPaddings_dp() {
        return paddings_dp;
    }

    public TwoColors paddings(int[] dp) {
        this.paddings_dp = dp;
        return this;
    }

    public boolean isItalic() {
        return isItalic;
    }

    /**
     * Наклонный текст
     *
     * @return --
     */
    public TwoColors italic() {
        this.isItalic = true;
        return this;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    /**
     * В this не используется - только для передачи в другие классы.
     * <p>
     * Используется для рисования SLD на фоне (см. {@link G67G_Draw#setBackgroundSLD(View, TwoColors)})  )
     *
     * @param drawable (1) --
     * @return --
     */
    public TwoColors setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
        return this;
    }

    /**
     * Установка цвета и шрифта для TextView
     *
     * @param tv
     */
    public void apply(TextView tv) {
        if (this.mColorBg != null) tv.setBackgroundColor(this.mColorBg);
        if (this.mColor != null) tv.setTextColor(this.mColor);
        if (this.mTypeface != null) tv.setTypeface(this.mTypeface);
        if (this.size_sp != null) tv.setTextSize(size_sp);
        if (this.text != null) tv.setText(this.text);
        //==
        SpannableString ss = null;
        CharSequence st = this.text == null ? tv.getText() : this.text;
        if (isUnderline) {
            if (ss == null) ss = new SpannableString(st);
            ss.setSpan(new UnderlineSpan(), 0, ss.length(), 0);
        }
        if (isBold) {
            if (ss == null) ss = new SpannableString(st);
            ss.setSpan(new StyleSpan(Typeface.BOLD), 0, ss.length(), 0);
        }
        if (isItalic) {
            if (ss == null) ss = new SpannableString(st);
            ss.setSpan(new StyleSpan(Typeface.ITALIC), 0, ss.length(), 0);
        }
        if (this.isUnderline || this.isBold) tv.setText(ss);
        //==
        if (this.colorPressed != null && this.mColor != null) {
            tv.setClickable(true);
            int[][] states = new int[][]{new int[]{android.R.attr.state_pressed}, // pressed
                    new int[]{}}; // по умолчанию
            int[] statesColors = new int[]{this.colorPressed, this.mColor};
            ColorStateList csl = new ColorStateList(states, statesColors);
            tv.setClickable(true);
            tv.setTextColor(csl);
        }
        //==
        if (this.paddings_dp.length == 4 && TArray_01.getValuesCount(this.paddings_dp, 0) != 4) {
            int[] arr = new int[]{};
            for (int val : paddings_dp) {
                arr = ArrayUtils.add(arr, G67G_Draw.px(val, Bysa_01.fDensity));
            }
            tv.setPadding(arr[0], arr[1], arr[2], arr[3]);
        }
        if (clickListener != null) {
            tv.setOnClickListener(clickListener);
        }
    }

}
