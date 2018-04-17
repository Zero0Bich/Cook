package com.app.admin.cook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.admin.cook.view.FragmentDangKy;
import com.app.admin.cook.view.FragmentDangNhap;

/**
 * Created by Admin on 4/6/2018.
 */

public class ViewPagerDangNhapAdapter extends FragmentPagerAdapter {

    public ViewPagerDangNhapAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKy fragmentDangKy = new FragmentDangKy();
                return fragmentDangKy;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng ký";
            default:
                return null;
        }
    }
}
