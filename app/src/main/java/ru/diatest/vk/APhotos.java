package ru.diatest.vk;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import ru.diatest.vk.vkapi.VkEMethods;
import ru.diatest.vk.vkapi.VkResult;
import ru.diatest.vk.vkapi.VkUrlreq;
import ru.diatest.vk.vkapi.resp_obj.photos.VkPhoto;
import ru.diatest.vk.vkapi.resp_obj.photos.VkPhotosMain;
import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.BuButton_01;
import ru.surrsoft.baaz.widgets2.BuilderRV;
import ru.surrsoft.baaz.widgets2.BuilderTV_02;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class APhotos extends AppCompatActivity {
  public static final String EXTRA_KEY = "extra_userid_190502082100";
  private RecyclerView rv;
  private Handler mHandler;
  private TextView tvCount;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    String stUserid = getIntent().getExtras().getString(EXTRA_KEY, "");
    //---
    String stURL = VkUrlreq.create(VkEMethods.PHOTOS_GET, stUserid + "");
    Vdin_01.logM("stURL [" + stURL + "]", APhotos.this);

    //---
    mHandler = new Handler(getMainLooper());

    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(10)
      .build();

    //---
    View.OnClickListener action = v -> {
      mtRequestClick(stURL);
    };

    //---
    NLinearLayout lay1 = new BuLayLinear_01(this)
      .addTo(layMain)
      .horizontal()
      .addChild(new BuButton_01(this)
        .buText("Обновить")
        .buOnclick(action)
        .build())
      .build();

    //---
    tvCount = new BuilderTV_02(this)
      .addTo(lay1)
      .text(mtStCount("-"))
      .textColor(Color.BLACK)
      .paddingL(5)
      .build();

    //---
    rv = new BuilderRV(this)
      .adapter(new Adapter(new ArrayList<>()))
      .create();
    layMain.addView(rv);

    //---
    action.onClick(null);

  }

  private class VHolder extends RecyclerView.ViewHolder {
    VHolder(@NonNull View itemView) {
      super(itemView);
    }
  }


  private class Adapter extends RecyclerView.Adapter<VHolder> {

    private ArrayList<VkPhoto> photos;

    Adapter(ArrayList<VkPhoto> photos) {
      this.photos = photos;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      //---
      ImageView iv = new ImageView(APhotos.this);
      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(U.MP, U.WC);
      iv.setLayoutParams(lp);
      //---
      VHolder vh = new VHolder(iv);
      //---
      return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, int i) {
      VkPhoto vkPhoto = photos.get(i);
      //---
      Glide.with(APhotos.this)
        .load(vkPhoto.getPhotoMaxSizeUrl())
        .into(((ImageView) vHolder.itemView));
    }

    @Override
    public int getItemCount() {
      return photos.size();
    }
  }

  private void mtRequestClick(String stUrl) {
    Vdin_01.logM("mtRequestClick(stUrl=[" + stUrl + "])", APhotos.this);
    //---
    App.okHttpClient.newCall(new Request.Builder().url(stUrl).build()).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Vdin_01.logM("ошибка запроса; e [" + e.getClass().getName() + "] //02.05.2019-7:30-143", APhotos.this);
        e.printStackTrace();
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        String stResult = response.body().string();
        Vdin_01.logM("stResult [" + stResult + "] //02.05.2019-7:31-150", APhotos.this);
        //---
        if (VkResult.isValid(VkEMethods.PHOTOS_GET, stResult)) {
          Vdin_01.logM("пришёл ожидаемый ответ; method [" + VkEMethods.PHOTOS_GET + "]", APhotos.this);
          //---
          VkPhotosMain vkPhotosMain = Bysa_01.gson.fromJson(stResult, VkPhotosMain.class);
          //---
          String s = Bysa_01.gsonPretty.toJson(vkPhotosMain);
          Vdin_01.logM("s [" + s + "]", APhotos.this);
          //---
          mHandler.post(() -> {
            tvCount.setText(mtStCount(vkPhotosMain.response.count + ""));
            //---
            rv.setAdapter(new Adapter(vkPhotosMain.response.items));
          });
        } else {
          Vdin_01.logM("ВНИМАНИЕ: пришёл ответ с ошибкой; method [" + VkEMethods.PHOTOS_GET + "]", APhotos.this);
          mHandler.post(() -> {
            Toast.makeText(APhotos.this, "ОШИБКА: " + stResult, Toast.LENGTH_LONG).show();
          });
        }
        //---
      }
    });
  }

  private String mtStCount(String st) {
    return "Количество изображений: " + st;
  }
}
