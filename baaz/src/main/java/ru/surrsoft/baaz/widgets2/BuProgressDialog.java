package ru.surrsoft.baaz.widgets2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;

import ru.surrsoft.baaz.cls_c.G67G_Dates;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;

/**
 * Билдер progress dialog. Отображается модальный виджет у которого слева крутится "прогресс"
 * //``````````````````````````````````
 * ИСПОЛЬЗОВАНИЕ: этому билдеру обязательно нужно передать _задачу (см. {@link #task(Runnable)}.
 * Эта _задача будет выполнятся в фоновом потоке. Диалог автоматически завершается по оканчании
 * _задачи. Диалог модальный.
 * //``````````````````````````````````
 * ОПЦИИ:
 * -- можно задать _минимальное-время-показа (см. {@link #minTime(int)})
 * //``````````````````````````````````
 * ПОНЯТИЯ:
 * -- _задача - см. {@link #task(Runnable)}
 * -- _минимальное-время-показа - см. {@link #minTime(int)}
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-07 (stored)
 */
public class BuProgressDialog {
    private static final String TAG = ":" + BuProgressDialog.class.getSimpleName();


    private final Context mContext;
    private String stTitle;
    private String stMessage = EStrings._ОБРАБОТКА.val(ETextCase.FUC);
    private Runnable task;
    private long mGMinTime = 1000;
    private boolean bCancelable;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public BuProgressDialog(Context ctx) {
        mContext = ctx;
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * @param str (1) --
     * @return --
     */
    public BuProgressDialog title(String str) {
        this.stTitle = str;
        return this;
    }

    /**
     * По умолчанию используется текст "Обработка..."
     *
     * @param str (1) -- null если текст не нужен
     * @return --
     */
    public BuProgressDialog message(String str) {
        this.stMessage = str;
        return this;
    }

    /**
     * {ОБЯЗАТЕЛЬНОЕ} Указанная здесь задача будет выполнятся в фоновом потоке. По окончании задачи
     * диалог будет остановлен
     *
     * @param task (1) --
     * @return --
     */
    public BuProgressDialog task(Runnable task) {
        this.task = task;
        return this;
    }

    /**
     * _минимальное-время-показа - {есть значение по умолчанию} минимальное время в течение которого
     * диалог
     * будет отображаться не
     * смотря на то сколько фактически времени требуется на выполнение _задачи
     *
     * @param iMsec (1) -- время в милисекундах; 1000 по умолчанию; задать 0 чтобы отключить эту
     *              опцию
     * @return --
     */
    public BuProgressDialog minTime(int iMsec) {
        this.mGMinTime = iMsec;
        return this;
    }

    /**
     * По умолчанию диалог нельзя закрыть нажатием на кнопку "Назад" ("Back"). Если же указать TRUE
     * в (1) - это станет возможным
     *
     * @param b (1) --
     * @return --
     */
    public BuProgressDialog cancelable(boolean b) {
        this.bCancelable = b;
        return this;
    }

    //interfaces
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public interface Back {
        /**
         * Вызывается при dismiss диалога (завершении работы диалога). Вызов выполняется в main thread
         */
        void onDialogDismiss();
    }


    //
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * @param _back (1) -- {опциональное} переданный сюда объект получит вызыв при dismiss диалога.
     *              Вызов будет в main thread
     * @return --
     */
    public ProgressDialog createAndShow(@Nullable final Back _back) {
        if (task == null) {
            throw new SomeException("(debug)");
        }
        //---
        final ProgressDialog dialog = new ProgressDialog(mContext);
        //--- back
        if (_back != null) {
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    _back.onDialogDismiss();
                }
            });
        }
        //--- cancelable
        dialog.setCancelable(bCancelable);
        //--- title
        if (stTitle != null) {
            dialog.setTitle(stTitle);
        }
        //--- message
        if (stMessage != null) {
            dialog.setMessage(stMessage);
        }
        //---
        dialog.show();
        //--- thread
        new Thread(new Runnable() {                                     //<=== THREAD
            @Override
            public void run() {
                long gTimeStart = G67G_Dates.getTimestamp();
                //---
                task.run();                                            //<=== TASK

                //---
                if (mGMinTime > 0) {
                    //^ реализация опции _минимальное-время-показа
                    long gTimeEnd = G67G_Dates.getTimestamp();
                    long g51 = gTimeEnd - gTimeStart;
                    if (g51 < mGMinTime) {
                        long g53 = mGMinTime - g51;
                        //---
                        try {
                            Thread.sleep(g53);                          //<=== SLEEP
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //---
                    }
                }


                dialog.dismiss();
            }
        }).start();
        //---
        return dialog;
    }


}
