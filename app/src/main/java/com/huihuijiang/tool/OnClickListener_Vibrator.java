package com.huihuijiang.tool;

import static android.content.Context.VIBRATOR_SERVICE;

import android.annotation.SuppressLint;
import android.os.Vibrator;
import android.view.View;

public class OnClickListener_Vibrator implements View.OnClickListener {
    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        Vibrator vibrator = (Vibrator) v.getContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(10);
    }
}