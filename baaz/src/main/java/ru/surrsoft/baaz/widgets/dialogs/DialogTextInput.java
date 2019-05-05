package ru.surrsoft.baaz.widgets.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.configs.TextConfigs;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.IApply;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Простой диалог с EditText
 * <p/>
 * СЦЕНАРИИ
 * <li> 1) инстанцирование -- вызов {@link #create()} ==> объект AlertDialog   </li>
 * <li> 2) инстанцирование -- настройка {@link #_cfg} -- вызов {@link #create()}
 * ==> объект AlertDialog </li>
 * <li> 3) инстанцирование -- подмена {@link #_cfg} своим экземпляром -- вызов {@link #create()}
 * ==> объект AlertDialog   </li>
 * <p/>
 */
public class DialogTextInput {

  private final EditText _edit;

  public Configs _cfg;
  public N2287_Presenter _presenter;

  private final Context mContext;
  private AlertDialog.Builder _b;
  private DialogInterface.OnClickListener lstBtnOk;
  private DialogInterface.OnClickListener lstBtnCancel;

  public interface N2287_Presenter {
    /**
     * Текст из _edit
     *
     * @param text
     */
    void n2287_ok(String text);
  }

  public DialogTextInput(Context context) {
    this.mContext = context;
    _cfg = new Configs();

    _edit = new EditText(context);
    _edit.setSelectAllOnFocus(true);
    _b = new AlertDialog.Builder(context);
    //---
    lstBtnOk = (dialog, which) -> {
      if (_presenter != null) _presenter.n2287_ok(_edit.getText().toString());
    };
    //---
    lstBtnCancel = (dialog, which) -> {
    };
  }

  public AlertDialog create() {
    _cfg.apply_w282w(this);
    AlertDialog a = _b.create();
    //чтобы клавиатура показывалась при запуске
    a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    return a;
  }

  /**
   * СТАРЫЕ НАЗВАНИЯ: "N2287_Configs"
   */
  public class Configs implements IApply {
    public String _edit_text;
    public String _title_text;
    public String _message_text;
    public TextConfigs _title_tcf;
    public TextConfigs _message_tcf;
    public String _btnOk_text = EStrings._OK.val();
    public String _btnCancel_text = EStrings._CANCEL.val();

    @Override
    public void apply_w282w(Object view) {
      DialogTextInput dti = (DialogTextInput) view;
      LinearLayout lay = new BuLayLinear_01(mContext).build();
      if (_title_text != null) {
        TextView title = new TextView(mContext);
        title.setText(_title_text);
        if (_title_tcf != null) _title_tcf.apply_w282w(title);
        lay.addView(title);
      }
      if (_message_text != null) {
        TextView message = new TextView(mContext);
        message.setText(_message_text);
        if (_message_tcf != null) _message_tcf.apply_w282w(message);
        lay.addView(message);
      }
      if (_btnOk_text != null) dti._b.setPositiveButton(_btnOk_text, lstBtnOk);
      if (_btnCancel_text != null) dti._b.setNegativeButton(_btnCancel_text, lstBtnCancel);

      if (_edit_text != null) _edit.setText(_edit_text);
      lay.addView(_edit);

      dti._b.setView(lay);
    }

    @Override
    public void commit_w282w() {

    }
  }
}
