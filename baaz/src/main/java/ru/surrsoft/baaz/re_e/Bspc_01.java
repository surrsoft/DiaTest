package ru.surrsoft.baaz.re_e;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import org.greenrobot.eventbus.EventBus;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.logs.Vdin_01;

/**
 * [[bspc]] - по сути это билдер активити
 * <p>
 * //````````````````````````
 * ЦЕЛЬ: упростить работу с активити:
 * 1) упростить ситуацию когда допустим в методе
 * onCreate() нужно создать соединение с БД, а в методе onDestroy() не забыть отпустить это
 * соединение. Для этого в текущем классе реализована привязка к жизненному циклу _активити через
 * использование фрагмента
 * 2) упростить типовые действия с активити, например включение кнопки "назад"
 * <p>
 * <p>
 * //````````````````````````
 * ОПЦИИ:
 * -- если активити должно работать с Ormlite, следует воспользоваться методом {@link
 * #buOrmlite(Class)}. В этот метод следует передать ссылку на класс своего кастомного хелпера
 * Ormlite. После этого объект хелпера следует получать с помощью метода {@link
 * #ormliteHelperGet()}; результат этого метода нужно будет привести к типу вашего
 * кастомного хелпера. Полученный хелпер будет автоматически отпускаться при срабатывании метода
 * onDestroy() _активити
 * -- {@link #buBackButton(boolean)} позволяет включить отображение кнопки "Назад" в action bar.
 * По
 * нажатию на эту кнопку текущий активити будет финиширован
 * -- {@link #buEventbus(Object)} позволяет задействовать EventBus
 * <p>
 * <p>
 * //````````````````````````
 * ЭЛЕМЕНТЫ:
 * -- _активити - активити к жизненному циклу которого привязывается текущий класс (см. {@link
 * #mActy} )
 * <p>
 * МЕТКИ:
 * [zipc]
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class Bspc_01 {
  private static final String TAG = ":" + Bspc_01.class.getSimpleName();

  private final Context mContext;

  private AppCompatActivity mActy;

  //Ormlite
  //```````````````````````````````````````````````

  /**
   * NULL здесь означает что функционал подключения Ormlite не задействован
   */
  private Class mBuOrmliteHelperCls;

  /**
   *
   */
  private OrmLiteSqliteOpenHelper mpublic_OrmliteHelper;

  //```````````````````````````````````````````````

  private boolean mBbackButtonEnable;
  /**
   * [obzm]
   */
  private String mStObzm;
  /**
   *
   */
  private Object mOjEventBus;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @param acty (1) -- _активити с жизненным циклом которого this будет связан
   */
  public Bspc_01(AppCompatActivity acty) {
    mContext = acty;
    mActy = acty;
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````


  /**
   * Следует передать через (1) Class если нужно реализовать взятие/освобождение хелпера Ormlite.
   * Иначе следует передать в (1) NULL либо совсем не вызывать текущий метод
   *
   * @param ormliteHelperClass (1) -- класс хелпера (^[wbez]); null если функциональность Ormlite
   *                           поддерживать не треубется
   * @return --
   */
  public Bspc_01 buOrmlite(Class ormliteHelperClass) {
    this.mBuOrmliteHelperCls = ormliteHelperClass;
    return this;
  }

  /**
   * Передать через (1) TRUE если нужно чтобы в ActionBar отображалась кнопка "Назад" слева, по
   * нажатию на которую активити {@link #mActy} должно финишироваться
   *
   * @param b (1) --
   * @return --
   */
  public Bspc_01 buBackButton(boolean b) {
    this.mBbackButtonEnable = b;
    return this;
  }

  /**
   * [obzm]
   *
   * @param obzm (1) --
   * @return --
   */
  public Bspc_01 buObzm(String obzm) {
    this.mStObzm = obzm;
    return this;
  }

  /**
   * @param oj (1) -- объект-подписчик на события
   * @return --
   */
  public Bspc_01 buEventbus(Object oj) {
    mOjEventBus = oj;
    return this;
  }


  //create
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public Bspc_01 create() {
    String st = "создание [bspc]; опции: ";
    if (mBuOrmliteHelperCls != null) st += "ortmlite";
    if (mBbackButtonEnable) st += ", back button";
    //-
    Vdin_01.log(st, Vdin_01.E.MIDDLE, this);
    Vdin_01.logI("[bspc] - билдер активити привязывающийся к его жизненному циклу", this);
    //--- проверки
    if (mActy == null) {
      throw new SomeException(mtObzmWrap("(debug)"));
    }

    //--- привязка
    MFragment mf = new MFragment();
    mf.bspc = this;
    if (mBbackButtonEnable) {
      //без этой инструкции не будут срабатывать методы onCreateOptionMenu,
      // onOptionsItemSelected, и возможно еще другие
      mf.setHasOptionsMenu(true);
    }
    mActy
      .getSupportFragmentManager()
      .beginTransaction()
      .add(mf, "bspcSomeTag50")
      .commit();

    //--- получение хелпера Ormlite
    if (mBuOrmliteHelperCls != null) {
      mpublic_OrmliteHelper = OpenHelperManager.getHelper(mContext, mBuOrmliteHelperCls);
    }

    //---
    if (mBbackButtonEnable) {
      ActionBar ab = mActy.getSupportActionBar();
      if (ab != null) {
        ab.setDisplayHomeAsUpEnabled(true);
      }
    }

    //---
    return this;
  }

  //
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Выполните кастинг к типу который вы передавали через {@link #buOrmlite(Class)}
   *
   * @return --
   */
  public OrmLiteSqliteOpenHelper ormliteHelperGet() {
    if (mBuOrmliteHelperCls == null) {
      throw new SomeException(mtObzmWrap("(debug) задайте необходимые параметры через билд-метод ormlite()"));
    }
    //---
    return mpublic_OrmliteHelper;
  }

  //MFragment


  /**
   * Фрагмент без UI для пристыковки к жизненному циклу активити
   * <p>
   * ПРИМЕЧНИЕ: публичным сделан по требованию IDE
   */
  public static class MFragment extends Fragment {

    public Bspc_01 bspc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Vdin_01.logM("onCreate()" + mtActyInfoGet(), this);
    }

    @Override
    public void onStart() {
      super.onStart();
      Vdin_01.logM("onStart()" + mtActyInfoGet(), this);
      //---
      if (bspc.mOjEventBus != null) {
        Vdin_01.logM("EventBus - зарегистрирован", this);
        EventBus.getDefault().register(bspc.mOjEventBus);
      }
    }

    @Override
    public void onResume() {
      super.onResume();
      Vdin_01.logM("onResume()" + mtActyInfoGet(), this);

    }

    @Override
    public void onPause() {
      super.onPause();
      Vdin_01.logM("onPause()" + mtActyInfoGet(), this);

    }

    @Override
    public void onStop() {
      super.onStop();
      Vdin_01.logM("onStop()" + mtActyInfoGet(), this);
      //---
      if (bspc.mOjEventBus != null) {
        Vdin_01.logM("EventBus - дерегистрирован", this);
        EventBus.getDefault().unregister(bspc.mOjEventBus);
      }
    }

    @Override
    public void onDestroy() {
      super.onDestroy();
      Vdin_01.logStart("onDestroy()" + mtActyInfoGet(), this);
      //--- освобождение Ormlite хелпера
      if (bspc.mBuOrmliteHelperCls != null) {
        boolean b47 = false;
        if (bspc.mpublic_OrmliteHelper != null) {
          OpenHelperManager.releaseHelper();
          bspc.mpublic_OrmliteHelper = null;
          b47 = true;
        }
        Vdin_01.logM("? освободил Ormlite хелпер - " + b47 + mtActyInfoGet(), this);
      }
      Vdin_01.logEnd("", this);
    }

    private String mtActyInfoGet() {
      return "; [" + bspc.mActy.getClass().getSimpleName() + "]$";
    }


    /**
     * Чтобы этот метод вызывался нужно на фрагенте выполнить метод .setHasOptionsMenu(true);
     *
     * @param item (1) --
     * @return --
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      int itemId = item.getItemId();
      //---
      if (bspc.mBbackButtonEnable) {
        if (itemId == android.R.id.home) {
          bspc.mActy.finish();
        }
      }

      //---
      return super.onOptionsItemSelected(item);
    }

  }

  //``````````````````````````````````````````````````````````````````````````````````````````````
  private String mtObzmWrap(String s) {
    if (mStObzm != null)
      return s + "; obzm=[" + mStObzm + "]";
    return s;
  }
}
