package ru.diatest.vk.pkor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class VkItemViewHolder extends RecyclerView.ViewHolder {
  public TextView tvFirstName;
  public TextView tvLastName;
  public ImageView ivImage;

  public VkItemViewHolder(@NonNull View itemView) {
    super(itemView);
  }
}
