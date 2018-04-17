package com.app.admin.cook.model;

import android.content.Context;

import com.android.volley.toolbox.StringRequest;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.presenter.IPresenterMonAnMoiNhat;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;
import com.app.admin.cook.ulti.XuLyJsonMonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/10/2018.
 */

public class ModelMonAnMoiNhat implements CallbackXuLyJson {
    private Context context;
    private IPresenterMonAnMoiNhat iPresenterMonAnMoiNhat;

    public ModelMonAnMoiNhat(Context context, IPresenterMonAnMoiNhat iPresenterMonAnMoiNhat) {
        this.context = context;
        this.iPresenterMonAnMoiNhat = iPresenterMonAnMoiNhat;
    }

    public void downloadJson() {
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlMonAnMoiNhat);
        dl.downloadJsonUseGet();
    }

    @Override
    public void xuLyJson(String s) {
        iPresenterMonAnMoiNhat.hienThiDuLieu(XuLyJsonMonAn.xuLy(s));
    }
}
