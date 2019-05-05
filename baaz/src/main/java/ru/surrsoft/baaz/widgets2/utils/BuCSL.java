package ru.surrsoft.baaz.widgets2.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EC;

/**
 * Порядок вызова методов задания цвета имеет значение, такое же как у ColorStateList (это не
 * касается только метода normal() - где бы он не располагался, состояние normal фактически всегда
 * будет в самом низу)
 */
public class BuCSL {
  private int[][] mStates = {};
  private int[] mColors = {};

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuCSL() {
    //состояние normal должно быть всегда
    mStates = ArrayUtils.add(mStates, new int[]{});
    mColors = ArrayUtils.add(mColors, Color.RED);
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public BuCSL normal(@Nullable EC ec) {
    if (ec != null) {
      normal(new Color2(ec.val));
    }
    return this;
  }

  public BuCSL normal(@Nullable Color2 color) {
    if (color != null) {
      mColors[mColors.length - 1] = color.val;
    }
    return this;
  }

  public BuCSL pressed(@Nullable EC ec) {
    if (ec != null) {
      pressed(new Color2(ec.val));
    }
    return this;
  }

  public BuCSL pressed(@Nullable Color2 color) {
    if (color != null) {
      mStates = ArrayUtils.add(mStates, mStates.length - 1, new int[]{android.R.attr.state_pressed});
      mColors = ArrayUtils.add(mColors, mColors.length - 1, color.val);
    }
    return this;
  }

  public BuCSL disable(@Nullable EC ec) {
    if (ec != null) {
      disable(new Color2(ec.val));
    }
    return this;
  }

  public BuCSL disable(@Nullable Color2 color) {
    if (color != null) {
      mStates = ArrayUtils.add(mStates, mStates.length - 1, new int[]{-1 * android.R.attr.state_enabled});
      mColors = ArrayUtils.add(mColors, mColors.length - 1, color.val);
    }
    return this;
  }

  public BuCSL selected(@Nullable EC ec) {
    if (ec != null) {
      selected(new Color2(ec.val));
    }
    return this;
  }

  public BuCSL selected(@Nullable Color2 color) {
    if (color != null) {
      mStates = ArrayUtils.add(mStates, mStates.length - 1, new int[]{android.R.attr.state_selected});
      mColors = ArrayUtils.add(mColors, mColors.length - 1, color.val);
    }
    return this;
  }

  //create
  //``````````````````````````````````````````````````````````````````````````````````````````````
  @NonNull
  public ColorStateList create() {
    if (mStates.length == 0) {
      mStates = ArrayUtils.add(mStates, new int[]{});
    }
    if (mColors.length == 0) {
      mColors = ArrayUtils.add(mColors, Color.RED);
    }
    return new ColorStateList(mStates, mColors);
  }

}
