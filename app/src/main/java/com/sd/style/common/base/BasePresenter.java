package com.sd.style.common.base;

/**
 * @author: SanShi
 * @description:
 * @date 2017/8/1  15:04
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
