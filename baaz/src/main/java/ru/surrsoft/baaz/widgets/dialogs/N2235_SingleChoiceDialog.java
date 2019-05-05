package ru.surrsoft.baaz.widgets.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Позволяет назначить для любого view следующее действие - при нажатии на view появляется диалог
 * с одиночным выбором из списка элементов
 * <p/>
 * СЦЕНАРИИ ИСПОЛЬЗОВАНИЯ
 * <li> - создать экземпляр -- с помощью метода {@link #applyForView(View)} применить к какому-либо
 * view; </li>
 *
 * <p/>
 * #version 2 20.08.2016  #id [[w235w]]
 */
public class N2235_SingleChoiceDialog {



    public int startIndex;

    private final Context mContext;
    private final N2235_Presenter presenter;
    private String[] selectingElems;

    /**
     * @param selectingElems   (1) -- содержимое диалога (элементы выбора)
     * @param textElemNoSelect (2) -- текст для элемента "ничего не выбрано"; null если он не нужен
     * @param startIndex       (3) -- начальный индекс выбранного элемента; -1 если без выбора
     * @param context          (4) -- контекст
     * @param presenter        (5) -- получает обратные вызовы
     */
    public N2235_SingleChoiceDialog(@NonNull String[] selectingElems,
                                    String textElemNoSelect,
                                    int startIndex,
                                    @NonNull Context context,
                                    @Nullable N2235_Presenter presenter) {
        this.selectingElems = selectingElems;
        this.startIndex = startIndex;
        this.mContext = context;
        this.presenter = presenter;

        if (startIndex > this.selectingElems.length - 1) startIndex = -1;
        //добавление элемента "без выбора"
        this.selectingElems = ArrayUtils.add(this.selectingElems, 0, textElemNoSelect);
    }

    public interface N2235_Presenter {
        /**
         * Результат выбора. Вызывается при каждом check элемента списка
         *
         * @param index (1) -- индекс выбранного элемента; если -1 то значит ничего не выбрано
         */
        void onSelected(int index);

        /**
         * Вызывается по окончании диалога
         */
        void onCancel();
    }


    /**
     * Применение к view (1)
     *
     * @param view
     */
    public void applyForView(View view) {
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(mContext);
                b.setSingleChoiceItems(selectingElems, startIndex + 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startIndex = which - 1;
                        if (presenter != null) {
                            presenter.onSelected(which - 1);
                        }
                    }
                });
                AlertDialog a = b.create();
                a.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (presenter != null) {
                            presenter.onCancel();
                        }
                    }
                });
                a.show();
            }
        });
    }

}
