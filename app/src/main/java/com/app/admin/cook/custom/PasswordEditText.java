package com.app.admin.cook.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.app.admin.cook.R;

/**
 * Created by Admin on 4/6/2018.
 */

public class PasswordEditText extends EditText {
    private Drawable eye, eyeStrike;
    private Boolean visible = true;
    private Drawable drawable;
    private static final int ALPHA = (int) (255 * .55f);

    public PasswordEditText(Context context) {
        super(context);
        khoiTao();
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao();
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoiTao();
    }


    private void khoiTao(){
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        xuLyShow();
    }

    private void xuLyShow() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = !visible ? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) - 20) {
            visible = !visible;
            xuLyShow();
            invalidate();
        }

        return super.onTouchEvent(event);
    }
}
