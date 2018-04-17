package com.app.admin.cook.model;

import android.content.Context;
import android.util.Log;

import com.app.admin.cook.adapter.CallbackGetChild;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.presenter.IPresenterDanhSachMonAn;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;
import com.app.admin.cook.ulti.XuLyJsonMonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 4/11/2018.
 */

public class ModelMonAnTheoLoaiMonAn implements CallbackXuLyJson {
    private Context context;
    private CallbackGetChild callbackGetChild;
    private int maLoaiCha;
    private int maLoaiCon;

    private IPresenterDanhSachMonAn iPresenterDanhSachMonAn;
    private int maLoaiMa;
    private int page = 1;

    public ModelMonAnTheoLoaiMonAn(Context context, IPresenterDanhSachMonAn iPresenterDanhSachMonAn, int maLoaiMa) {
        this.context = context;
        this.iPresenterDanhSachMonAn = iPresenterDanhSachMonAn;
        this.maLoaiMa = maLoaiMa;
    }

//    public ModelMonAnTheoLoaiMonAn(Context context, CallbackGetChild callbackGetChild, int maLoaiCha, int maLoaiCon) {
//        this.context = context;
//        this.callbackGetChild = callbackGetChild;
//        this.maLoaiCha = maLoaiCha;
//        this.maLoaiCon = maLoaiCon;
//    }

    public void downloadJson() {
        Map<String, String> map = new HashMap<>();
        map.put("loaiMonAn", String.valueOf(maLoaiMa));
        map.put("page", String.valueOf(page));
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlMonAnTheoLoaiMonAn, map);
        dl.downloadDataUsePost();
    }

    public void downloadJsonLoadMore() {
        Map<String, String> map = new HashMap<>();
        map.put("loaiMonAn", String.valueOf(maLoaiMa));
        map.put("page", String.valueOf(++page));
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlMonAnTheoLoaiMonAn, map);
        dl.downloadDataUsePost();
    }

    @Override
    public void xuLyJson(String s) {
        if (page == 1) {
            iPresenterDanhSachMonAn.hienThi(XuLyJsonMonAn.xuLy(s), true);
        } else {
            iPresenterDanhSachMonAn.hienThi(XuLyJsonMonAn.xuLy(s), false);
        }
    }
}
