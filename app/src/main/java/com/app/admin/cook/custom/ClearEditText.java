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

public class ClearEditText extends EditText {
    private Drawable clear, nonClear;
    private Boolean visible = false;
    private Drawable drawable;

    public ClearEditText(Context context) {
        super(context);
        khoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoiTao();
    }

    private void khoiTao() {
        clear = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonClear = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (lengthAfter == 0 && start == 0) {
            visible = false;
            xuLyShow();
        } else {
            visible = true;
            xuLyShow();
        }
    }

    private void xuLyShow() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible ? clear : nonClear;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (visible) {
            if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width() - 20)) {
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }
}
