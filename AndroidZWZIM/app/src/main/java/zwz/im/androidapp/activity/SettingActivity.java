package zwz.im.androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zwz.im.androidapp.LoginActivity;
import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;


public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.receive_news)
    Switch receive_news;
    @BindView(R.id.receive_voice_video)
    Switch receive_voice_video;
    @BindView(R.id.sound)
    Switch sound;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.update_pwd)
    TextView update_pwd;
    @BindView(R.id.font_size)
    TextView font_size;
    @BindView(R.id.chat_bg)
    TextView chat_bg;
    @BindView(R.id.skin)
    TextView skin;
    @BindView(R.id.about_us)
    TextView about_us;
    @BindView(R.id.upgrade)
    TextView upgrade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setTranslucentStatus(this,R.color.heise); // 顶部状态栏透明

        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {

        title.setText("设置");


        receive_news.setOnClickListener(this);
        receive_voice_video.setOnClickListener(this);
        sound.setOnClickListener(this);
        logout.setOnClickListener(this);
        update_pwd.setOnClickListener(this);
        font_size.setOnClickListener(this);
        chat_bg.setOnClickListener(this);
        skin.setOnClickListener(this);
        about_us.setOnClickListener(this);
        upgrade.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.receive_news:

                break;

            case R.id.receive_voice_video:

                break;

            case R.id.sound:

                break;

            case R.id.logout:
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                break;

            case R.id.update_pwd:
                startActivity(new Intent(SettingActivity.this, RegisterActivity.class));
                break;

            case R.id.font_size:

                break;

            case R.id.chat_bg:

                break;

            case R.id.skin:

                break;

            case R.id.about_us:

                break;

            case R.id.upgrade:

                break;
        }
    }
}
