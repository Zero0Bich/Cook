package com.app.admin.cook.presenter;

import com.app.admin.cook.object.LoaiMonAn;

import java.util.List;

/**
 * Created by Admin on 4/6/2018.
 */

public interface IPresenterExpandMenu {
    void yeuCauDuLieu();
    void xuLyExpandMenu(List<LoaiMonAn> loaiMonAns);
}
