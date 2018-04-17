package com.app.admin.cook.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import com.app.admin.cook.model.ModelMonAnTheoLoaiMonAn;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.view.ViewDanhSachMonAn;

import java.util.List;

/**
 * Created by Admin on 4/12/2018.
 */

public class PresenterDanhSachMonAn implements IPresenterDanhSachMonAn {
    private Context context;
    private ViewDanhSachMonAn viewDanhSachMonAn;
    private ModelMonAnTheoLoaiMonAn modelMonAnTheoLoaiMonAn;
    private int maLoaiMa;
    private ProgressBar progressBar;
    private boolean isLoading = false;

    public PresenterDanhSachMonAn(Context context, ViewDanhSachMonAn viewDanhSachMonAn, int maLoaiMa, ProgressBar progressBar) {
        this.context = context;
        this.viewDanhSachMonAn = viewDanhSachMonAn;
        this.maLoaiMa = maLoaiMa;
        this.progressBar = progressBar;
        modelMonAnTheoLoaiMonAn = new ModelMonAnTheoLoaiMonAn(context, this, maLoaiMa);
    }

    @Override
    public void layDanhSachMonAn() {
        modelMonAnTheoLoaiMonAn.downloadJson();
    }

    @Override
    public void layLoadMore() {
        modelMonAnTheoLoaiMonAn.downloadJsonLoadMore();
    }

    @Override
    public void hienThi(List<MonAn> list, boolean chk) {
        viewDanhSachMonAn.hienThiDanhSachMonAn(list, chk);
    }
}
