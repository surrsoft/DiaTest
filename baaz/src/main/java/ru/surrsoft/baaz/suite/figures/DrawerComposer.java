package ru.surrsoft.baaz.suite.figures;

import android.content.Context;

import java.util.ArrayList;

/**
 * Используется для компоновки вместе нескольких drawer (см. потомков AbsDrawer)
 * <p>
 * [[w477w]]
 * <p>
 * [zipc]
 */
public class DrawerComposer extends N1208_AbsDrawer {
    private ArrayList<N1208_AbsDrawer> drawers;

    //==============================================================================================

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public DrawerComposer(Context c) {
        drawers = new ArrayList<>();
    }


    //``````````````````````````````````````````````````````````````````````````````````````````````
    public DrawerComposer addDrawer(N1208_AbsDrawer drawer) {
        drawers.add(drawer);
        return this;
    }


    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public void _draw() {
        //Log2_01.i(TAG, "--9:29--> _draw()" + "; getDebugString()=[" + (getDebugString()) + "]", LOG2);
        for (N1208_AbsDrawer eDrawer : drawers) {
            //== передача потомку параметров
            eDrawer
                    .canvas(mCanvas)
                    .w_px(mW_px)
                    .h_px(mH_px)
                    .animView(mAnimView)
                    .drawableStates(mDrawableStates);
            //.colorStroke(mColorCSL)
            //.colorFill(mColorFillCSL);
            //== отрисовка потомка
            eDrawer.draw();
        }
    }

    @Override
    public void animToEnd() {
        for (N1208_AbsDrawer eDrawer : drawers) {
            eDrawer.animToEnd();
        }
    }

    @Override
    public void animStart() {
        for (N1208_AbsDrawer eDrawer : drawers) {
            eDrawer.animStart();
        }
    }

    @Override
    public void animCancel() {
        for (N1208_AbsDrawer eDrawer : drawers) {
            eDrawer.animCancel();
        }
    }

    @Override
    public void animPause() {
        for (N1208_AbsDrawer eDrawer : drawers) {
            eDrawer.animPause();
        }
    }

    @Override
    public void animResume() {
        for (N1208_AbsDrawer eDrawer : drawers) {
            eDrawer.animResume();
        }
    }

    @Override
    public boolean animIsRunning() {
        for (N1208_AbsDrawer eDrawer : drawers) {
            if (eDrawer.animIsRunning()) {
                return true;
            }
        }
        return false;
    }

}
