package com.app.admin.cook.view;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.app.admin.cook.R;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.presenter.PresenterChiTietMonAn;
import com.app.admin.cook.ulti.Server;
import com.bumptech.glide.Glide;

public class ChiTietMonAnActivity extends AppCompatActivity implements ViewChiTietMonAn {
    private Toolbar toolbar;
    private ImageView imgView;
    private TextView txtGioiThieu, txtThoiGian, txtThucHien;
    private TableLayout tableLayout;
    private MonAn monAn;
    private int maNd;
//    private Menu menu;
    private MenuItem menuItem;
//    bien da thich hay chua
    private boolean isLike = false;
//    bien xu ly yeu cau co thanh cong hay ko
    private boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        final Intent intent = getIntent();
        monAn = (MonAn) intent.getSerializableExtra("monan");
        maNd = intent.getIntExtra("maNd", 0);

        anhXa();

        Glide.with(this).load(monAn.getHinhMa())
                .placeholder(R.drawable.image_is_loading)
                .error(R.drawable.error_load_image).into(imgView);
        txtGioiThieu.setText("    " + monAn.getGioiThieu());
        txtThoiGian.append(monAn.getThoiGian());

//        progress nguyen lieu
        xuLyNguyenLieu(monAn.getNguyenLieu());

//        set line below text
        txtThucHien.setPaintFlags(txtThucHien.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtThucHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChiTietMonAnActivity.this, CacBuocCheBienActivity.class);
                i.putExtra("chebien", monAn.getCheBien());
                i.putExtra("tenmonan", monAn.getTenMa());
                startActivity(i);
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolBarChiTietMa);
        imgView = findViewById(R.id.imgViewChiTietMa);
        txtGioiThieu = findViewById(R.id.txtGioiThieuChiTietMa);
        txtThoiGian = findViewById(R.id.txtThoiGianChiTietMa);
        txtThucHien = findViewById(R.id.txtThucHienChiTietMa);
        tableLayout = findViewById(R.id.tabLayout);

        toolbar.setTitle(monAn.getTenMa());

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void xuLyNguyenLieu(String string) {
        String[] arr = string.split(",");
        int l = arr.length;

        TableRow tableRow = null;
        TableLayout.LayoutParams paramsRow = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams paramsText = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < l; i++) {
            if (i % 2 == 0) {
                tableRow = new TableRow(this);
                tableRow.setLayoutParams(paramsRow);
            }
            TextView textView = new TextView(this);
            textView.setLayoutParams(paramsText);
            textView.setText("- " + arr[i]);
            textView.setTextSize(18);
            textView.setPadding(30, 0, 0, 0);
            tableRow.addView(textView);
            if (i % 2 != 0) {
                tableLayout.addView(tableRow);
            } else {
                if (i == (l - 1) && (l % 2 != 0) ) {
                    tableLayout.addView(tableRow);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mon_an, menu);

        menuItem = menu.findItem(R.id.item_yeu_thich);

        for (MonAn monn : MainActivity.listMonAnYeuThich) {
            if (monAn.getMaMa() == monn.getMaMa()) {
                menuItem.setIcon(R.drawable.ic_heart_red_32dp);
                monAn = monn;
                isLike = true;
                break;
            }
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_yeu_thich) {
            if (isLike) {
                xuLy(Server.urlXoaMonAnYeuThich);
                menuItem.setIcon(R.drawable.ic_heart_black_32dp);
                isLike = false;
                MainActivity.listMonAnYeuThich.remove(monAn);
            } else {
                xuLy(Server.urlThemMonAnYeuThich);
                menuItem.setIcon(R.drawable.ic_heart_red_32dp);
                isLike = true;
                MainActivity.listMonAnYeuThich.add(monAn);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void xuLy(String url) {
        PresenterChiTietMonAn presenterChiTietMonAn = new PresenterChiTietMonAn(this, this, url, monAn.getMaMa(), maNd);
        presenterChiTietMonAn.xuLyDuLieu();
    }

    @Override
    public void ketQuaXuLy(String s) {
        if (s.equals("true")) {
            isSuccess = true;
        } else {
            isSuccess = false;
        }
    }
}
