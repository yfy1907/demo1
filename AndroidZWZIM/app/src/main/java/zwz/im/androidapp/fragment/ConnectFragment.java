package zwz.im.androidapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zwz.im.androidapp.R;
import zwz.im.androidapp.adapter.RecyclerAdapter;
import zwz.im.androidapp.model.Fruit;
import zwz.im.androidapp.utils.ViewFindUtils;


public class ConnectFragment extends BaseFragmentHome {

    private static final String TAG = "ConnectFragment";
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoaded;

    private String[] mTitles = {"我发布", "我回复"};
    private SegmentTabLayout mTabLayout_1;

    private ArrayList<ConnectViewPageBaseFragment> mFragments = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("michael-onCreateView", "连线");
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            mView = inflater.inflate(R.layout.connect, container, false);

            initView();
            isPrepared = true;
            // 实现懒加载
            lazyLoad();
        }
        //缓存的mView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }

    /**
     * 初始化控件
     */
    private void initView() {
//        for (String title : mTitles) {
//            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
//        }
        ConnectViewPageTopicFragment topicFragment = new ConnectViewPageTopicFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("fragmentTitle", mTitles[0]);
        topicFragment.setArguments(bundle1);

        ConnectViewPageReplyFragment replyFragment = new ConnectViewPageReplyFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("fragmentTitle", mTitles[1]);
        replyFragment.setArguments(bundle2);

        mFragments.add(topicFragment);
        mFragments.add(replyFragment);

        mTabLayout_1 = find(R.id.tablayout_connect);

        initViewpageControl();

        //设置未读消息红点
        mTabLayout_1.showDot(2);
        MsgView rtv_3_2 = mTabLayout_1.getMsgView(1);
        if (rtv_3_2 != null) {
            rtv_3_2.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }



    }


    private void initViewpageControl() {
        final ViewPager viewpageConnect = find(R.id.viewpage_connect);

        viewpageConnect.setAdapter(new MyPagerAdapter(getFragmentManager(), mFragments));

        mTabLayout_1.setTabData(mTitles);
        mTabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.e(TAG,"当前选中Layout："+position);
                viewpageConnect.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewpageConnect.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG,"当前选中 setCurrentTab ："+position);
                mTabLayout_1.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpageConnect.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<ConnectViewPageBaseFragment> childFragments;

        public MyPagerAdapter(FragmentManager fm, List<ConnectViewPageBaseFragment> mChildFragments) {
            super(fm);
            this.childFragments = mChildFragments;
        }

        @Override
        public int getCount() {
            int ret = 0;
            if(childFragments != null){
                ret = childFragments.size();
            }
            return ret;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.childFragments.get(position).getFragmentTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return this.childFragments.get(position);
        }
    }

    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoaded) {
            return;
        }
        //填充各控件的数据
        mHasLoaded = true;
    }
}
