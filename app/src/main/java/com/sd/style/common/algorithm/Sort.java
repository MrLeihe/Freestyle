package com.sd.style.common.algorithm;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

/**
 * Author: HeLei on 2017/9/4 23:56
 * 算法
 */

public class Sort {

    /**
     * 字符串递归反转
     */
    private String reverse(String str) {
        if(TextUtils.isEmpty(str) || str.length() <= 1) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }

    /**
     * 插入排序
     */
    private void insertSort() {
        int[] insert = {30, 4, 20, 2, 14, 16, -2, 90};
        int temp;
        for (int i = 1; i < insert.length; i++) {
            temp = insert[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (insert[j] > temp) {
                    insert[j + 1] = insert[j];
                } else {
                    break;
                }
            }

            insert[j+ 1] = temp;
        }
        Logger.d(insert);
    }

    /**
     * 冒泡排序
     */
    private void bubbleSort() {
        int[] bubble = {4, 29, 12, 91, 3, 2, 68, 100, 32};
        int temp;
        for (int i = 0; i < bubble.length; i++) {
            for (int j = i+ 1; j < bubble.length; j++) {
                if(bubble[i] > bubble[j]) {
                    temp = bubble[i];
                    bubble[i] = bubble[j];
                    bubble[j] = temp;
                }
            }
        }
        Logger.d(bubble);
    }

    /**
     * 选择排序
     */
    private void selectSort() {
        int[] selectArray = {6,3,10,5,25,12,0,9,56,1};
        int min;
        int temp;
        for (int i = 0; i< selectArray.length; i++) {
            min = selectArray[i];
            for (int j = i; j < selectArray.length; j++) {
                if(selectArray[j] < min) {
                    min = selectArray[j];
                    temp = selectArray[i];
                    selectArray[i] = min;
                    selectArray[j] = temp;
                }
            }
        }
        Logger.d(selectArray);
    }

    /**
     * 归并排序
     */
    private void mergeSort() {
        int[] one = {1, 3, 5, 9, 12};
        int[] two = {2, 4, 6, 10, 24};
        int[] sort = new int[one.length + two.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < one.length && j < two.length) {
            if (one[i] <= two[j]) {
                sort[k++] = one[i++];
            } else {
                sort[k++] = two[j++];
            }
        }

        while (i < one.length) {
            sort[k++]= one[i++];
        }

        while (j < one.length) {
            sort[k++] = two[j++];
        }

        Logger.d(sort);
    }

}
