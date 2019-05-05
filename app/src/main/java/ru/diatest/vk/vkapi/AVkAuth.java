package ru.diatest.vk.vkapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ru.surrsoft.baaz.logs.Vdin_01;

/**
 * Активити для авторизации во ВКонтакте (получения _токена ([gcza])) методом _implicit-flow ([irfu])
 * <p>
 */
public class AVkAuth extends AppCompatActivity {

  public static final String EXTRA_KEY_URL_AUTH = "_urlauth";
  public static final String EXTRA_ERROR_STRING = "_190501131500";
  public static int RESULT_ERROR = 1314;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Vdin_01.logStart("onCreate(...)", AVkAuth.this);

    //--- _urlauth ([gxbu])
    String stUrlauth = getIntent().getExtras().getString(EXTRA_KEY_URL_AUTH, "");
    Vdin_01.logM("stUrlauth [" + stUrlauth + "]", AVkAuth.this);

    //---
    WebView webView = new WebView(this);
    //---
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDomStorageEnabled(true);
    //---
    webView.loadUrl(stUrlauth);
    //---
    webView.setFocusable(true);
    webView.setFocusableInTouchMode(true);
    //---
    webView.setWebViewClient(new WebViewClient() {
      //текущий метод может вызываться несколько раз в ходе авторизации
      @Override
      public void onPageFinished(WebView view, String _url) {
        boolean isUrltokenValid = VkUrltoken.isValid(_url);
        Vdin_01.logM("EVENT-> onPageFinished; _url [" + _url + "]; isUrltoken [" + isUrltokenValid + "]", AVkAuth.this);
        //---
        if (isUrltokenValid) {
          Vdin_01.logM("получен валидный _urltoken, сохраняем его элементы и завершаем активити авторизации", AVkAuth.this);
          //--- сохранение элементов _urltoken (т.е. _vktoken, _userid) в постоянное хранилище
          VkDatas.update(new VkUrltoken(_url));
          //---
          setResult(RESULT_OK);
          finish();
        } else {
          if (_url.contains("https://oauth.vk.com/blank.html#error")) {
            setResult(RESULT_ERROR, new Intent().putExtra(EXTRA_ERROR_STRING, _url));
            finish();
          }
        }
      }
    });
    //---
    setContentView(webView);
    //---
    Vdin_01.logEnd("", this);
  }

}
