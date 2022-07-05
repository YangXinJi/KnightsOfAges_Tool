package com.huihuijiang.tool;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.lifecycle.ViewModel;

import com.huihuijiang.ui.mainUI.CJBViewModel;

public class SpinnerZYClick_A implements android.widget.AdapterView.OnItemSelectedListener {
    private final Context context;
    private final Spinner spinner1;
    private final Spinner spinner2;
    private final Spinner spinner3;
    private final SpinnerZYSelect spinnerZYSelect;
    public SpinnerZYClick_A(Context context, Spinner spinner1, Spinner spinner2, Spinner spinner3){
        this.context=context;
        this.spinner1=spinner1;
        this.spinner2=spinner2;
        this.spinner3=spinner3;
        spinnerZYSelect=new SpinnerZYSelect();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerZYSelect.setSpinnerZY(context,spinner3,spinner1.getSelectedItemPosition(),spinner2.getSelectedItemPosition());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
