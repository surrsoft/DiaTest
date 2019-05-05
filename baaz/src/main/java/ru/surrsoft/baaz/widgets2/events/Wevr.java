package ru.surrsoft.baaz.widgets2.events;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;


/**
 * [[wevr]]
 * <p>
 * Служит для отлавливания событий EventBus.
 * <p>
 * ФИШКИ ЭТОГО ОБЪЕКТА:
 * Этот объект пристыковывается к жизненному циклу активити.
 * При своем создании, либо при onStart() у активити, он автоматически регистрирует себя в EventBus.
 * При остановке активити (onStop() у активити) - автоматичеки дерегистрирует себя из EventBus.
 * <p>
 * [zipc]
 *
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public abstract class Wevr {
    private static final String TAG = ":" + Wevr.class.getSimpleName();


    //==============================================================================================

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Wevr(AppCompatActivity acty) {
        EventBus.getDefault().register(this);
        //===
        MFragment f = new MFragment();
        f.ce = this;
        acty
          .getSupportFragmentManager()
          .beginTransaction()
          .add(f, "CatcherEv")
          .commit();
    }

    //фрагмент без UI, для пристыковки к ЖЦ активити
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public static class MFragment extends Fragment {
        public Wevr ce;
        //=====================================================================


        @Override
        public void onStart() {
            EventBus bus = EventBus.getDefault();
            if (!bus.isRegistered(ce)) {
                bus.register(ce);
            }
            super.onStart();
        }

        @Override
        public void onStop() {
            EventBus.getDefault().unregister(ce);
            super.onStop();
        }
    }
}
