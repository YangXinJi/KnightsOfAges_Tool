package com.huihuijiang.tool;

import android.widget.Spinner;

import com.huihuijiang.R;

public class ViewStyle {
    public ViewStyle() {
    }

    public void setSpinnerStyle(Spinner spinner){
        spinner.setPopupBackgroundResource(R.drawable.popupwindow);
        spinner.setBackgroundResource(androidx.appcompat.R.drawable.abc_spinner_textfield_background_material);
        spinner.setDropDownHorizontalOffset(20);
        spinner.setDropDownVerticalOffset(120);
    }
}
