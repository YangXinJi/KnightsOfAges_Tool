package com.huihuijiang.ui.mainUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.huihuijiang.tool.SpinnerZYClick_A;
import com.huihuijiang.tool.SpinnerZYClick_B;
import com.huihuijiang.tool.ViewStyle;

import java.util.Objects;

public class CJBFragment extends Fragment {

    private CJBViewModel viewModel;
    private FragmentCjbBinding binding;
    private ViewStyle viewStyle;
    private double[] zyData = new double[]{0,0,0,0,0,0};
    private GeShi geShi;
    private int num_zhiYe;
    private TextView[] tvs_ql;

    public void setZyData(double[] mainData) {
        this.zyData = mainData;
    }

    public double[] getZyData() {
        return zyData;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCjbBinding.inflate(inflater, container, false);
        viewModel=new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CJBViewModel.class);
        viewModel.setContext(getActivity());
        viewStyle=new ViewStyle();
        geShi=new GeShi();
        /**
         *初始化潜力雷达图旁边的textview
         */
        tvs_ql=new TextView[]{
                binding.cardSx.tvLl,
                binding.cardSx.tvJq,
                binding.cardSx.tvMj,
                binding.cardSx.tvTz,
                binding.cardSx.tvGz,
                binding.cardSx.tvYz
        };
        //初始化雷达图
        binding.cardSx.viewRadar.setData(getResources().getStringArray(R.array.liu_wei), viewModel.getSpinnerDataMutableLiveData());

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
                binding.cardSx.viewRadar.reFlash(i, Double.parseDouble(s));
            }
        }

        //初始化主职业等级数据
        binding.mainZYDJ.zyLevel.setAdapter(viewModel.getSpinner_mainZYDJ_zyLevel());
        binding.mainZYDJ.zyBingZhong.setAdapter(viewModel.getSpinner_mainZYDJ_zyBingZhong());
        binding.mainZYDJ.zyLevel.setSelection(viewModel.getSpinner_mainZYDJ_zyLevel_position());
        binding.mainZYDJ.zyBingZhong.setSelection(viewModel.getSpinner_mainZYDJ_zyBingZhong_position());

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
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
            binding.cardSx.spinnerQl.etSx.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tvs_ql[binding.cardSx.spinnerQl.sx.getSelectedItemPosition()]
                            .setText(getResources().getStringArray(R.array.liu_wei)[binding.cardSx.spinnerQl.sx.getSelectedItemPosition()]
                                    +":"+binding.cardSx.spinnerQl.etSx.getText());
                    if (!binding.cardSx.spinnerQl.etSx.getText().toString().isEmpty()&&!binding.cardSx.spinnerQl.etSx.getText().toString().equals(".")){
                        try {
                            viewModel.setSpinnerDataMutableLiveData(binding.cardSx.spinnerQl.sx.getSelectedItemPosition(), Double.parseDouble(binding.cardSx.spinnerQl.etSx.getText().toString()));
                            double num=0;
                            for (int j = 0; j < viewModel.getSpinnerDataMutableLiveData().size(); j++) {
                                num+=viewModel.getSpinnerDataMutableLiveData().get(j);
                            }
                            binding.cardSx.tvZql.setText("总潜力:"+num);
                        } catch (NumberFormatException e) {
                            String str="请规范输入";
                            e.printStackTrace();
                            tvs_ql[binding.cardSx.spinnerQl.sx.getSelectedItemPosition()]
                                    .setText(getResources().getStringArray(R.array.liu_wei)[binding.cardSx.spinnerQl.sx.getSelectedItemPosition()]+":"+str);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            for (int i = 0; i < 6; i++) {
                tvs_ql[i].setText(getResources().getStringArray(R.array.liu_wei)[i]+":"+viewModel.getSpinnerDataMutableLiveData().get(i));
            }
            //等级进度条
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
            //添加次级职业按钮
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
                Log.i("测试", "选择潜力"+d);
                binding.cardSx.spinnerQl.etSx.setText(String.valueOf(d));
            } else if (binding.cardSx.spinnerQl.sxLevel.equals(parent)) {
                Log.i("测试", "选择潜力等级");
                viewModel.setSpinner_sx_position(position);
                binding.cardSx.spinnerQl.etSx.setText(getResources().getTextArray(R.array.qianLi_z)[parent.getSelectedItemPosition()]);
                String s = Objects.requireNonNull(binding.cardSx.spinnerQl.etSx.getText()).toString();
                binding.cardSx.viewRadar.reFlash(binding.cardSx.spinnerQl.sx.getSelectedItemPosition(), Double.parseDouble(s));
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