package zwz.im.androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import zwz.im.androidapp.LoginActivity;
import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;
import zwz.im.androidapp.adapter.GuidePageAdapter;

public class LaunchActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合

    //最后一页的按钮
    private Button ib_start;
    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ib_start = (Button)findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                finish();
            }
        });
        //加载ViewPager
        initViewPager();
    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.drawable.launch_one, R.drawable.launch_two};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        viewPager.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        viewPager.setOnPageChangeListener(this);
    }


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
        if (position == imageIdArray.length - 1) {
            ib_start.setVisibility(View.VISIBLE);
        } else {
            ib_start.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}