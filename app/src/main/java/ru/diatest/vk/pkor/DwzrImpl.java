package ru.diatest.vk.pkor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.diatest.vk.App;
import ru.diatest.vk.WgConfigs;
import ru.diatest.vk.vkapi.VkItemDao;
import ru.diatest.vk.vkapi.resp_obj.friends.VkUser;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.widgets2.BuImageView;
import ru.surrsoft.baaz.widgets2.BuilderTV_02;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.pkor_b.a.Dwzr;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class DwzrImpl implements Dwzr<VkItemViewHolder, VkUser> {

  private final VkItemDao vkItemDao;
  private final Context mContext;

  public DwzrImpl(Context ctx) {
    vkItemDao = App.dbRoom.getVkUserDao();
    mContext = ctx;
  }

  @Override
  public VkUser elemGetByIndex(int index) {
    return vkItemDao.getByIndex(index);
  }

  @Override
  public VkItemViewHolder viewHolderCreate(Context ctx) {
    //---
    NLinearLayout layMain = new BuLayLinear_01(ctx)
      .paddings(10)
      .build();
    //---
    TextView tvFirstName = new BuilderTV_02(ctx)
      .addTo(layMain)
      .text("")
      .textColor(WgConfigs.UserCard.FirsName.color)
      .textSize(WgConfigs.UserCard.FirsName.fontSize_sp)
      .build();
    //---
    TextView tvLastName = new BuilderTV_02(ctx)
      .addTo(layMain)
      .text("")
      .textColor(WgConfigs.UserCard.LastName.color)
      .textSize(WgConfigs.UserCard.LastName.fontSize_sp)
      .build();
    //---
    ImageView ivImage = ((BuImageView) new BuImageView(ctx)
      .layParamClass(LinearLayout.LayoutParams.class)
      .margins(new Margins_01(10, 10, 10, 0))
      .wh(60))
      .buAddTo(layMain)
      .build();
    //---
    VkItemViewHolder vh = new VkItemViewHolder(layMain);
    vh.tvFirstName = tvFirstName;
    vh.tvLastName = tvLastName;
    vh.ivImage = ivImage;
    //---
    return vh;
  }

  @Override
  public void viewHolderBind(VkItemViewHolder vh, int index) {
    VkUser vkUser = vkItemDao.getByIndex(index);
    //---
    vh.tvFirstName.setText(vkUser.first_name);
    vh.tvLastName.setText(vkUser.last_name);
    //---
    if (TString_01.isNoEmpty(vkUser.photo_100)) {
      Glide.with(mContext)
        .load(vkUser.photo_100)
        .into(vh.ivImage);
    }
    //---
  }

  @Override
  public void remove(int index) {
    vkItemDao.delete(vkItemDao.getByIndex(index));
  }

  @Override
  public void add(AppCompatActivity acty) {

  }

  @Override
  public void swap(int iFirstIndex, int iSecondIndex) {

  }

  @Override
  public long getCount() {
    return vkItemDao.getCount();
  }
}
