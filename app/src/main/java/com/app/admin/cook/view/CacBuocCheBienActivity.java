package com.app.admin.cook.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.admin.cook.R;
import com.app.admin.cook.adapter.ViewPagerCheBienAdapter;

public class CacBuocCheBienActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerCheBienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cac_buoc_che_bien);

        Intent intent = getIntent();
        String chuoi = intent.getStringExtra("chebien");
        String tenMonAn = intent.getStringExtra("tenmonan");

        toolbar = findViewById(R.id.toolBarCacBuocCheBien);
        tabLayout = findViewById(R.id.tabCacBuocCheBien);
        viewPager = findViewById(R.id.viewPagerCacBuocCheBien);
        adapter = new ViewPagerCheBienAdapter(getSupportFragmentManager(), chuoi);

        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);

        toolbar.setTitle("Cách chế biến " + tenMonAn.trim());
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
