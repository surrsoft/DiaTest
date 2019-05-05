package ru.surrsoft.baaz.demo.pkor_demo.a;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * ViewHolder
 */
public class A_ViewHolder extends RecyclerView.ViewHolder {

  public TextView tvFirstName;
  public TextView tvLastName;

  public A_ViewHolder(@NonNull View itemView) {
    super(itemView);
  }

}
