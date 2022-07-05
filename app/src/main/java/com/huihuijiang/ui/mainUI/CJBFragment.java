package com.huihuijiang.ui.mainUI;

import static android.content.Context.VIBRATOR_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.huihuijiang.R;
import com.huihuijiang.databinding.FragmentCjbBinding;
import com.huihuijiang.tool.GeShi;
import com.huihuijiang.tool.OnClickListener_Vibrator;
import com.huihuijiang.tool.RadarData;
import com.huihuijiang.tool.SpinnerZYClick_A;
import com.huihuijiang.tool.SpinnerZYClick_B;
import com.huihuijiang.tool.ViewStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CJBFragment extends Fragment {

    private CJBViewModel viewModel;
    private FragmentCjbBinding binding;
    private ViewStyle viewStyle;
    private double[] mainData = new double[6];
    private GeShi geShi;
    private int num_zhiYe;

    public void setMainData(double[] mainData) {
        this.mainData = mainData;
    }

    public double[] getMainData() {
        return mainData;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCjbBinding.inflate(inflater, container, false);

        viewModel=new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CJBViewModel.class);
        viewModel.setContext(getActivity());

        viewStyle=new ViewStyle();
        geShi=new GeShi();

        //初始化雷达图
        List<RadarData> dataList = new ArrayList<>();
        for (int i = 5; i > 2; i--) {
            RadarData data = new RadarData(getResources().getStringArray(R.array.liu_wei)[i],0.1*100/1.3);
            dataList.add(data);
        }
        for (int i = 0; i < 3; i++) {
            RadarData data = new RadarData(getResources().getStringArray(R.array.liu_wei)[i],0.1*100/1.3);
            dataList.add(data);
        }
        binding.cardSx.radarView.radarView.setDataList(dataList);
        binding.viewRadar1.setData(getResources().getStringArray(R.array.liu_wei),new double[]{0,0,0,0,0,0});

        Thread thread=new Thread(new MyThread());
        thread.setName("初始化view");
        thread.start();

        //初始化基础属性视图数据
        binding.cardSx.spinnerQl.sx.setAdapter(viewModel.getSpinner());
        binding.cardSx.spinnerQl.sxLevel.setAdapter(viewModel.getSpinner_sx());
        binding.cardSx.spinnerQl.sx.setSelection(viewModel.getSpinner_position());
        binding.cardSx.spinnerQl.sxLevel.setSelection(viewModel.getSpinner_sx_position());
        for (int i = 0; i < 6; i++) {
            String s= String.valueOf(viewModel.getSpinnerDataMutableLiveData().get(i));
            if (!s.equals("0")){
                binding.viewRadar1.reFlash(i, Double.parseDouble(s));

                switch (i){
                    case 0:
                        binding.cardSx.radarView.radarView.refresh(3,s);
                        break;
                    case 1:
                        binding.cardSx.radarView.radarView.refresh(4,s);
                        break;
                    case 2:
                        binding.cardSx.radarView.radarView.refresh(5,s);
                        break;
                    case 3:
                        binding.cardSx.radarView.radarView.refresh(2,s);
                        break;
                    case 4:
                        binding.cardSx.radarView.radarView.refresh(1,s);
                        break;
                    case 5:
                        binding.cardSx.radarView.radarView.refresh(0,s);
                        break;
                }
            }
        }

        //初始化主职业等级数据
        binding.mainZYDJ.zyLevel.setAdapter(viewModel.getSpinner_mainZYDJ_zyLevel());
        binding.mainZYDJ.zyBingZhong.setAdapter(viewModel.getSpinner_mainZYDJ_zyBingZhong());
        binding.mainZYDJ.zyLevel.setSelection(viewModel.getSpinner_mainZYDJ_zyLevel_position());
        binding.mainZYDJ.zyBingZhong.setSelection(viewModel.getSpinner_mainZYDJ_zyBingZhong_position());

        return binding.getRoot();
    }

    public class MyThread implements Runnable{
        @Override
        public void run() {
            //初始化基础属性视图view
            viewStyle.setSpinnerStyle(binding.cardSx.spinnerQl.sx);
            viewStyle.setSpinnerStyle(binding.cardSx.spinnerQl.sxLevel);
            binding.cardSx.spinnerQl.sx.setOnItemSelectedListener(new SpinnerSXClick());
            binding.cardSx.spinnerQl.sxLevel.setOnItemSelectedListener(new SpinnerSXClick());
            //初始化主职业等级view
            viewStyle.setSpinnerStyle(binding.mainZYDJ.zyLevel);
            viewStyle.setSpinnerStyle(binding.mainZYDJ.zyBingZhong);
            viewStyle.setSpinnerStyle(binding.mainZYDJ.zyZhiYe);
            binding.mainZYDJ.zyLevel.setOnItemSelectedListener(new SpinnerZYClick(getActivity(),binding.mainZYDJ.zyLevel,binding.mainZYDJ.zyBingZhong,binding.mainZYDJ.zyZhiYe));
            binding.mainZYDJ.zyBingZhong.setOnItemSelectedListener(new SpinnerZYClick_A(getActivity(),binding.mainZYDJ.zyLevel,binding.mainZYDJ.zyBingZhong,binding.mainZYDJ.zyZhiYe));
            binding.mainZYDJ.zyZhiYe.setOnItemSelectedListener(new SpinnerZYClick_B(binding.mainZYDJ.zyZhiYe));

            binding.cjbDjSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    binding.cjbDjValue.setText(String.valueOf(progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            binding.btnAdd.setOnClickListener(new OnClickListener_Vibrator() {
                @SuppressLint({"ResourceAsColor", "SetTextI18n"})
                @Override
                public void onClick(View v) {
                    super.onClick(v);
                    //职业tv
                    TextView textView=new TextView(getActivity());
                    textView.setTextSize(15);
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setText(geShi.toChinese(num_zhiYe+2)+"级职业");
                    binding.linearLayout.addView(textView);
                    //spinner线性布局
                    LinearLayout linearLayout_spinner=new LinearLayout(getActivity());
                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-1,-2);
                    params.setMargins(5,5,5,5);
                    linearLayout_spinner.setLayoutParams(params);
                    binding.linearLayout.addView(linearLayout_spinner);
                    //spinner
                    Spinner spinner_zyLevel=new Spinner(getActivity());
                    Spinner spinner_zyBingZhong=new Spinner(getActivity());
                    Spinner spinner_zyZhiYe=new Spinner(getActivity());
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            viewStyle.setSpinnerStyle(spinner_zyLevel);
                            viewStyle.setSpinnerStyle(spinner_zyBingZhong);
                            viewStyle.setSpinnerStyle(spinner_zyZhiYe);
                            spinner_zyLevel.setOnItemSelectedListener(new SpinnerZYClick(getActivity(),spinner_zyLevel,spinner_zyBingZhong,spinner_zyZhiYe));
                            spinner_zyBingZhong.setOnItemSelectedListener(new SpinnerZYClick_A(getActivity(),spinner_zyLevel,spinner_zyBingZhong,spinner_zyZhiYe));
                            spinner_zyZhiYe.setOnItemSelectedListener(new SpinnerZYClick_B(spinner_zyZhiYe));
                        }
                    }.start();
                    params=new LinearLayout.LayoutParams(0,geShi.px_to_dp(60,getResources()));
                    params.weight=1;
                    params.setMargins(5,5,5,5);
                    spinner_zyLevel.setLayoutParams(params);
                    spinner_zyLevel.setAdapter(viewModel.getSpinner_mainZYDJ_zyLevel());
                    linearLayout_spinner.addView(spinner_zyLevel);
                    spinner_zyBingZhong.setLayoutParams(params);
                    spinner_zyBingZhong.setAdapter(viewModel.getSpinner_mainZYDJ_zyBingZhong());
                    linearLayout_spinner.addView(spinner_zyBingZhong);
                    spinner_zyZhiYe.setLayoutParams(params);
                    linearLayout_spinner.addView(spinner_zyZhiYe);
                    //等级input_layout
                    TextInputLayout inputLayout=new TextInputLayout(getActivity());
                    inputLayout.setHint(R.string.dengJi);
                    inputLayout.setHintTextColor(ColorStateList.valueOf(R.color.hint_color));
                    params=new LinearLayout.LayoutParams(-1,geShi.px_to_dp(60,getResources()));
                    params.setMargins(5,5,5,5);
                    inputLayout.setLayoutParams(params);
                    binding.linearLayout.addView(inputLayout);
                    TextInputEditText editText=new TextInputEditText(getActivity());
                    editText.setTextSize(18);
                    params=new LinearLayout.LayoutParams(-1,-1);
                    editText.setLayoutParams(params);
                    inputLayout.addView(editText);
                    MaterialDivider divider=new MaterialDivider(getActivity());
                    divider.setDividerColorResource(R.color.dividerColor);
                    params=new LinearLayout.LayoutParams(-1,geShi.px_to_dp(2,getResources()));
                    params.setMargins(10,0,10,0);
                    divider.setLayoutParams(params);
                    binding.linearLayout.addView(divider);
                    num_zhiYe++;
                }
            });
        }
    }

    private class SpinnerSXClick implements AdapterView.OnItemSelectedListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (binding.cardSx.spinnerQl.sx.equals(parent)) {
                viewModel.setSpinner_position(position);
                double d=viewModel.getSpinnerDataMutableLiveData().get(position);
                Log.i("测试", "测试"+d);
                if (d>1.1){ binding.cardSx.spinnerQl.sxLevel.setSelection(0); }
                else if (d>0.95){ binding.cardSx.spinnerQl.sxLevel.setSelection(1);}
                else if (d>0.85){ binding.cardSx.spinnerQl.sxLevel.setSelection(2);}
                else if (d>0.65){ binding.cardSx.spinnerQl.sxLevel.setSelection(3);}
                else if (d>0.50){ binding.cardSx.spinnerQl.sxLevel.setSelection(4);}
                else if (d>0.35){ binding.cardSx.spinnerQl.sxLevel.setSelection(5);}
                else if (d>0.20){ binding.cardSx.spinnerQl.sxLevel.setSelection(6);}
                else { binding.cardSx.spinnerQl.sxLevel.setSelection(7);}
                binding.cardSx.spinnerQl.etSx.setText(String.valueOf(d));
            } else if (binding.cardSx.spinnerQl.sxLevel.equals(parent)) {
                viewModel.setSpinner_sx_position(position);
                switch (parent.getSelectedItemPosition()){
                    case 0:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(1.2));
                        break;
                    case 1:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(1.1));
                        break;
                    case 2:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(0.95));
                        break;
                    case 3:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(0.8));
                        break;
                    case 4:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(0.6));
                        break;
                    case 5:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(0.4));
                        break;
                    case 6:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(0.2));
                        break;
                    case 7:
                        binding.cardSx.spinnerQl.etSx.setText(String.valueOf(0.1));
                        break;
                }
                String s = Objects.requireNonNull(binding.cardSx.spinnerQl.etSx.getText()).toString();
                switch (binding.cardSx.spinnerQl.sx.getSelectedItemPosition()){
                    case 0:
                        binding.cardSx.radarView.radarView.refresh(3,s);
                        binding.viewRadar1.reFlash(0, Double.parseDouble(s));
                        break;
                    case 1:
                        binding.cardSx.radarView.radarView.refresh(4,s);
                        binding.viewRadar1.reFlash(1, Double.parseDouble(s));
                        break;
                    case 2:
                        binding.cardSx.radarView.radarView.refresh(5,s);
                        binding.viewRadar1.reFlash(2, Double.parseDouble(s));
                        break;
                    case 3:
                        binding.cardSx.radarView.radarView.refresh(2,s);
                        binding.viewRadar1.reFlash(3, Double.parseDouble(s));
                        break;
                    case 4:
                        binding.cardSx.radarView.radarView.refresh(1,s);
                        binding.viewRadar1.reFlash(4, Double.parseDouble(s));
                        break;
                    case 5:
                        binding.cardSx.radarView.radarView.refresh(0,s);
                        binding.viewRadar1.reFlash(5, Double.parseDouble(s));
                        break;
                }
                viewModel.setSpinnerDataMutableLiveData(
                        binding.cardSx.spinnerQl.sx.getSelectedItemPosition(),
                        Double.parseDouble(s)
                );
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class SpinnerZYClick extends SpinnerZYClick_A implements android.widget.AdapterView.OnItemSelectedListener{
        public SpinnerZYClick(Context context, Spinner spinner1, Spinner spinner2, Spinner spinner3) {
            super(context, spinner1, spinner2, spinner3);
        }
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            super.onItemSelected(parent,view,position,id);
            if (parent.getId()==binding.mainZYDJ.zyLevel.getId()){
                viewModel.setSpinner_mainZYDJ_zyLevel_position(position);
            }else if (parent.getId()==binding.mainZYDJ.zyBingZhong.getId()){
                viewModel.setSpinner_mainZYDJ_zyBingZhong_position(position);
            }
        }
    }
}