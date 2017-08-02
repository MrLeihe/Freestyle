package com.sd.style.ui;

import android.widget.Button;

import com.sd.style.R;
import com.sd.style.common.api.ApiRetrofit;
import com.sd.style.common.base.BaseActivity;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.common.http.RequestFactory;
import com.sd.style.ui.User.response.LoginResponse;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

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
    public void onClick() {
        subscribe();
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    private void subscribe() {
        Map<String, String> map = new HashMap<>();

        Retrofit retrofit = RequestFactory.create();
        ApiRetrofit apiRetrofit = retrofit.create(ApiRetrofit.class);
        apiRetrofit.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast("登录失败");
                    }

                    @Override
                    public void onComplete() {
                        showToast("登陆成功");
                    }
                });
    }
}
