package com.zxh.rxjava2demo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by edianzu on 2017/10/11.
 */

public class EditTextWithClear extends EditText {
    public EditTextWithClear(Context context) {
        this(context, null);
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditTextWithClear(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
