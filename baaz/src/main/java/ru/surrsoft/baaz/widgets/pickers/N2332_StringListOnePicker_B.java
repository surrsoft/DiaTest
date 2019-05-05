package ru.surrsoft.baaz.widgets.pickers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.configs.TextConfigs;
import ru.surrsoft.baaz.univers.EStrings;

/**
 * Picker одиночного выбора элемента из списка строк EStrings[]
 * <p/>
 * ВОЗМОЖНОСТИ:
 * <ul>
 * <li>есть опция "c/без элементом 'не выбрано'" (см. _cfg.noselectTextOrNull).
 * При наличии элемента "не выбрано" следует учитывать что возвращаемый индекс будет больше на 1.</li>
 * <li>можно сделать чтобы диалог скрывался сразу после выбора элемента (см. _cfg.isHideAfterSelect_defFalse)</li>
 * <li>можно вкл./выкл. показ кнопок (см. _cfg.isShowButtons_defTrue);</li>
 * <li>можно сделать чтобы не показывался диалог, а происходило
 * циклическое переключение между элементами при нажатии (см. _cfg.isCyclic) </li>
 * </ul>
 * <br/>
 * ИСПОЛЬЗОВАНИЕ:
 * <ul>
 * <li> + инстанцировать -- настроить {@link #_model} -- настроить {@link #_cfg} -- задать {@link #_presenter}
 * -- вызвать {@link #yCommit()}    </li>
 * </ul>
 * <br/>
 * ПРИМЕР: https://gist.github.com/surrsoft/195645f91152b1bba8992cd67a7e422d
 * <br/>
 * #version 1 20.08.2016  #id [[w332w]]
 */
public class N2332_StringListOnePicker_B extends android.support.v7.widget.AppCompatTextView {

    private static final String TAG = ":" + N2332_StringListOnePicker_B.class.getSimpleName();


    public N2332_Configs _cfg;
    public N2332_Presenter _presenter;
    public N2332_Model _model;

    private Context mContext;
    private int mSelectIndex;
    private int mSelectIndexLastOk;

    public interface N2332_Presenter {
        /**
         * Индекс выбранного элемента. Следует учитывать что он будет больше на 1 если включена опция
         * отображения пункта "не выбрано"
         *
         * @param n2332_index (1) --
         */
        void n2332_onSelected(int n2332_index);
    }

    //1 //constructors
    public N2332_StringListOnePicker_B(Context context) {
        super(context);
        init(context);
    }

    public N2332_StringListOnePicker_B(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2332_StringListOnePicker_B(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructors

    private void init(Context context) {
        mContext = context;
        _model = new N2332_Model();
        _cfg = new N2332_Configs();
    }

    public void yCommit() {
        mSelectIndex = _model.startIndex;
        mSelectIndexLastOk = _model.startIndex;
        //===
        String[] elems = EStrings.toStrings(_model.elems);
        if (_cfg.noselectTextOrNull != null) {
            elems = ArrayUtils.add(elems, 0, _cfg.noselectTextOrNull.val());
        }
        //===
        this.setText(elems[_model.startIndex]);
        if (_cfg.picker_tcf != null) {
            _cfg.picker_tcf.apply_w282w(this);
        }
        //===
        final String[] finalElems = elems;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_cfg.isCyclic_defFalse){
                    mSelectIndex++;
                    if(mSelectIndex == finalElems.length) mSelectIndex = 0;
                    m38(finalElems[mSelectIndex]);
                    return;
                }
                AlertDialog.Builder b = new AlertDialog.Builder(mContext);
                if (_cfg.titleText != null) {
                    b.setTitle(_cfg.titleText.val());
                }
                b.setSingleChoiceItems(finalElems, mSelectIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //этот if для предотвращения срабатывания презентера при нажатии на уже и так выбранный элемент
                        if (which != mSelectIndex) {
                            mSelectIndex = which;
                            if (!_cfg.isShowButtons_defTrue || _cfg.isHideAfterSelect_defFalse) {
                                m38(finalElems[which]);
                            }
                        }
                        if (_cfg.isHideAfterSelect_defFalse) {
                            dialog.dismiss();
                        }
                    }
                });
                if (_cfg.isShowButtons_defTrue) {
                    b.setPositiveButton(EStrings._OK.val(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mSelectIndexLastOk = mSelectIndex;
                            m38(finalElems[mSelectIndex]);
                        }
                    });
                    b.setNegativeButton(EStrings._CANCEL.val(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mSelectIndex = mSelectIndexLastOk;
                        }
                    });
                }
                b.create().show();
            }
        });
    }

    private void m38(String finalElem) {
        if (_presenter != null) _presenter.n2332_onSelected(mSelectIndex);
        N2332_StringListOnePicker_B.this.setText(finalElem);
        if (_cfg.picker_tcf != null) _cfg.picker_tcf.apply_w282w(this);
    }

    public static class N2332_Model{
        /**
         * Элементы которые будут отображаться в списке выбора
         */
        public EStrings[] elems;
        /**
         * Начальный индекс - 0+
         */
        public int startIndex;
    }

    public static class N2332_Configs {
        /**
         * Текст для пункта "не выбрано". Если NULL то этого пункта не будет
         */
        public EStrings noselectTextOrNull;
        /**
         * TRUE если нужно отображать кнопки OK/Cancel
         */
        public boolean isShowButtons_defTrue = true;
        /**
         * Текст заголовка
         */
        public EStrings titleText;
        /**
         * Оформление текста самого picker
         */
        public TextConfigs picker_tcf;
        /**
         * Если TRUE то диалог будет автоматически закрываться после выбора
         */
        public boolean isHideAfterSelect_defFalse;
        /**
         * Если TRUE то диалог показываться не будет, а будет выполняться циклическое переключение
         * между элементами
         */
        public boolean isCyclic_defFalse;

    }
}
