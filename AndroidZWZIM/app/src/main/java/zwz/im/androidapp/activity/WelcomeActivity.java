package zwz.im.androidapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import zwz.im.androidapp.LoginActivity;
import zwz.im.androidapp.MainActivity;
import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    private boolean isFirstIn=false;//判定是否是第一次打开
    private static final int TIME=2000;//声明延时的时间
    private static final int GO_HOME=1000;//跳转到Main2Activity的时间
    private static final int GO_GUIDE=1001;//跳转到MainActivity的时间

    //线程分为主线程(主线程又叫UI线程，只能有一个主线程）和子线程（可以有多个）Handler只能在主线程里运行
    //handler是Android给我们提供用来更新UI的一套机制，也是一套消息处理机制，我们可以发消息，也可以通过它处理消息。
    //使用Handler的方式进行延时操作
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTranslucentStatus(this,R.color.heise); // 顶部状态栏透明
        init();
    }
    private void init(){
        //获取SharedPreferenced对象,用以存储isFirstIn的当前值
        SharedPreferences preferences=getSharedPreferences("jike",MODE_PRIVATE);
        isFirstIn=preferences.getBoolean("isFirstIn1",true);//将isFirstIn获取为true
        if (!isFirstIn){
            handler.sendEmptyMessageDelayed(GO_HOME,TIME);//发送消息
        }else {
            handler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            SharedPreferences.Editor editor=preferences.edit();//对存储数据进行编辑
            editor.putBoolean("isFirstIn1",false);//将isFirstIn存储为false
            editor.commit();//编辑提交
        }
    }

    private void goHome(){
//        Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
        Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();//关掉不用的界面
    }
    private  void goGuide(){

        initMedia();


//        Intent intent=new Intent(WelcomeActivity.this, LaunchActivity.class);
//      Intent intent=new Intent(WelcomeActivity.this, GuideActivity.class);
//      Intent intent=new Intent(WelcomeActivity.this, GuidanceActivity.class);
//        startActivity(intent);
//        finish();//关掉不用的界面
    }

    String APP_PATH;
    String VIDEO_NAME = "welcome_video.mp4";
    private void initMedia(){
        APP_PATH = getApplicationContext().getFilesDir().getAbsolutePath();

        Log.e("","##------APP_PATH=="+APP_PATH);

        //初始化welcome_media.mp4文件。如果内存卡存在则直接播放，如果不存在则从资源文件中读取写入内存卡
        if (!new File(APP_PATH + VIDEO_NAME).exists()){
            try {
                //输入流
                InputStream in = getApplicationContext().getResources().openRawResource(R.raw.welcome_video);
                //输出流
                OutputStream out = new FileOutputStream(APP_PATH + VIDEO_NAME);
                //将资源文件welcome_media.mp3写入到sd卡
                byte[] buffer = new byte[1024];
                int length;

                while ((length = in.read(buffer)) > 0){
                    out.write(buffer, 0, length);
                }
                out.flush();       //刷新
                out.close();        //关闭
                in.close();

                // Log.i("SplashActivity", "mp4写入成功");

            } catch (Exception  e) {
                e.printStackTrace();
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                File file = new File(APP_PATH + VIDEO_NAME);
                if (file.exists()){
                    //如果视频写入成功，则打开引导页面
                    Intent intent = new Intent(WelcomeActivity.this, GuidanceActivity.class);
                    intent.putExtra("VideoPath", APP_PATH + VIDEO_NAME);
                    startActivity(intent);
                }else {
                    //如果视频写入不成功，则跳过引导页，直接打开程序主界面
                    Intent intent = new Intent(WelcomeActivity.this, LaunchActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1000);
    }



}
