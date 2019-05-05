package ru.surrsoft.baaz.widgets.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.configs.TextConfigs;
import ru.surrsoft.baaz.univers.EDialogPressBtn;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.IApply;
import ru.surrsoft.baaz.univers.N2284_Run;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Адаптированная под паттерн w265w (configer) версия класса {@link N2255_DialogUni}.
 * Предназначен для отображения только title и message.
 * Для получения результата используется презентер
 * <p/>
 * СЦЕНАРИИ
 * <li> -- инстанцирование -- запуск через {@link #n2284_run(Object)} ==> в этом случае отобразяться
 * только кнопки ОК и CANCEL</li>
 * <li> -- инстрацирование -- настройка {@link #_cfg} -- запуск через {@link #n2284_run(Object)}</li>
 * <li> -- инстрацирование -- подмена {@link #_cfg} -- запуск через {@link #n2284_run(Object)}</li>
 * <p/>
 * О _CFG
 * <li> -- элементы _cfg равные null обозначают что эти элементы не должны присутствовать
 * в диалоге</li>
 * <p/>
 * #version 1 09.07.2016  #id [[w289w]]
 */
public class N2255_2_DialogUni implements N2284_Run {

  private static final String TAG = ":" + N2255_2_DialogUni.class.getSimpleName();

  public AlertDialog.Builder _builder;
  public N2255_2_Configs _cfg;
  public LinearLayout _lay;
  public DialogInterface.OnClickListener _btnOkLst;
  public DialogInterface.OnClickListener _btnCancelLst;
  public DialogInterface.OnClickListener _btnNeutralLst;
  public Context _context;
  public N2255_2_Presenter _presenter;

  public interface N2255_2_Presenter {
    /**
     * Вызывается при нажатии одной из кнопок диалога
     *
     * @param ebtn   (1) -- enum олицетворяющий какая кнопка была нажата
     * @param someOj (2) -- объект переданный ранее в конструктор this
     */
    void n2255_2_on(EDialogPressBtn ebtn, Object someOj);
  }

  /**
   * @param ctx    (1) --
   * @param someOj (2) -- любой объект; он будет в неизменном виде передан презентеру при
   *               нажатии на любую из клавиш диалога; полезен например если нужно чтобы
   *               получатель результата диалога
   *               понимал с какой целью вызывался диалог (например для создания элемента, либо
   *               же для его редактирования)
   */
  public N2255_2_DialogUni(Context ctx, final Object someOj) {
    _context = ctx;
    _cfg = new N2255_2_Configs(ctx);
    _builder = new AlertDialog.Builder(ctx);
    _lay = new BuLayLinear_01(ctx).build();
    _btnOkLst = new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if (_presenter != null) _presenter.n2255_2_on(EDialogPressBtn.OK, someOj);
      }
    };
    _btnCancelLst = new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if (_presenter != null) _presenter.n2255_2_on(EDialogPressBtn.CANCEL, someOj);
      }
    };
    _btnNeutralLst = new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if (_presenter != null) _presenter.n2255_2_on(EDialogPressBtn.NEYTRAL, someOj);
      }
    };

  }

  public static class N2255_2_Configs implements IApply {
    public TextConfigs _title_tcf;
    public TextConfigs _message_tcf;
    public String _btnOk_text;
    public String _btnCancel_text;
    public String _btnNeutral_text;

    public N2255_2_Configs(Context ctx) {
      _btnOk_text = EStrings._OK.val();
      _btnCancel_text = EStrings._CANCEL.val();
      _title_tcf = new TextConfigs();
      _message_tcf = new TextConfigs();
    }

    @Override
    public void apply_w282w(Object view) {
      N2255_2_DialogUni d = (N2255_2_DialogUni) view;
      boolean b = false;
      if (_title_tcf != null) {
        TextView tvTitle = new TextView(d._context);
        _title_tcf.apply_w282w(tvTitle);
        d._lay.addView(tvTitle);
        b = true;
      }
      if (_message_tcf != null) {
        TextView tvMsg = new TextView(d._context);
        _message_tcf.apply_w282w(tvMsg);
        d._lay.addView(tvMsg);
        b = true;
      }
      if (b) d._builder.setView(d._lay);

      if (_btnOk_text != null) d._builder.setPositiveButton(_btnOk_text, d._btnOkLst);
      if (_btnCancel_text != null) d._builder.setNegativeButton(_btnCancel_text,
        d._btnCancelLst);
      if (_btnNeutral_text != null) d._builder.setNeutralButton(_btnNeutral_text,
        d._btnNeutralLst);
    }

    @Override
    public void commit_w282w() {

    }
  }

  /**
   * Запустить отображение диалога
   *
   * @param oj (1) -- null или любой объект
   * @return --
   */
  @Override
  public Object n2284_run(Object oj) {
    this._cfg.apply_w282w(this);
    if (_builder != null) {
      _builder.create().show();
    }
    return null;
  }

  /**
   * #самодокументирован
   *
   * @return --
   */
  public AlertDialog getAlertDialog() {
    if (_builder != null) {
      return _builder.create();
    }
    return null;
  }

}
