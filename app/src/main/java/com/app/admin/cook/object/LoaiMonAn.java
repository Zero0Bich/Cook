package com.app.admin.cook.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/6/2018.
 */

public class LoaiMonAn implements Serializable {
    private int maLoaiMa;
    private String tenLoaiMa;
    private int maLoaiCha;
    private List<LoaiMonAn> listCon;
    private List<MonAn> dsMonAn;

    public LoaiMonAn() {
        this.maLoaiMa = 0;
        this.tenLoaiMa = "";
        this.maLoaiCha = 0;
        listCon = new ArrayList<>();
        dsMonAn = new ArrayList<>();
    }

    public LoaiMonAn(int maLoaiMa, String tenLoaiMa, int maLoaiCha) {
        this.maLoaiMa = maLoaiMa;
        this.tenLoaiMa = tenLoaiMa;
        this.maLoaiCha = maLoaiCha;
        this.listCon = new ArrayList<>();
        this.dsMonAn = new ArrayList<>();
    }

    public LoaiMonAn(int maLoaiMa, String tenLoaiMa, int maLoaiCha, List<LoaiMonAn> listCon) {
        this.maLoaiMa = maLoaiMa;
        this.tenLoaiMa = tenLoaiMa;
        this.maLoaiCha = maLoaiCha;
        this.listCon = listCon;
        this.dsMonAn = new ArrayList<>();
    }

    public LoaiMonAn(int maLoaiMa, String tenLoaiMa, int maLoaiCha, List<LoaiMonAn> listCon, List<MonAn> dsMonAn) {
        this.maLoaiMa = maLoaiMa;
        this.tenLoaiMa = tenLoaiMa;
        this.maLoaiCha = maLoaiCha;
        this.listCon = listCon;
        this.dsMonAn = dsMonAn;
    }

    public int getMaLoaiMa() {
        return maLoaiMa;
    }

    public void setMaLoaiMa(int maLoaiMa) {
        this.maLoaiMa = maLoaiMa;
    }

    public String getTenLoaiMa() {
        return tenLoaiMa;
    }

    public void setTenLoaiMa(String tenLoaiMa) {
        this.tenLoaiMa = tenLoaiMa;
    }

    public int getMaLoaiCha() {
        return maLoaiCha;
    }

    public void setMaLoaiCha(int maLoaiCha) {
        this.maLoaiCha = maLoaiCha;
    }

    public List<LoaiMonAn> getListCon() {
        return listCon;
    }

    public void setListCon(List<LoaiMonAn> listCon) {
        this.listCon = listCon;
    }

    public List<MonAn> getDsMonAn() {
        return dsMonAn;
    }

    public void setDsMonAn(List<MonAn> dsMonAn) {
        this.dsMonAn = dsMonAn;
    }
}
