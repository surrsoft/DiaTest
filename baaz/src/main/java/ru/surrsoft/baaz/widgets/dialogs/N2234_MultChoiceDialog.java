package ru.surrsoft.baaz.widgets.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import ru.surrsoft.baaz.misc.Dwin;

/**
 * Позволяет назначить для любого view следующее действие - при нажатии на view появляется диалог
 * множественного выбора
 * <p/>
 * #version 1 23.04.2016  #id [[w234w]]
 */
public class N2234_MultChoiceDialog {


    private final Context mContext;
    private final String[] listSt;
    private final boolean[] bools;
    private final N2234_IPresenter presenter;
    public Dwin[] checkElems;

    /**
     * @param checkElems (1) -- содержимое диалога (элементы выбора)
     * @param context    (2) --
     */
    public N2234_MultChoiceDialog(@NonNull Dwin[] checkElems,
                                  @NonNull Context context,
                                  @Nullable N2234_IPresenter presenter) {
        this.checkElems = checkElems;
        this.mContext = context;
        this.presenter = presenter;

        listSt = Dwin.getStrings(checkElems);
        bools = Dwin.getCheckedElemsBoolean(checkElems);
    }

    public interface N2234_IPresenter {
        /**
         * Результат выбора. Вызывается при каждом check элемента списка
         *
         * @param checkElems (1) -- измененный список элементов выбора
         * @param index      (2) -- индекс измененного элемента
         */
        void onSelected(Dwin[] checkElems, int index);

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
                b.setMultiChoiceItems(listSt, bools, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkElems[which].check = isChecked;
                        if (presenter != null) {
                            presenter.onSelected(checkElems, which);
                        }
                    }
                });
                AlertDialog a = b.create();
                a.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if(presenter != null){
                            presenter.onCancel();
                        }
                    }
                });
                a.show();
            }
        });
    }

}
