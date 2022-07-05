package com.huihuijiang.tool;

import android.content.res.Resources;
import android.util.TypedValue;

public class GeShi {
    public int px_to_dp(int px, Resources r){
        int dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, px, r.getDisplayMetrics());
        return dp;
    }
    //阿拉伯数字转汉字
    public String toChinese(int d) {
        String result = "";
        String str=String.valueOf(d);
        String[] shu = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] danWei = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += shu[num] + danWei[n - 2 - i];
            } else {
                result += shu[num];
            }
        }
        return result;
    }
}
