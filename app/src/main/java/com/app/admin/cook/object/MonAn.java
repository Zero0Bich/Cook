package com.app.admin.cook.object;

import java.io.Serializable;

/**
 * Created by Admin on 4/10/2018.
 */

public class MonAn implements Serializable {
    private int maMa;
    private String tenMa;
    private String hinhMa;
    private String gioiThieu;
    private String nguyenLieu;
    private String cheBien;
    private String thoiGian;

    public MonAn() {
        this.maMa = 0;
        this.tenMa = "";
        this.hinhMa = "";
        this.gioiThieu = "";
        this.nguyenLieu = "";
        this.cheBien = "";
        this.thoiGian = "";
    }

    public MonAn(int maMa, String tenMa, String hinhMa, String gioiThieu, String nguyenLieu, String cheBien, String thoiGian) {
        this.maMa = maMa;
        this.tenMa = tenMa;
        this.hinhMa = hinhMa;
        this.gioiThieu = gioiThieu;
        this.nguyenLieu = nguyenLieu;
        this.cheBien = cheBien;
        this.thoiGian = thoiGian;
    }

    public int getMaMa() {
        return maMa;
    }

    public void setMaMa(int maMa) {
        this.maMa = maMa;
    }

    public String getTenMa() {
        return tenMa;
    }

    public void setTenMa(String tenMa) {
        this.tenMa = tenMa;
    }

    public String getHinhMa() {
        return hinhMa;
    }

    public void setHinhMa(String hinhMa) {
        this.hinhMa = hinhMa;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public String getCheBien() {
        return cheBien;
    }

    public void setCheBien(String cheBien) {
        this.cheBien = cheBien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
