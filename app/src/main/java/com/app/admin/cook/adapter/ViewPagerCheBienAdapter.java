package com.app.admin.cook.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.app.admin.cook.view.FragmentCheBien;

/**
 * Created by Admin on 4/14/2018.
 */

public class ViewPagerCheBienAdapter extends FragmentPagerAdapter {
    private String strCheBien;
    private String[] arrBuoc;

    public ViewPagerCheBienAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerCheBienAdapter(FragmentManager fm, String strCheBien) {
        super(fm);
        this.strCheBien = strCheBien;
        arrBuoc = strCheBien.split("Bước:");
    }

    @Override
    public Fragment getItem(int position) {
        for (int i = 1; i < arrBuoc.length; i++) {
            if ((i - 1) == position) {
                FragmentCheBien fragmentCheBien = new FragmentCheBien();
                Bundle bundle = new Bundle();
                bundle.putString("buoc", arrBuoc[i].trim());
                fragmentCheBien.setArguments(bundle);
                return fragmentCheBien;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return (arrBuoc.length - 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        for (int i = 1; i < arrBuoc.length; i++) {
            if ((i - 1) == position) {
                return "Bước " + i;
            }
        }
        return null;
    }
}
