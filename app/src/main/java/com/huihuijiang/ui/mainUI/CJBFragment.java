package com.huihuijiang.ui.mainUI;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.huihuijiang.R;
import com.huihuijiang.databinding.FragmentCjbBinding;
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

        //初始化基础属性视图
        binding.cardSx.spinnerQl.sx.setAdapter(viewModel.getSpinner());
        binding.cardSx.spinnerQl.sxLevel.setAdapter(viewModel.getSpinner_sx());
        viewStyle.setSpinnerStyle(binding.cardSx.spinnerQl.sx);
        viewStyle.setSpinnerStyle(binding.cardSx.spinnerQl.sxLevel);
        binding.cardSx.spinnerQl.sx.setSelection(viewModel.getSpinner_position());
        binding.cardSx.spinnerQl.sxLevel.setSelection(viewModel.getSpinner_sx_position());
        binding.cardSx.spinnerQl.sx.setOnItemSelectedListener(new SpinnerSXClick());
        binding.cardSx.spinnerQl.sxLevel.setOnItemSelectedListener(new SpinnerSXClick());

        //初始化主职业等级
        binding.mainZYDJ.zyLevel.setAdapter(viewModel.getSpinner_mainZYDJ_zyLevel());
        binding.mainZYDJ.zyBingZhong.setAdapter(viewModel.getSpinner_mainZYDJ_zyBingZhong());
        viewStyle.setSpinnerStyle(binding.mainZYDJ.zyLevel);
        viewStyle.setSpinnerStyle(binding.mainZYDJ.zyBingZhong);
        viewStyle.setSpinnerStyle(binding.mainZYDJ.zyZhiYe);
        binding.mainZYDJ.zyLevel.setOnItemSelectedListener(new SpinnerZYClick_A(getActivity(),binding.mainZYDJ.zyLevel,binding.mainZYDJ.zyBingZhong,binding.mainZYDJ.zyZhiYe));
        binding.mainZYDJ.zyBingZhong.setOnItemSelectedListener(new SpinnerZYClick_A(getActivity(),binding.mainZYDJ.zyLevel,binding.mainZYDJ.zyBingZhong,binding.mainZYDJ.zyZhiYe));
        binding.mainZYDJ.zyZhiYe.setOnItemSelectedListener(new SpinnerZYClick_B












                (binding.mainZYDJ.zyZhiYe));
        return binding.getRoot();
    }

    private class SpinnerSXClick implements AdapterView.OnItemSelectedListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (binding.cardSx.spinnerQl.sx.equals(parent)) {
                viewModel.setSpinner_position(position);
                double d=viewModel.getSpinnerDataMutableLiveData().get(position);
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
}