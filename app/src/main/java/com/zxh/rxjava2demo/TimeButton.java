package com.zxh.rxjava2demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.IntEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import android.animation.ValueAnimator;


import com.zxh.rxjava2demo.R;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by edianzu on 2017/10/11.
 */

public class TimeButton extends AppCompatButton implements View.OnClickListener {
    private int time = 60;// 倒计时长度,这里给了默认60秒
    private String textafter = this.getResources().getString(R.string.send_again) + this.getResources().getString(R.string.second_format);
    private String textbefore = this.getResources().getString(R.string.get_captcha);
    private int colorafter;
    private int colorbefore;
    private ValueAnimator mAnimator;
    private OnClickListener mOnclickListener;


    public TimeButton(Context context) {
        this(context, null);
        setOnClickListener(this);

    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        initStyle();
    }

    private void initStyle() {
        textafter = getResources().getString(R.string.send_again) + this.getResources().getString(R.string.second_format);
        textbefore = getResources().getString(R.string.get_captcha);
        colorafter = ContextCompat.getColor(getContext(), R.color.white);
        colorbefore = ContextCompat.getColor(getContext(), R.color.blue_assist);
        setText(textbefore);
        setTextColor(colorbefore);
        setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selector_band_card_get_captcha, null));
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else
            this.mOnclickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (mOnclickListener != null)
            mOnclickListener.onClick(v);
        startCountDownAnim();
    }

    private ValueAnimator getAnimator() {
        if (mAnimator == null) {
            mAnimator = ValueAnimator.ofInt(time, 0);
        }
        return mAnimator;
    }

    /**
     * 显示倒计时动画
     */
    private void startCountDownAnim() {
        getAnimator().setDuration(time * 1000);//设置动画播放总时长
        getAnimator().setRepeatCount(0);
        getAnimator().setRepeatMode(ValueAnimator.RESTART);
        getAnimator().setInterpolator(new LinearInterpolator());
        getAnimator().setEvaluator(new IntEvaluator());
        getAnimator().addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(String.format(textafter, (int) animation.getAnimatedValue()));
                setUnclickable();
            }
        });
        getAnimator().addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setClickable();
            }
        });
        getAnimator().start();
    }

    /**
     * 可以点击
     */
    private void setClickable() {
        setTextColor(colorbefore);
        setText(textbefore);
        setClickable(true);
        setEnabled(true);
    }

    /**
     * 不可以点击
     */
    private void setUnclickable() {
        setTextColor(colorafter);
        setClickable(false);
        setEnabled(false);
    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void cancel() {
        getAnimator().cancel();
    }

    /**
     * 设置计时时候显示的文本
     */
    public TimeButton setClickAfter(String text) {
        this.textafter = text;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public TimeButton setClickBefore(String text) {
        this.textbefore = text;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param time 时间 默认秒
     * @return
     */
    public TimeButton setTime(int time) {
        this.time = time;
        return this;
    }
}