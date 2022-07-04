package com.huihuijiang.tool;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.huihuijiang.ui.mainUI.CJBFragment;

import java.util.Arrays;

public class SpinnerZYClick_B implements android.widget.AdapterView.OnItemSelectedListener {
    private CJBFragment cjbFragment;
    private DataOfProfession_By_OvO data;
    private Spinner spinner;
    public SpinnerZYClick_B(Spinner spinner){
        cjbFragment=new CJBFragment();
        data=new DataOfProfession_By_OvO();
        this.spinner=spinner;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        data.setData(spinner.getSelectedItem().toString());
        cjbFragment.setMainData(data.getAll());
        Log.i("测试", "onItemSelected: "+ Arrays.toString(cjbFragment.getMainData()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
