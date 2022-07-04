package com.huihuijiang.tool;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.huihuijiang.R;

public class SpinnerZYSelect {
    public void setSpinnerZY(Context context, Spinner spinner,int n1,int n2){
        int n3=0;
        switch (n1){
            case 0:switch (n2){
                case 0:n3=0;break;
                case 1:n3=1;break;
                case 2:n3=2;break;
                case 3:n3=3;break;
                case 4:n3=4;break;
                case 5:n3=5;break;
            }break;
            case 1:switch (n2){
                case 0:n3=6;break;
                case 1:n3=7;break;
                case 2:n3=8;break;
                case 3:n3=9;break;
                case 4:n3=10;break;
                case 5:n3=11;break;
            }break;
            case 2:switch (n2){
                case 0:n3=12;break;
                case 1:n3=13;break;
                case 2:n3=14;break;
                case 3:n3=15;break;
                case 4:n3=16;break;
                case 5:n3=17;break;
            }break;
            case 3:switch (n2){
                case 0:n3=18;break;
                case 1:n3=19;break;
                case 2:n3=20;break;
                case 3:n3=21;break;
                case 4:n3=22;break;
                case 5:n3=23;break;
            }break;
        }
        String[] s=new String[]{"初始化失败"};
        switch (n3){
            case 0:s=context.getResources().getStringArray(R.array.profession_all);break;
            case 1:s=context.getResources().getStringArray(R.array.profession_qiBing);break;
            case 2:s=context.getResources().getStringArray(R.array.profession_gongBing);break;
            case 3:s=context.getResources().getStringArray(R.array.profession_buBing);break;
            case 4:s=context.getResources().getStringArray(R.array.profession_zhongBuBing);break;
            case 5:s=context.getResources().getStringArray(R.array.profession_teShu);break;
            case 6:s=context.getResources().getStringArray(R.array.profession_zhuanZhi1);break;
            case 7:s=context.getResources().getStringArray(R.array.profession_qiBing1);break;
            case 8:s=context.getResources().getStringArray(R.array.profession_gongBing1);break;
            case 9:s=context.getResources().getStringArray(R.array.profession_buBing1);break;
            case 10:s=context.getResources().getStringArray(R.array.profession_zhongBuBing1);break;
            case 11:s=context.getResources().getStringArray(R.array.profession_teShu1);break;
            case 12:s=context.getResources().getStringArray(R.array.profession_zhuanZhi2);break;
            case 13:s=context.getResources().getStringArray(R.array.profession_qiBing2);break;
            case 14:s=context.getResources().getStringArray(R.array.profession_gongBing2);break;
            case 15:s=context.getResources().getStringArray(R.array.profession_buBing2);break;
            case 16:s=context.getResources().getStringArray(R.array.profession_zhongBuBing2);break;
            case 17:s=context.getResources().getStringArray(R.array.profession_teShu2);break;
            case 18:s=context.getResources().getStringArray(R.array.profession_zhuanZhi3);break;
            case 19:s=context.getResources().getStringArray(R.array.profession_qiBing3);break;
            case 20:s=context.getResources().getStringArray(R.array.profession_gongBing3);break;
            case 21:s=context.getResources().getStringArray(R.array.profession_buBing3);break;
            case 22:s=context.getResources().getStringArray(R.array.profession_zhongBuBing3);break;
            case 23:s=context.getResources().getStringArray(R.array.profession_teShu3);break;
        }
        ArrayAdapter<String> data = new ArrayAdapter<>(context, R.layout.spinner_item,R.id.tv_spinner,s);
        data.setDropDownViewResource(R.layout.spinner_item_dropdown);
        spinner.setAdapter(data);
    }
}
