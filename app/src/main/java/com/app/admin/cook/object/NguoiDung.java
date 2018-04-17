package com.app.admin.cook.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/6/2018.
 */

public class NguoiDung implements Serializable {
    private int maNd;
    private String tenNd;
    private String email;
    private String matKhau;
    private int maLoaiNd;
    private List<MonAn> monAns;

    public NguoiDung() {
        this.maNd = 0;
        this.tenNd = "";
        this.email = "";
        this.matKhau = "";
        this.maLoaiNd = 1;
        this.monAns = new ArrayList<>();
    }

    public NguoiDung(String tenNd, String email, String matKhau) {
        this.maNd = 0;
        this.tenNd = tenNd;
        this.email = email;
        this.matKhau = matKhau;
        this.maLoaiNd = 1;
        this.monAns = new ArrayList<>();
    }

    public NguoiDung(int maNd, String tenNd, String email, String matKhau, int maLoaiNd) {
        this.maNd = maNd;
        this.tenNd = tenNd;
        this.email = email;
        this.matKhau = matKhau;
        this.maLoaiNd = maLoaiNd;
        this.monAns = new ArrayList<>();
    }

    public NguoiDung(int maNd, String tenNd, String email, String matKhau, int maLoaiNd, List<MonAn> monAns) {
        this.maNd = maNd;
        this.tenNd = tenNd;
        this.email = email;
        this.matKhau = matKhau;
        this.maLoaiNd = maLoaiNd;
        this.monAns = monAns;
    }

    public int getMaNd() {
        return maNd;
    }

    public void setMaNd(int maNd) {
        this.maNd = maNd;
    }

    public String getTenNd() {
        return tenNd;
    }

    public void setTenNd(String tenNd) {
        this.tenNd = tenNd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaLoaiNd() {
        return maLoaiNd;
    }

    public void setMaLoaiNd(int maLoaiNd) {
        this.maLoaiNd = maLoaiNd;
    }

    public List<MonAn> getMonAns() {
        return monAns;
    }

    public void setMonAns(List<MonAn> monAns) {
        this.monAns = monAns;
    }
}
