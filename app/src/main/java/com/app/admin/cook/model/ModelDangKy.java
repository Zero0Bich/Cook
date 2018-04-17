package com.app.admin.cook.model;

import android.content.Context;

import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.presenter.IPresenterDangKy;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 4/6/2018.
 */

public class ModelDangKy implements CallbackXuLyJson {
    private Context context;
    private IPresenterDangKy presenterDangKy;

    public ModelDangKy(Context context, IPresenterDangKy presenterDangKy) {
        this.context = context;
        this.presenterDangKy = presenterDangKy;
    }

    public void dangKyTaiKhoan(NguoiDung nguoiDung) {
        Map<String, String> map = new HashMap<>();
        map.put("tenNd", nguoiDung.getTenNd());
        map.put("email", nguoiDung.getEmail());
        map.put("matKhau", nguoiDung.getMatKhau());
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlDangKyTaiKhoan, map);
        dl.downloadDataUsePost();
    }

    @Override
    public void xuLyJson(String s) {
        if (s.equals("true")) {
            presenterDangKy.ketQuaDangKy("true");
        } else {
            presenterDangKy.ketQuaDangKy("false");
        }
    }
}
