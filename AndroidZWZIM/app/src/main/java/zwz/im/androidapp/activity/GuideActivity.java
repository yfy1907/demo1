package zwz.im.androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import zwz.im.androidapp.MainActivity;
import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;
import zwz.im.androidapp.adapter.ViewPagerAdapter;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    //点随着view的改变而改变，需要实现改变的监听方法，重写ViewPager.OnPageChangeListener

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private List<View> views;//声明view集合
    private ImageView[] dots;//声明图像点的集合
    private int[] ids={R.id.iv1,R.id.iv2};//获取点id的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        initDots();
    }

    private void initViews() {//初始化view
        LayoutInflater inflater=LayoutInflater.from(this);
        views=new ArrayList<>();
        views.add(inflater.inflate(R.layout.view_one,null));
        views.add(inflater.inflate(R.layout.view_two,null));

        adapter = new ViewPagerAdapter(views,this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);//viewPager实现监听
        //第三个页面获取id
        views.get(1).findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main2=new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent_main2);
            }
        });
    }
    private void initDots(){  //初始化点
        dots=new ImageView[views.size()];//实例化对象
        for (int i=0;i<views.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);//获取id
        }
    }

    //页面被选中时调用
    @Override
    public void onPageSelected(int i) {
        for (int j=0;j<ids.length;j++){
            if (i==j){
                dots[j].setImageResource(R.drawable.login_point_selected);
            }else {
                dots[j].setImageResource(R.drawable.login_point);
            }
        }
    }

    //页面滑动时调用
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
    //页面滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int i) {

    }


}
