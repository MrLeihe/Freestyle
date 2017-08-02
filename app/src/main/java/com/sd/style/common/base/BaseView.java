package com.sd.style.common.base;

/**
 * @author: SanShi
 * @description: mvp中v的基类
 * @date 2017/8/1  15:27
 */

public interface BaseView {

    /**
     * <p>显示加载进度框</p>
     */
    void showLoading();

    /**
     * <p>隐藏加载进度框</p>
     */
    void hideLoading();

    /**
     * <p>显示加载失败布局</p>
     */
    void showError();

    /**
     * <p>隐藏加载失败布局</p>
     */
    void hideError();
}
