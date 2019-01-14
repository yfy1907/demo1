package zwz.im.androidapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

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
        setTranslucentStatus();
        init();
    }
    private void init(){
        //获取SharedPreferenced对象,用以存储isFirstIn的当前值
        SharedPreferences preferences=getSharedPreferences("jike",MODE_PRIVATE);
        isFirstIn=preferences.getBoolean("isFirstIn",true);//将isFirstIn获取为true
        if (!isFirstIn){
            handler.sendEmptyMessageDelayed(GO_HOME,TIME);//发送消息
        }else {
            handler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            SharedPreferences.Editor editor=preferences.edit();//对存储数据进行编辑
            editor.putBoolean("isFirstIn",false);//将isFirstIn存储为false
            editor.commit();//编辑提交
        }
    }

    private void goHome(){
        Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();//关掉不用的界面
    }
    private  void goGuide(){
        Intent intent=new Intent(WelcomeActivity.this, LaunchActivity.class);
//        Intent intent=new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();//关掉不用的界面
    }

}
