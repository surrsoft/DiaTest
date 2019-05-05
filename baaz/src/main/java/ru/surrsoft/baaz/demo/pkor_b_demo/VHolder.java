package ru.surrsoft.baaz.demo.pkor_b_demo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * ViewHolder
 */
public class VHolder extends RecyclerView.ViewHolder {
  public TextView tv1;
  public TextView tv2;

  public VHolder(@NonNull View itemView) {
    super(itemView);
  }
}
