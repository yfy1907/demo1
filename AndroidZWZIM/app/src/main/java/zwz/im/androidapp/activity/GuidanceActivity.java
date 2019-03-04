package zwz.im.androidapp.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.io.File;

import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;

/**
 * 启动页 视频播放
 */
public class GuidanceActivity extends BaseActivity {

    private VideoView videoView;
    String videoPath = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        setTranslucentStatus(this,R.color.heise);// 设置导航栏透明
        videoView = (VideoView) this.findViewById(R.id.id_video);
        videoPath = getIntent().getStringExtra("VideoPath");
        Log.e("","## 加载视频路径："+videoPath);
        loadData();
    }

    protected void loadData() {
        File file = new File(videoPath);
        if (!file.exists()){
            Log.e("", "视频文件不存在");
            goLaunchActivity();
        }else {
            videoView.setVideoPath(file.getPath());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    //设置为填充父窗体
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            //设置布局
            videoView.setLayoutParams(layoutParams);
            //循环播放
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // mp.setLooping(true);
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    goLaunchActivity();
                }
            });
            videoView.start();
        }
    }

    private void goLaunchActivity(){
        Intent intent=new Intent(GuidanceActivity.this, LaunchActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null){
            //释放掉占用的内存
            videoView.suspend();
        }
    }
}
