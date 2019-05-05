package ru.surrsoft.baaz.widgets.other;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.tclasses.TLayoutParams_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Билдер SeekBar
 * <p>
 * ОПЦИИ:
 * -- если указать label (см. {@link #buLabel}, то в layParent заданный через {@link #buAddTo(ViewGroup)},
 * будет вставлен не просто SeekBar, а LinearLayout внутри которого будет label (TextView) и под ним уже
 * сам SeekBar. При этом, в текст label будет автоматически подставляться текущий progress, если
 * строка label имеет место под вставку числа, например "Величина %d"
 * -- ещё можно передвать ссылку на любые TextView (см. {@link #buAddObserverTV(TextView)}). Начальный
 * текст этих TextView будед запомнен, а затем им будет подставлен этот текст, но с о значением progress
 * (если в тексте есть место под число, например "Величина: %d")
 */
public class BuSeekBar_01 {

  private final Context mContext;
  private int iProgressMax = 100;
  private int iProgressStart = 0;
  private int iProgressCurrent = iProgressStart;
  private ArrayList<View> chields = new ArrayList<>();
  private ViewGroup layParent;
  private ProgressListener progressListener;
  private Margins_01 margins;
  private Class clsLayoutParams;
  private int iW = ViewGroup.LayoutParams.MATCH_PARENT;
  private int iH = ViewGroup.LayoutParams.WRAP_CONTENT;
  private BuilderTV buLabel;
  private TextView vLabel;
  private String stLabelStartText;
  private ArrayList<TextView> tvObservers = new ArrayList<>();
  private ArrayList<String> listTvObserversStartTexts = new ArrayList<>();

  public BuSeekBar_01(Context ctx) {
    mContext = ctx;
  }

  public BuSeekBar_01 buProgressMax(int iMax) {
    this.iProgressMax = iMax;
    return this;
  }

  public BuSeekBar_01 buProgressStart(int iProgress) {
    this.iProgressStart = iProgress;
    this.iProgressCurrent = iProgress;
    return this;
  }

  public BuSeekBar_01 buAddTo(ViewGroup layParent) {
    this.layParent = layParent;
    return this;
  }

  public BuSeekBar_01 buProgressListener(ProgressListener pl) {
    this.progressListener = pl;
    return this;
  }

  /**
   * Если (1) != null, то при build() над SeekBar добавляется текстовый элемент (1), и SeekBar вместе с
   * этим текстовым элементом будут обёрнуты в LinearLayout
   *
   * @param builderTV (1) -- если содержит текст куда можно подставить число, то вместо этого числа
   *                  будет подставляться progress
   * @return --
   */
  public BuSeekBar_01 buLabel(BuilderTV builderTV) {
    this.buLabel = builderTV;
    return this;
  }

  /**
   * У всех добавленных сюда TextView будет обновляться их текст значением progress, если их начальный текст
   * имеет место под вставку цифры, например "Величина: %d"
   *
   * @param tvObserver (1) --
   * @return --
   */
  public BuSeekBar_01 buAddObserverTV(TextView tvObserver) {
    tvObservers.add(tvObserver);
    //-- сохраняем начальные тексты всех tvObserver
    listTvObserversStartTexts.add(((String) tvObserver.getText()));
    return this;
  }

  public interface ProgressListener {
    void f(int iProgress);
  }

  public BuSeekBar_01 buMargins(Margins_01 margins) {
    this.margins = margins;
    return this;
  }

  public BuSeekBar_01 buClsLayoutParams(Class cls) {
    this.clsLayoutParams = cls;
    return this;
  }

  public AppCompatSeekBar build() {
    AppCompatSeekBar ret = new AppCompatSeekBar(mContext);
    //---
    NLinearLayout layWrap = null;
    //оборачивание в слой с меткой над SeekBar
    if (buLabel != null) {
      layWrap = new BuLayLinear_01(mContext)
        .build();
      //--- vLavel
      vLabel = buLabel
        .addTo(layWrap)
        .create();
      stLabelStartText = (String) vLabel.getText();
      //---
      //---
      layWrap.addView(ret);
    }
    //---
    if (layParent != null) {
      if (buLabel == null) {
        layParent.addView(ret);
      } else {
        layParent.addView(layWrap);
      }
    }
    //---
    ret.setMax(iProgressMax);
    //---
    ret.setProgress(iProgressStart);
    mtLabelTextUpdate(iProgressStart);
    //---
    ret.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Vdin_01.logM("onProgressChanged", this);
        if (progressListener != null && fromUser) {
          iProgressCurrent = progress;
          progressListener.f(progress);
        }
        if (fromUser && vLabel != null) {
          mtLabelTextUpdate(progress);
        }
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        Vdin_01.logM("onStartTrackingTouch", this);

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        Vdin_01.logM("onStartTrackingTouch", this);

      }
    });
    //---
    if (margins != null) {
      TLayoutParams_01.verify_B(ret, clsLayoutParams, iW, iH, margins.l_dp, margins.t_dp, margins.r_dp, margins.b_dp);
    } else {
      TLayoutParams_01.verify(ret, clsLayoutParams, iW, iH);
    }
    //---
    return ret;
  }

  private void mtLabelTextUpdate(int progress) {
    if (vLabel != null) {
      vLabel.setText(String.format(stLabelStartText, progress));
    }
    //---
    for (int i = 0; i < tvObservers.size(); i++) {
      TextView tv = tvObservers.get(i);
      tv.setText(String.format(listTvObserversStartTexts.get(i), progress));
    }
  }

  public int getProgressCurrent() {
    return iProgressCurrent;
  }
}
