package com.app.admin.cook.presenter;

import android.content.Context;

import com.app.admin.cook.model.ModelDangNhap;
import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.view.ViewDangNhap;

/**
 * Created by Admin on 4/6/2018.
 */

public class PresenterDangNhap implements IPresenterDangNhap {
    private Context context;
    private ModelDangNhap modelDangNhap;
    private ViewDangNhap viewDangNhap;

    public PresenterDangNhap(Context context, ViewDangNhap viewDangNhap) {
        this.context = context;
        this.viewDangNhap = viewDangNhap;
        this.modelDangNhap = new ModelDangNhap(context, this);
    }

    @Override
    public void xuLyDangNhap(String s1, String s2) {
        modelDangNhap.kiemTraDangNhap(s1, s2);
    }

    @Override
    public void xuLyThanhCong() {
        viewDangNhap.dangNhapThanhCong();
    }

    @Override
    public void xuLyThatBai() {
        viewDangNhap.dangNhapThatBai();
    }
}
