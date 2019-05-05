package ru.surrsoft.baaz.univers;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * [[peex]]
 * <p>
 * Сущность инкапсулирующая единичный интернет-запрос
 * <p>
 * КОДЫ: [zipc]; $[vswb]
 * <p>
 * #id [[w348w]]
 * <p>
 * ВЕРСИЯ 2 1.0 2019-02-05 (stored)
 */
public class Peex implements Runnable {

    private String mUrl;
    private OkHttpClient mOkHttpClient;
    private Object mBackObject;
    private PeexPresenter mPresenter;

    public interface PeexPresenter {
        void n2348_back(Moaz_j_ result);
    }

    public Peex(String url,
                OkHttpClient okHttpClient,
                Object backObject,
                PeexPresenter presenter) {
        this.mUrl = url;
        this.mOkHttpClient = okHttpClient;
        this.mBackObject = backObject;
        this.mPresenter = presenter;
    }

    @Override
    public void run() {
        Request request = new Request.Builder()
          .url(mUrl)
          .build();
        Call call = mOkHttpClient.newCall(request);
        //=== запрос
        Response response = null;
        Moaz_j_ moaz = new Moaz_j_();
        moaz.backObject = mBackObject;
        boolean ok = false;
        try {
            response = call.execute(); //<=== ЗАПРОС
            ok = true;
        } catch (UnknownHostException e0) {
            moaz.type = ETypeResponse.ERR_UNKNOWN_HOST;
        } catch (ConnectException e) {
            moaz.type = ETypeResponse.ERR_CONNECT;
        } catch (SocketTimeoutException e1) {
            moaz.type = ETypeResponse.ERR_TIMEOUT;
        } catch (IOException e) {
            e.printStackTrace();
            moaz.type = ETypeResponse.ERR_IOE;
        }

        if (!ok) {
            if (mPresenter != null) mPresenter.n2348_back(moaz);
        } else {
            //^ если запрос был выполнен без исключений
            //=== получение тела ответа
            String body = null;
            ResponseBody rbody = response.body();
            try {
                body = rbody.string(); //<=== BODY
            } catch (IOException e) {
                e.printStackTrace();
                rbody.close();
                moaz.type = ETypeResponse.ERR_BODY;
                if (mPresenter != null) {
                    mPresenter.n2348_back(moaz);
                }
            }
            //=== если получение тела ответа прошло успешно
            if (body != null) { //IF-1
                moaz.dsif = body;
                moaz.baut = response.code();
                if (response.isSuccessful()) {
                    moaz.type = ETypeResponse.OK;
                } else {
                    moaz.type = ETypeResponse.KO;
                }
                if (mPresenter != null) {
                    mPresenter.n2348_back(moaz);
                }
            }
        }
    }

    /**
     * Возвращает результат напрямую, а не через презентер
     *
     * @return
     */
    public Moaz_j_ run2() {
        final Moaz_j_[] moaz = new Moaz_j_[1];
        this.mPresenter = new PeexPresenter() {
            @Override
            public void n2348_back(Moaz_j_ _moaz) {
                moaz[0] = _moaz;
            }
        };
        this.run();
        return moaz[0];
    }

}
