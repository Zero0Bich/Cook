package com.app.admin.cook.model;

import android.content.Context;

import com.app.admin.cook.adapter.CallbackGetChild;
import com.app.admin.cook.presenter.IPresenterDanhSachMonAn;
import com.app.admin.cook.presenter.IPresenterTimKiemMonAn;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;
import com.app.admin.cook.ulti.XuLyJsonMonAn;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 4/16/2018.
 */

public class ModelTimKiemMonAn implements CallbackXuLyJson{
    private Context context;
    private IPresenterTimKiemMonAn presenterTimKiemMonAn;
    private String tenMa;
    private int page = 1;

    public ModelTimKiemMonAn(Context context, IPresenterTimKiemMonAn presenterTimKiemMonAn, String tenMa) {
        this.context = context;
        this.presenterTimKiemMonAn = presenterTimKiemMonAn;
        this.tenMa = tenMa;
    }

    public void downloadJson() {
        Map<String, String> map = new HashMap<>();
        map.put("tenMa", tenMa);
        map.put("page", String.valueOf(page));
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlTimKiemMonAn, map);
        dl.downloadDataUsePost();
    }

    public void downloadJsonLoadMore() {
        Map<String, String> map = new HashMap<>();
        map.put("tenMa", tenMa);
        map.put("page", String.valueOf(++page));
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlTimKiemMonAn, map);
        dl.downloadDataUsePost();
    }

    @Override
    public void xuLyJson(String s) {
        if (page == 1) {
            presenterTimKiemMonAn.hienThi(XuLyJsonMonAn.xuLy(s), true);
        } else {
            presenterTimKiemMonAn.hienThi(XuLyJsonMonAn.xuLy(s), false);
        }
    }
}
