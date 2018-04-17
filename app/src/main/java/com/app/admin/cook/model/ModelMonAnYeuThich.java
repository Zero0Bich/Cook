package com.app.admin.cook.model;

import android.content.Context;
import android.util.Log;

import com.app.admin.cook.presenter.IPresenterMonAnYeuThich;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;
import com.app.admin.cook.ulti.XuLyJsonMonAn;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 4/15/2018.
 */

public class ModelMonAnYeuThich implements CallbackXuLyJson {
    private Context context;
    private IPresenterMonAnYeuThich presenterMonAnYeuThich;
    private int maNd;

    public ModelMonAnYeuThich(Context context, IPresenterMonAnYeuThich presenterMonAnYeuThich, int maNd) {
        this.context = context;
        this.presenterMonAnYeuThich = presenterMonAnYeuThich;
        this.maNd = maNd;
    }

    public void downloadJson() {
        Map<String, String> map = new HashMap<>();
        map.put("maNd", String.valueOf(maNd));
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlMonAnYeuThich, map);
        dl.downloadDataUsePost();
    }


    @Override
    public void xuLyJson(String s) {
        presenterMonAnYeuThich.ganDuLieuMonAn(XuLyJsonMonAn.xuLy(s));
    }
}
