package ru.diatest.vk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import ru.diatest.vk.pkor.DwzrImpl;
import ru.diatest.vk.pkor.VkItemViewHolder;
import ru.diatest.vk.vkapi.AVkAuth;
import ru.diatest.vk.vkapi.VkDatas;
import ru.diatest.vk.vkapi.VkEMethods;
import ru.diatest.vk.vkapi.VkItemDao;
import ru.diatest.vk.vkapi.VkResult;
import ru.diatest.vk.vkapi.VkUrlauth;
import ru.diatest.vk.vkapi.VkUrlreq;
import ru.diatest.vk.vkapi.errors.VkErrors;
import ru.diatest.vk.vkapi.resp_obj.err.VkErr;
import ru.diatest.vk.vkapi.resp_obj.friends.VkUser;
import ru.diatest.vk.vkapi.resp_obj.friends.VkUsersMain;
import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.widgets2.BuButton_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.budialogview.BuDialogView;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.pkor_b.WgPkor_B;
import ru.surrsoft.baaz.widgets2.pkor_b.a.Presenter;

public class AMain extends AppCompatActivity {

  private static final int REQUEST_CODE = 621;
  private Handler mHandler;
  private WgPkor_B wgPkor;
  private AlertDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Vdin_01.logStart("onCreate(...)", AMain.this);
    //---
    mHandler = new Handler(getMainLooper());
    //---
    mtWidgetsCreate();
    //---
    Vdin_01.logEnd("", this);
  }

  private void mtActionAuth() {
    Vdin_01.logStart("EVENT->", AMain.this);
    //---
    if (!G67G.Info.internetHave(this, true)) {
      Vdin_01.logM("ВНИМАНИЕ: отсутствует интернет-поюключение", AMain.this);
      Vdin_01.logEnd("", AMain.this);
      return;                     //<=== RETURN
    }
    //---
    String stUrlauth = new VkUrlauth()
      .buRevoke(true)
      .build();
    Vdin_01.logM("stUrlauth [" + stUrlauth + "]", AMain.this);
    //--- запуск активити
    Intent intent = new Intent(this, AVkAuth.class);
    intent.putExtra(AVkAuth.EXTRA_KEY_URL_AUTH, stUrlauth);
    startActivityForResult(intent, REQUEST_CODE);
    //---
    Vdin_01.logEnd("", AMain.this);
  }

  private void mtWidgetsCreate() {
    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(10)
      .build();
    //---
    new BuLayLinear_01(this)
      .addTo(layMain)
      .horizontal()
      .addChild(
        new BuButton_01(this)
          .buText("Аутентификация")
          .buListener(v -> {
            mtActionAuth();
          })
          .build()
      )
      .addChild(
        new BuButton_01(this)
          .buText("Обновить")
          .buListener(v -> {
            mtActionRequestFriends();
          })
          .build()
      )
      .build();
    //---
    mtWidgetListCreate(layMain);
    //---
  }

  private void mtWidgetListCreate(NLinearLayout layMain) {
    wgPkor = new WgPkor_B<VkItemViewHolder, VkUser>(this)
      .buAddTo(layMain)
      .buDwzr(new DwzrImpl(this))
      .buHeightMP(true)
      .buFABShow(false)
      .buPresenter(new Presenter<VkUser>() {
        @Override
        public boolean onListItemClick(View v, int index, VkUser vkUser) {
          Vdin_01.logStart("EVENT-> onListItemClick(...) //02.05.2019-7:23-118", AMain.this);
          //---
          Intent intent = new Intent(AMain.this, APhotos.class);
          intent.putExtra(APhotos.EXTRA_KEY, vkUser.id + "");
          startActivity(intent);
          //---
          return false;
        }
      });
    wgPkor
      .build();
  }


  /**
   * Выполняет запрос на получение списа друзей (метод {@link VkEMethods#FRIENDS_GET} )
   */
  private void mtActionRequestFriends() {
    Vdin_01.logStart("mtRequestFriends()", AMain.this);
    if (!G67G.Info.internetHave(this, true)) {
      Vdin_01.logM("ВНИМАНИЕ: отсутствует интернет-поюключение //03.05.2019-10:43-157", AMain.this);
      Vdin_01.logEnd("", AMain.this);
      return;                             //<=== RETURN
    } else {
      Vdin_01.logM("интернет-подключение есть, продолжаем //03.05.2019-10:43-161", AMain.this);
    }
    //---
    if (!VkDatas.isDatasExist()) {
      Vdin_01.logM("_vktoken не получен; инициируем аутентификацию //03.05.2019-10:43-165", AMain.this);
      Vdin_01.logEnd("", AMain.this);
      mtActionAuth();
    } else {
      Vdin_01.logM("_vktoken в наличии; выполняем запрос //03.05.2019-10:43-168", AMain.this);
      //---
      String stUrl = VkUrlreq.create(VkEMethods.FRIENDS_GET, "");
      //---
      App.okHttpClient.newCall(new Request.Builder().url(stUrl).build()).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
          mtToLogAndToast("ошибка запроса; e [" + e.getClass().getName() + "] //02.05.2019-17:26-155");
          e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
          String stResult = response.body().string();
          Vdin_01.logM("stResult [" + stResult + "]", AMain.this);
          mtResultHandle(stResult);
        }
      });
    }
    Vdin_01.logEnd("", AMain.this);
  }

  private void mtResultHandle(String stResult) {
    //---
    mHandler.post(() -> {
      //---
      boolean b = VkResult.isValid(VkEMethods.FRIENDS_GET, stResult);
      if (b) {
        Vdin_01.logM("результат успешно получен; method [" + VkEMethods.FRIENDS_GET + "]", AMain.this);
        //---
        VkUsersMain vkUsersMain = Bysa_01.gson.fromJson(stResult, VkUsersMain.class);
        Vdin_01.logM("json [" + Bysa_01.gsonPretty.toJson(vkUsersMain) + "]", AMain.this);
        //---
        VkItemDao vkUserDao = App.dbRoom.getVkUserDao();
        //- очищаем таблицу
        vkUserDao.clear();
        //- заполняем таблицу
        ArrayList<VkUser> items = vkUsersMain.response.items;
        vkUserDao.insertAll(items);
        //--- обновляем список (виджет)
        wgPkor.refresh(new DwzrImpl(this));
        //---
        Vdin_01.logM("очистили и заполнили БД-таблицу 'vkusers'", AMain.this);
      } else {
        VkErr vkErr = VkErrors.resolve(stResult);
        //---
        Vdin_01.logM("ВНИМАНИЕ: результат не получен; " +
          "err [" + vkErr.eErr + "]; method [" + VkEMethods.FRIENDS_GET + "]; stResult [" + stResult + "] //03.05.2019-11:07-214", AMain.this);
        //---
        mtToLogAndToast("ошибка [" + stResult + "]");
      }
    });
    //---
  }

  private void mtToLogAndToast(String st) {
    Vdin_01.logM(st, AMain.this);
    mHandler.post(() -> {
      mtDialogShow(st);
    });
  }

  private void mtDialogShow(String st) {
    dialog = new BuDialogView(this)
      .buTitle(BuDialogView.getDefaultTitle().text("Ошибка"))
      .buBody(BuDialogView.getDefaultBody().text(st))
      .buAddButton(BuDialogView
        .getDefaultButton()
        .text(EStrings._OK.val())
        .onclick(v -> {
          dialog.cancel();
        })
      )
      .build();
    //---
    dialog.show();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    Vdin_01.logStart("onActivityResult(requestCode [" + requestCode + "]; resultCode [" + resultCode + "]; ...)", AMain.this);
    if (requestCode == REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Vdin_01.logStart("авторизация завершена успешно //01.05.2019-13:03-114", AMain.this);
        Vdin_01.logM("_vktoken [" + VkDatas.vktokenGet() + "]", AMain.this);
        Vdin_01.logM("_userid [" + VkDatas.useridGet() + "]", AMain.this);
        Vdin_01.logM("запрашиваем список друзей...", AMain.this);
        mtActionRequestFriends();
        Vdin_01.logEnd("", AMain.this);
      } else {
        String stErr = "";
        if (data != null && data.getExtras() != null) {
          stErr = data.getExtras().getString(AVkAuth.EXTRA_ERROR_STRING, "");
        }
        mtToLogAndToast("ВНИМАНИЕ: авторизация не выполнена; stErr [" + stErr + "] //01.05.2019-11:50-117");
      }
    }
    Vdin_01.logEnd("", this);
  }

}
