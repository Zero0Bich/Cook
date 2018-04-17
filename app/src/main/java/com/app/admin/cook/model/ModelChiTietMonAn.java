package com.app.admin.cook.model;

import android.content.Context;

import com.app.admin.cook.presenter.IPresenterChiTietMonAn;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 4/16/2018.
 */

public class ModelChiTietMonAn implements CallbackXuLyJson {
    private Context context;
    private IPresenterChiTietMonAn presenterChiTietMonAn;
    private String url;
    private int maMa, maNd;

    public ModelChiTietMonAn(Context context, IPresenterChiTietMonAn presenterChiTietMonAn, String url, int maMa, int maNd) {
        this.context = context;
        this.presenterChiTietMonAn = presenterChiTietMonAn;
        this.url = url;
        this.maMa = maMa;
        this.maNd = maNd;
    }

    public void downloadJson() {
        Map<String, String> map = new HashMap<>();
        map.put("maMa", String.valueOf(maMa));
        map.put("maNd", String.valueOf(maNd));
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, url, map);
        dl.downloadDataUsePost();
    }

    @Override
    public void xuLyJson(String s) {
        presenterChiTietMonAn.xuLyKetQua(s);
    }
}
