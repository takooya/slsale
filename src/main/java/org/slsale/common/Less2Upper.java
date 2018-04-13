package org.slsale.common;

import org.junit.Test;

/**
 * @Author takooya
 * @Description
 * @Date:created in 18:51 2018/4/12
 */
public class Less2Upper {
    private final static String[] upperDigits = {"零", "壹", "贰", "叁", "肆", "伍"
            , "陆", "柒", "捌", "玖", "拾", "佰", "仟"};

    public static String change(int cutter) {
        String[] addString = new String[3];
        //str0:一万以下部分
        //str1:一亿到一万部分
        //str2:一亿以上部分
        boolean mFlag = true;
        if (cutter < 0) {
            cutter = -cutter;
            mFlag = false;
        }
        if (cutter > Integer.MAX_VALUE) {//超出计算范围
            System.out.println("超出计算能力");
        } else if (cutter == 0) {
            return upperDigits[0];
        } else {
            for (int i = 0; cutter > 0; i++, cutter = cutter / 10000) {
                StringBuilder numString = new StringBuilder();
                //获得每个千分位的 直译数
                //0300==>零叁佰零零
                //1020==>壹仟零贰拾零
                for (int j = 1000; j != 0; j /= 10) {
                    if (cutter >= j && (cutter / j % 10) != 0) {
                        numString.append(upperDigits[cutter / j % 10]);
                        if (j != 1) {
                            numString.append(upperDigits[(int) Math.log10(j) + 9]);
                        }
                    } else {
                        numString.append(upperDigits[0]);
                    }
                }
                //去除头部的 0
                while (numString.toString().startsWith(upperDigits[0])) {
                    numString = numString.deleteCharAt(0);
                }
                //去除尾部的0
                while (numString.toString().endsWith(upperDigits[0])) {
                    numString = numString.deleteCharAt(numString.length() - 1);
                }
                //双0 变 一个0
                while (numString.indexOf(upperDigits[0] + upperDigits[0]) != -1) {
                    numString.deleteCharAt(numString.indexOf(upperDigits[0] + upperDigits[0]));
                }
                boolean isNull = numString == null || numString.toString().trim().equals("");
                addString[i] = numString + (i == 1 && !isNull ? "万" : "") + (i == 2 ? "亿" : "");
            }
        }
        //两段间 无千位 添加 零
        if (addString[1] != null && !addString[0].contains("仟") && !addString[0].equals("")) {
            addString[1] += upperDigits[0];
        }
        if (addString[2] != null && !addString[1].contains("仟")) {
            addString[2] += upperDigits[0];
        }
        StringBuilder result = new StringBuilder();
        result.append((addString[2] != null ? addString[2] : "") +
                (addString[1] != null ? addString[1] : "") +
                (addString[0] != null ? addString[0] : ""));
        //再次排除双0
        while (result.indexOf(upperDigits[0] + upperDigits[0]) != -1) {
            result.deleteCharAt(result.indexOf(upperDigits[0] + upperDigits[0]));
        }
        //去除尾部的0
        while (result.toString().endsWith(upperDigits[0])) {
            result = result.deleteCharAt(result.length() - 1);
        }
        return mFlag ? "" : "负" + result.toString();
    }

    @Test
    public void test() {
        System.out.println(change(-100000001));
    }
}
