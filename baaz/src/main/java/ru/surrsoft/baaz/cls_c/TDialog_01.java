package ru.surrsoft.baaz.cls_c;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ru.surrsoft.baaz.Bysa_01;

/**
 * Диалоги разные
 */
public class TDialog_01 {

    /**
     * Показывает простой диалог с текстом (1) и кнопкой ОК
     *
     * @param message
     * @param context #version 1 11-05-2016
     */
    public static void alert(String message, Context context) {
        AlertDialog.Builder bld = new AlertDialog.Builder(context);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        bld.create().show();
    }

    /**
     * Отображает диалог с прокручивающимся списком (5)
     *
     * @param aTitle     (1) -- текст заголовка
     * @param aTxOk      (2) -- текст кнопки Positive; если null, то не
     *                   отображается
     * @param aTxCancel  (3) -- текст кнопки Negative; если null, то не
     *                   отображается
     * @param aTxNeytral (4) -- текст кнопки Neytral; если null, то не отображается
     * @param aArr       (5) -- список элементов
     */
    public static void showList(String aTitle, String aTxOk, String aTxCancel, String aTxNeytral,
                                ArrayList<String> aArr) {

        AlertDialog.Builder d = new AlertDialog.Builder(Bysa_01.appContext);

        if (aTitle != null && aTitle.length() > 0) {
            d.setTitle(aTitle);
        }

        final ArrayAdapter<String> a = new ArrayAdapter<String>(Bysa_01.appContext, android.R.layout.select_dialog_singlechoice);

        // добавление элементов в адаптер
        for (int i = 0; i < aArr.size(); i++) {
            a.add(aArr.get(i));
        }

        d.setAdapter(a, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(":info: press --- нажат элемент списка диалога ---");

            }
        });

        if (aTxOk != null && aTxOk.length() > 0) {
            d.setPositiveButton(aTxOk, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(":info: press --- нажата кнопка ОК диалога ---");

                }
            });
        }
        if (aTxCancel != null && aTxCancel.length() > 0) {
            d.setNegativeButton(aTxCancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(":info: press --- нажата кнопка Cancel диалога ---");

                }
            });
        }
        if (aTxNeytral != null && aTxNeytral.length() > 0) {
            d.setNeutralButton(aTxNeytral, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(":info: press --- нажат кнопа Neytral диалога ---");

                }
            });
        }

        d.show();
    }

    /**
     * Показ обычного диалога
     *
     * @param aTitle     (1) -- текст заголовка
     * @param aTxMessage (2) -- текст сообщения
     * @param aTxOk      (3) -- текст кнопки Positive; если null, то не
     *                   отображается
     * @param aTxCancel  (4) -- текст кнопки Negative; если null, то не
     *                   отображается
     * @param aTxNeytral (5) -- текст кнопки Neytral; если null, то не отображается
     */
    public static void showDialog(String aTitle, String aTxMessage, String aTxOk, String aTxCancel,
                                  String aTxNeytral) {

        AlertDialog.Builder d = new AlertDialog.Builder(Bysa_01.appContext);

        if (aTitle != null && aTitle.length() > 0) {
            d.setTitle(aTitle);
        }

        if (aTxMessage != null && aTxMessage.length() > 0) {
            d.setMessage(aTxMessage);
        }

        if (aTxOk != null && aTxOk.length() > 0) {
            d.setPositiveButton(aTxOk, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(":info: press --- нажата кнопка ОК диалога ---");

                }
            });
        }
        if (aTxCancel != null && aTxCancel.length() > 0) {
            d.setNegativeButton(aTxCancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(":info: press --- нажата кнопка Cancel диалога ---");

                }
            });
        }
        if (aTxNeytral != null && aTxNeytral.length() > 0) {
            d.setNeutralButton(aTxNeytral, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(":info: press --- нажат кнопа Neytral диалога ---");

                }
            });
        }

        d.show();
    }

}
