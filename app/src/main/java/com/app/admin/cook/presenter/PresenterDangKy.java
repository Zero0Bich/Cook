package com.app.admin.cook.presenter;

import android.content.Context;

import com.app.admin.cook.model.ModelDangKy;
import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.view.ViewDangKy;

/**
 * Created by Admin on 4/6/2018.
 */

public class PresenterDangKy implements IPresenterDangKy {
    private Context context;
    private ViewDangKy viewDangKy;
    private ModelDangKy modelDangKy;
    private NguoiDung nguoiDung;

    public PresenterDangKy(Context context, ViewDangKy viewDangKy, NguoiDung nguoiDung) {
        this.context = context;
        this.viewDangKy = viewDangKy;
        this.nguoiDung = nguoiDung;
        this.modelDangKy = new ModelDangKy(context, this);
    }

    @Override
    public void thucHienDangKy() {
        modelDangKy.dangKyTaiKhoan(nguoiDung);
    }

    @Override
    public void ketQuaDangKy(String s) {
        if (s.equals("true")) {
            viewDangKy.dangKyThanhCong();
        } else {
            viewDangKy.dangKyThatBai();
        }
    }
}
