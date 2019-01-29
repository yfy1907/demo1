package zwz.im.androidapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zwz.im.androidapp.R;
import zwz.im.androidapp.adapter.RecyclerAdapter;
import zwz.im.androidapp.model.Fruit;


public class ConnectFragment extends BaseFragmentHome {

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoaded;

    //private RecyclerView recyler_view;
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private List<Fruit> myReplyList = new ArrayList<Fruit>();

    private TabHost tabhost;
    private RecyclerView recyler_view1;
    private RecyclerView recyler_view2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("michael-onCreateView", "连线");
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            mView = inflater.inflate(R.layout.connect, container, false);
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
//        recyler_view = find(R.id.recycler_view);
//        recyler_view.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyler_view.setAdapter(new RecyclerAdapter(getContext(), fruitList));

        this.tabhost = find(R.id.mytab);
        this.tabhost.setup();

        recyler_view1 = find(R.id.recycler_view1);
        recyler_view1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyler_view1.setAdapter(new RecyclerAdapter(getContext(), fruitList));

        recyler_view2 = find(R.id.recycler_view2);
        recyler_view2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyler_view2.setAdapter(new RecyclerAdapter(getContext(), myReplyList));


        View viewTopic = getLayoutInflater().inflate(R.layout.connect_tabwidget_style,null);
        TextView textviewTopic = (TextView) viewTopic.findViewById(R.id.textview_tabname);
        textviewTopic.setText("我发布");
        View viewReply = getLayoutInflater().inflate(R.layout.connect_tabwidget_style,null);
        TextView textviewReply = (TextView) viewReply.findViewById(R.id.textview_tabname);
        textviewReply.setText("我回复");

        this.tabhost.addTab(this.tabhost.newTabSpec("mytopic").setIndicator(viewTopic).setContent(R.id.recycler_view1));
        this.tabhost.addTab(this.tabhost.newTabSpec("myreply").setIndicator(viewReply).setContent(R.id.recycler_view2));
        this.tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabKey) {
                tabhost.setCurrentTabByTag(tabKey);
                if (tabKey.equals("mytopic")) {

                } else {

                }

            }
        });

    }

    private void initData() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("我发布的心声内容，最近心情很不好，我也不知道为什么,就是觉得每天过的不开心，学习很累，效果也不好，家里还批评我，心里真的很不舒服。", 5, "connect");
            fruitList.add(apple);
            Fruit watermelon = new Fruit("分享", 6, "connect");
            fruitList.add(watermelon);
            Fruit pear = new Fruit("家庭", 6, "connect");
            fruitList.add(pear);
            Fruit strawberry = new Fruit("最近遇到很多烦心事，下面我和大家说说吧", 6, "connect");
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("最长六字分类", 6, "connect");
            fruitList.add(cherry);
            Fruit mango = new Fruit("分享", 6, "connect");
            fruitList.add(mango);


            Fruit apple1 = new Fruit("我发布的心声内容，最近心情很不好，我也不知道为什么,就是觉得每天过的不开心，学习很累，效果也不好，家里还批评我，心里真的很不舒服。", 5, "connect");
            fruitList.add(apple1);
            Fruit watermelon1 = new Fruit("分享", 6, "connect");
            fruitList.add(watermelon1);
            Fruit pear1 = new Fruit("家庭", 6, "connect");
            fruitList.add(pear1);
            Fruit strawberry1 = new Fruit("最近遇到很多烦心事，下面我和大家说说吧", 6, "connect");
            fruitList.add(strawberry1);
            Fruit cherry1 = new Fruit("最长六字分类", 6, "connect");
            fruitList.add(cherry1);
            Fruit mango1 = new Fruit("分享", 6, "connect");
            fruitList.add(mango1);
        }

        for(int i=0; i < 2; i++){
            Fruit banana = new Fruit("不要把事情都压在心里，把不开心大声的说出来，压在心里会越来越不开心。", 7, "connect");
            myReplyList.add(banana);
            Fruit orange = new Fruit("心情不好，和我好好聊聊吧，我也遇到过这种事情，感觉他很讨厌，每天都不好好工作，把办公室搞的乌烟瘴气，真不知道，这种人我该如何和他相处。",7, "connect");
            myReplyList.add(orange);
            Fruit pineapple = new Fruit("家庭", 7, "connect");
            myReplyList.add(pineapple);
            Fruit pineapple1 = new Fruit("家庭", 7, "connect");
            myReplyList.add(pineapple1);
            Fruit pineapple2 = new Fruit("家庭", 7, "connect");
            myReplyList.add(pineapple2);
            Fruit pineapple3 = new Fruit("家庭", 7, "connect");
            myReplyList.add(pineapple3);
            Fruit pineapple4 = new Fruit("家庭", 7, "connect");
            myReplyList.add(pineapple4);
            Fruit pineapple5 = new Fruit("家庭", 7, "connect");
            myReplyList.add(pineapple5);
            Fruit pineapple6 = new Fruit("家庭", 7, "connect");
            myReplyList.add(pineapple6);

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
