package zwz.im.androidapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDecoder;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import zwz.im.androidapp.LoginActivity;
import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;
import zwz.im.androidapp.activity.skin.SkinActivity;
import zwz.im.androidapp.adapter.GuidePageAdapter;

public class LaunchActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private GifImageView[] gifImageArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合

    //最后一页的按钮
    private Button ib_start;
    private LinearLayout.LayoutParams layoutParams;

    // 第二个图片播放完成时
    GifDrawable gifDrawable;
    View view2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        setTranslucentStatus(this,R.color.heise);

        ib_start = (Button)findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
//                startActivity(new Intent(LaunchActivity.this, SkinActivity.class));
                finish();
            }
        });
        //加载ViewPager
        initViewPager();
    }

    /**
     * 加载图片ViewPager
     */
    @SuppressLint("ResourceType")
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        gifImageArray = new GifImageView[]{ (GifImageView) findViewById(R.drawable.launch_gif_one) , (GifImageView) findViewById(R.drawable.launch_gif_two) };
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        // int len = gifImageArray.length;
        LayoutInflater inflater=LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.view_gif_image1, null);
        view2 = inflater.inflate(R.layout.view_gif_image2, null);

        //这里控制播放的对象实际是gifDrawable
        viewList.add(view1);
        viewList.add(view2);

        //View集合初始化好后，设置Adapter
        viewPager.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        viewPager.setOnPageChangeListener(this);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                gifDrawable.stop();
                ib_start.setVisibility(View.VISIBLE);
            }
        }
    };


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        //判断是否是最后一页，若是则显示按钮
        if (position == gifImageArray.length - 1) {
            GifImageView gifImageView2 = view2.findViewById(R.id.gif_image);
            // gifImageView2.setBackgroundResource(R.drawable.launch_gif_two);
            gifImageView2.setImageResource(R.drawable.launch_gif_two);
            gifDrawable = (GifDrawable) gifImageView2.getDrawable();
            //资源对象
            gifDrawable.start();
            gifDrawable.setLoopCount(1);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.obtainMessage(1, "ok").sendToTarget();
                }
            }, gifDrawable.getDuration());

        } else {
            ib_start.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}