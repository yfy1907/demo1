package zwz.im.androidapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.SettingActivity;
import zwz.im.androidapp.adapter.RecyclerAdapter;
import zwz.im.androidapp.model.Fruit;
import zwz.im.androidapp.widget.view.CircleProgressBar;


public class MineFragment_bak extends BaseFragmentHome implements View.OnClickListener {

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoaded;

    private RecyclerView recyler_view;
    private int mTotalProgress = 45;
    private int mCurrentProgress = 0;
    private ImageView iv_setting;
    private CircleProgressBar cpb_one, cpb_two;
    private List<Fruit> fruitList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("michael-onCreateView", "我");
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            mView = inflater.inflate(R.layout.mine, container, false);
            initData();
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
        cpb_one = find(R.id.cpb_one);
        cpb_two = find(R.id.cpb_two);
        iv_setting = find(R.id.iv_setting);
        iv_setting.setOnClickListener(this);

        recyler_view = find(R.id.recycler_view);
        recyler_view.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyler_view.setAdapter(new RecyclerAdapter(getContext(), fruitList));

        new Thread(new ProgressRunable()).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    class ProgressRunable implements Runnable {
        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress) {
                mCurrentProgress += 1;
                cpb_one.setProgress(mCurrentProgress);
                cpb_two.setProgress(mCurrentProgress);
                try {
                    Thread.sleep(25);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initData() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("最近遇到很多烦心事，下面我和大家说说吧", 0, "mine",null);
            fruitList.add(apple);
            Fruit banana = new Fruit("最长六字分类", 3, "mine",null);
            fruitList.add(banana);
            Fruit orange = new Fruit("最近遇到很多烦心事，下面我和大家说说吧",0, "mine",null);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("分享", 1, "mine",null);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("家庭", 2, "mine",null);
            fruitList.add(pear);
            Fruit grape = new Fruit("最长六字分类", 3, "mine",null);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("家庭", 2, "mine",null);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("最近遇到很多烦心事，下面我和大家说说吧", 0, "mine",null);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("最长六字分类", 3, "mine",null);
            fruitList.add(cherry);
            Fruit mango = new Fruit("分享", 1, "mine",null);
            fruitList.add(mango);
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
