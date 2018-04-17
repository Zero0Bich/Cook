package com.app.admin.cook.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.admin.cook.R;
import com.bumptech.glide.Glide;

/**
 * Created by Admin on 4/14/2018.
 */

public class FragmentCheBien extends Fragment {
    private LinearLayout layout, linearLayout;
    private LinearLayout.LayoutParams paramsTextView, paramsImageView, paramsImageViewDouble, paramsLinearLayout;
    private boolean isLinear = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_che_bien, container, false);

        Bundle bundle = getArguments();
        String str = bundle.getString("buoc");

//        xử lý hiển thị
        String[] arr = str.split("\"");

        layout = view.findViewById(R.id.linearLayoutCheBien);
        paramsTextView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsImageView = new LinearLayout.LayoutParams(500, 300);
        paramsImageView.gravity = Gravity.CENTER;

        paramsImageViewDouble = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        paramsImageViewDouble.weight = 1.0f;

        paramsLinearLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < arr.length; i++) {
            String chuoi = arr[i].trim();
            if (i % 2 == 0) {
                if (!chuoi.equals("")) {
//                Tạo textview
                    TextView textView = new TextView(getActivity());
                    textView.setLayoutParams(paramsTextView);
                    textView.setText("    " + chuoi);
                    textView.setTextSize(21);
                    layout.addView(textView);
                }
            } else {
//                Tạo imageview
                ImageView imageView = new ImageView(getActivity());
                Glide.with(getActivity()).load(chuoi)
                        .placeholder(R.drawable.image_is_loading)
                        .error(R.drawable.error_load_image)
                        .into(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                if (i <= arr.length - 2) {
                    if (arr[i + 1].trim().equals("") && arr[i + 2] != null && !isLinear) {
//                    đã tạo linearLayout
                        isLinear = true;

//                    Tạo LinearLayout chứa Imageview
                        linearLayout = new LinearLayout(getActivity());
                        linearLayout.setLayoutParams(paramsLinearLayout);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setPadding(0, 20, 0, 0);

                        imageView.setLayoutParams(paramsImageViewDouble);
                        imageView.setPadding(0, 0, 10, 0);

                        linearLayout.addView(imageView);
                    } else {
                        ganHinh(imageView);
                    }
                } else {
                    ganHinh(imageView);
                }
            }
        }

        return view;
    }

    private void ganHinh(ImageView img) {
        if (isLinear) {
            isLinear = false;

            img.setLayoutParams(paramsImageViewDouble);
            img.setPadding(10, 0, 0, 0);

            linearLayout.addView(img);

            layout.addView(linearLayout);
        } else {
            img.setLayoutParams(paramsImageView);
            img.setPadding(0, 20, 0, 0);

            layout.addView(img);
        }
    }
}
