package com.sd.style.ui;

import android.text.TextUtils;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.sd.style.R;
import com.sd.style.common.base.BaseActivity;
import com.sd.style.common.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button)
    Button mButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void bindData() {
        mButton.setText("java");
    }

    @OnClick(R.id.button)
    public void onClick(){
        subscribe();
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    private void subscribe(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("大家好");
                emitter.onNext("你是瓜皮");
                emitter.onNext("烟花三月下扬州");
                emitter.onComplete();
                Logger.e("emitter complete");
                emitter.onNext("good");
                Logger.e("emitter good");
            }
        }).subscribe(new Observer<String>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.e("onSubscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                if(TextUtils.equals(s, "你是瓜皮")) {
                    mDisposable.dispose();
                }
                Logger.e(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.e(e, "onError");
            }

            @Override
            public void onComplete() {
                Logger.e("onComplete");
            }
        });

    }
}
