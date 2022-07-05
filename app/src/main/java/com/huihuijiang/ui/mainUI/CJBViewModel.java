package com.huihuijiang.ui.mainUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huihuijiang.R;

import java.util.ArrayList;
import java.util.List;

public class CJBViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    public void setContext(Context context) {
        this.context = context;
    }

    private MutableLiveData<List<Double>> spinnerDataMutableLiveData;
    public void setSpinnerDataMutableLiveData(int position,double n){
        if (spinnerDataMutableLiveData==null){
            loadSpinnerDataMutableLiveData();
        }
        List<Double> doubleList=this.spinnerDataMutableLiveData.getValue();
        doubleList.set(position,n);
        this.spinnerDataMutableLiveData.setValue(doubleList);
    }
    public void loadSpinnerDataMutableLiveData(){
        spinnerDataMutableLiveData=new MutableLiveData<>();
        List<Double> doubleList=new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            doubleList.add(0.0);
        }
        this.spinnerDataMutableLiveData.setValue(doubleList);
    }
    public List<Double> getSpinnerDataMutableLiveData() {
        if (spinnerDataMutableLiveData==null){
            loadSpinnerDataMutableLiveData();
        }
        return spinnerDataMutableLiveData.getValue();
    }

    private MutableLiveData<Integer> spinner_position;
    public int getSpinner_position() {
        if (spinner_position==null){
            spinner_position=new MutableLiveData<>();
            spinner_position.setValue(0);
        }
        return spinner_position.getValue();
    }
    public void setSpinner_position(int spinner_position) {
        this.spinner_position.setValue(spinner_position);
    }

    private MutableLiveData<Integer> spinner_sx_position;
    public int getSpinner_sx_position() {
        if (spinner_sx_position==null){
            spinner_sx_position=new MutableLiveData<>();
            spinner_sx_position.setValue(0);
        }
        return spinner_sx_position.getValue();
    }
    public void setSpinner_sx_position(int spinner_sx_position) {
        this.spinner_sx_position.setValue(spinner_sx_position);
    }

    private MutableLiveData<Integer> spinner_mainZYDJ_zyLevel_position;
    public int getSpinner_mainZYDJ_zyLevel_position() {
        if (spinner_mainZYDJ_zyLevel_position==null){
            spinner_mainZYDJ_zyLevel_position=new MutableLiveData<>();
            spinner_mainZYDJ_zyLevel_position.setValue(0);
        }
        return spinner_mainZYDJ_zyLevel_position.getValue();
    }
    public void setSpinner_mainZYDJ_zyLevel_position(int spinner_sx_position) {
        this.spinner_mainZYDJ_zyLevel_position.setValue(spinner_sx_position);
    }

    private MutableLiveData<Integer> spinner_mainZYDJ_zyBingZhong_position;
    public int getSpinner_mainZYDJ_zyBingZhong_position() {
        if (spinner_mainZYDJ_zyBingZhong_position==null){
            spinner_mainZYDJ_zyBingZhong_position=new MutableLiveData<>();
            spinner_mainZYDJ_zyBingZhong_position.setValue(0);
        }
        return spinner_mainZYDJ_zyLevel_position.getValue();
    }
    public void setSpinner_mainZYDJ_zyBingZhong_position(int spinner_sx_position) {
        this.spinner_mainZYDJ_zyBingZhong_position.setValue(spinner_sx_position);
    }

    private MutableLiveData<ArrayAdapter<String>> spinner;
    public ArrayAdapter<String> getSpinner(){
        if (spinner == null){
            spinner = new MutableLiveData<>();
            loadSpinner();
        }
        return spinner.getValue();
    }
    @SuppressLint("ResourceType")
    private void loadSpinner(){
        String[] s=context.getResources().getStringArray(R.array.liu_wei);
        setSpinner(s);
    }
    public void setSpinner(String[] s) {
        ArrayAdapter<String> data = new ArrayAdapter<>(context, R.layout.spinner_item,R.id.tv_spinner,s);
        data.setDropDownViewResource(R.layout.spinner_item_dropdown);
        spinner.setValue(data);
    }

    private MutableLiveData<ArrayAdapter<String>> spinner_sx;
    public ArrayAdapter<String> getSpinner_sx() {
        if (spinner_sx == null){
            spinner_sx = new MutableLiveData<>();
            loadSpinner_sx();
        }
        return spinner_sx.getValue();
    }
    @SuppressLint("ResourceType")
    private void loadSpinner_sx(){
        String[] s=context.getResources().getStringArray(R.array.liu_wei_l);
        setSpinner_sx(s);
    }
    public void setSpinner_sx(String[] s) {
        ArrayAdapter<String> data = new ArrayAdapter<>(context,  R.layout.spinner_item,R.id.tv_spinner,s);
        data.setDropDownViewResource(R.layout.spinner_item_dropdown);
        spinner_sx.setValue(data);
    }

    private MutableLiveData<ArrayAdapter<String>> spinner_mainZYDJ_zyLevel;
    public ArrayAdapter<String> getSpinner_mainZYDJ_zyLevel(){
        if (spinner_mainZYDJ_zyLevel == null){
            spinner_mainZYDJ_zyLevel = new MutableLiveData<>();
            loadSpinner_mainZYDJ_zyLevel();
        }
        return spinner_mainZYDJ_zyLevel.getValue();
    }
    @SuppressLint("ResourceType")
    private void loadSpinner_mainZYDJ_zyLevel(){
        String[] s=context.getResources().getStringArray(R.array.profession_zhuanZhi);
        setSpinner_mainZYDJ_zyLevel(s);
    }
    public void setSpinner_mainZYDJ_zyLevel(String[] s) {
        ArrayAdapter<String> data = new ArrayAdapter<>(context, R.layout.spinner_item,R.id.tv_spinner,s);
        data.setDropDownViewResource(R.layout.spinner_item_dropdown);
        spinner_mainZYDJ_zyLevel.setValue(data);
    }

    private MutableLiveData<ArrayAdapter<String>> spinner_mainZYDJ_zyBingZhong;
    public ArrayAdapter<String> getSpinner_mainZYDJ_zyBingZhong(){
        if (spinner_mainZYDJ_zyBingZhong == null){
            spinner_mainZYDJ_zyBingZhong = new MutableLiveData<>();
            loadSpinner_mainZYDJ_zyBingZhong();
        }
        return spinner_mainZYDJ_zyBingZhong.getValue();
    }
    @SuppressLint("ResourceType")
    private void loadSpinner_mainZYDJ_zyBingZhong(){
        String[] s=context.getResources().getStringArray(R.array.profession_bingZhong);
        setSpinner_mainZYDJ_zyBingZhong(s);
    }
    public void setSpinner_mainZYDJ_zyBingZhong(String[] s) {
        ArrayAdapter<String> data = new ArrayAdapter<>(context, R.layout.spinner_item,R.id.tv_spinner,s);
        data.setDropDownViewResource(R.layout.spinner_item_dropdown);
        spinner_mainZYDJ_zyBingZhong.setValue(data);
    }
}
