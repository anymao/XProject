package com.anymore.client.mvp.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.anymore.client.R;
import com.anymore.mvpkit.mvp.base.BaseMvpActivity;
import org.jetbrains.annotations.Nullable;

/**
 * Created by liuyuanmao on 2019/7/16.
 */
public class LoginActivity extends BaseMvpActivity<LoginContract.IPresenter> implements LoginContract.IView {

    private EditText etUsername,etPassword;
    private Button btnLogin;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            mPresenter.mockLogin(username,password);
        });
    }

    @Override
    public void loginSuccess() {
        showSuccess("登陆成功,hhhhh");
    }
}
