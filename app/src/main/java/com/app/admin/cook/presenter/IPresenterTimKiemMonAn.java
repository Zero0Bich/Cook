package com.app.admin.cook.presenter;

import com.app.admin.cook.object.MonAn;

import java.util.List;

/**
 * Created by Admin on 4/16/2018.
 */

public interface IPresenterTimKiemMonAn {
    void layDanhSachMonAn();
    void layLoadMore();
    void hienThi(List<MonAn> list, boolean chk);
}
