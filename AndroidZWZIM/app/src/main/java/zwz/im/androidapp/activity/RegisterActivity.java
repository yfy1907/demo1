package zwz.im.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zwz.im.androidapp.MainActivity;
import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.btn_join)
    Button btn_join;
    @BindView(R.id.verification_code)
    Button verification_code;
    @BindView(R.id.et_tel)
    EditText et_tel;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_nickname)
    EditText et_nickname;
    @BindView(R.id.et_receive_code)
    EditText et_receive_code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTranslucentStatus(this,R.color.heise); // 顶部状态栏透明
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {

        if ("join".equals(getIntent().getStringExtra("type"))) {
            et_nickname.setVisibility(View.VISIBLE);
        } else {
            et_pwd.setHint("请设置您的新密码");
            btn_join.setText("确 认 更 改");
        }

        iv_avatar.setOnClickListener(this);
        btn_join.setOnClickListener(this);
        verification_code.setOnClickListener(this);
        et_tel.setOnClickListener(this);
        et_pwd.setOnClickListener(this);
        et_nickname.setOnClickListener(this);
        et_receive_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:

                break;

            case R.id.et_nickname:

                break;

            case R.id.et_receive_code:

                break;

            case R.id.btn_join:
//                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                startActivity(new Intent(RegisterActivity.this, NewVocalCordsActivity.class));
                break;

            case R.id.verification_code:

                break;

            case R.id.et_tel:

                break;

            case R.id.et_pwd:

                break;
        }
    }
}

