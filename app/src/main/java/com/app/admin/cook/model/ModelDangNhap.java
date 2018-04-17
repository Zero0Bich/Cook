package com.app.admin.cook.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.presenter.IPresenterDangNhap;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 4/6/2018.
 */

public class ModelDangNhap implements CallbackXuLyJson {
    private Context context;
    private IPresenterDangNhap presenterDangNhap;
    private String email;
    private String matKhau;

    public ModelDangNhap(Context context) {
        this.context = context;
    }

    public ModelDangNhap(Context context, IPresenterDangNhap presenterDangNhap) {
        this.context = context;
        this.presenterDangNhap = presenterDangNhap;
    }

    public void kiemTraDangNhap(String emailDangNhap, String matKhauDangNhap) {
        this.email = emailDangNhap;
        this.matKhau = matKhauDangNhap;
        Map<String, String> map = new HashMap<>();
        map.put("tenDangNhap", email);
        map.put("matKhau", matKhau);

        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlDangNhap, map);
        dl.downloadDataUsePost();
    }

    @Override
    public void xuLyJson(String s) {
        if (s.equals("false")) {
            presenterDangNhap.xuLyThatBai();
        } else {
            try {
                JSONObject jsonObject = new JSONObject(s);
                int maNd = jsonObject.getInt("maNguoiDung");
                String tenNd = jsonObject.getString("tenNguoiDung");
                int maLoaiNd = jsonObject.getInt("maLoaiNguoiDung");

                SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = cachedDangNhap.edit();
                editor.putInt("maNd", maNd);
                editor.putString("tenNd", tenNd);
                editor.putString("email", email);
                editor.putString("matKhau", matKhau);
                editor.putInt("maLoaiNd", maLoaiNd);

                editor.commit();

                presenterDangNhap.xuLyThanhCong();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public NguoiDung layCachedDangNhap() {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        int maNd = cachedDangNhap.getInt("maNd", 0);
        String tenNd = cachedDangNhap.getString("tenNd", "");
        String email = cachedDangNhap.getString("email", "");
        String matKhau = cachedDangNhap.getString("matKhau", "");
        int maLoaiNd = cachedDangNhap.getInt("maLoaiNd", 1);

        NguoiDung nguoiDung = new NguoiDung(maNd, tenNd, email, matKhau, maLoaiNd);
        return nguoiDung;
    }

    public void xoaCachedDangNhap() {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.remove("maNd");
        editor.remove("tenNd");
        editor.remove("email");
        editor.remove("matKhau");
        editor.remove("maLoaiNd");
        editor.commit();
    }
}
