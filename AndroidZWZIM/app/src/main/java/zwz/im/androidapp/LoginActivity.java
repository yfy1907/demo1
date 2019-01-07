package zwz.im.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zwz.im.androidapp.activity.RegisterActivity;
import zwz.im.androidapp.activity.SettingActivity;
import zwz.im.androidapp.activity.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_join_us)
    TextView tv_join_us;
    @BindView(R.id.tv_forget_pwd)
    TextView tv_forget_pwd;
    @BindView(R.id.btn_see)
    Button btn_see;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.et_tel)
    EditText et_tel;
    @BindView(R.id.et_pwd)
    EditText et_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {

        iv_avatar.setOnClickListener(this);
        tv_join_us.setOnClickListener(this);
        tv_forget_pwd.setOnClickListener(this);
        btn_see.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        et_tel.setOnClickListener(this);
        et_pwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:

                break;

            case R.id.tv_join_us:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class).putExtra("type", "join"));
                break;

            case R.id.tv_forget_pwd:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class).putExtra("type", "forget"));
                break;

            case R.id.btn_see:
                startActivity(new Intent(LoginActivity.this, SettingActivity.class));
                break;

            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;

            case R.id.et_tel:

                break;

            case R.id.et_pwd:

                break;
        }
    }
}