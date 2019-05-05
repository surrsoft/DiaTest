package ru.surrsoft.baaz.widgets2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Конструктор диалога выбора даты
 * #version 1 27.11.2016  #id [[w401w]]
 * <p>
 * [zipc]
 */
public class BuilderDT implements DatePickerDialog.OnDateSetListener {
    private final Context mContext;

    public BuilderDT(Context context) {
        mContext = context;
    }

    public DatePickerDialog create() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(mContext, this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }

}
