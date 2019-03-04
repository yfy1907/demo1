package zwz.im.androidapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;

import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;
import zwz.im.androidapp.fragment.BaseFragmentHome;
import zwz.im.androidapp.fragment.FragmentFactory;
import zwz.im.androidapp.widget.view.NoScrollViewPager;

public class NewVocalCordsActivity extends BaseActivity {

    private MyAdapter mAdapter;
    private NoScrollViewPager mViewPager;
    private RadioGroup rgGroup;
    private ImageView iv_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_vocal_cords);
        setTranslucentStatus(this,R.color.heise); // 顶部状态栏透明
//        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary), true);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Log.i("michael-onCreate", "主Activity");

        int currentItem = getIntent().getIntExtra("default_select_item",3);

        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_content);
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        iv_rg = (ImageView) findViewById(R.id.iv_rg);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(currentItem, false);

        // 底栏标签切换监听
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_talk:
                        mViewPager.setCurrentItem(0, false);// 倾诉 参2:表示是否具有滑动动画
                        break;
                    case R.id.rb_voice:
                        mViewPager.setCurrentItem(1, false);   // 心声带
                        break;
                    case R.id.rb_heart:
                        mViewPager.setCurrentItem(2, false);   // 心室
                        break;
                    case R.id.rb_connect:
                        mViewPager.setCurrentItem(3, false);   // 连线
                        break;
                    case R.id.rb_mine:
                        mViewPager.setCurrentItem(4, false);   // 我
                        break;

                    default:
                        break;
                }
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                BaseFragmentHome fragment = FragmentFactory.createFragment(position);
                // 开始加载数据
                //fragment.loadData();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    // FragmentPagerAdapter是PagerAdapter的子类, 如果viewpager的页面是fragment的话,就继承此类
    class MyAdapter extends FragmentPagerAdapter {

        private String[] mTabNames = {"倾诉", "心声带", "心室", "连线", "我"};


        public MyAdapter(FragmentManager fm) {
            super(fm);
            //mTabNames = UIUtils.getStringArray(R.array.tab_names);// 加载页面标题数组
        }

        // 返回页签标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        // 返回当前页面位置的fragment对象
        @Override
        public Fragment getItem(int position) {
            BaseFragmentHome fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

    }
}
