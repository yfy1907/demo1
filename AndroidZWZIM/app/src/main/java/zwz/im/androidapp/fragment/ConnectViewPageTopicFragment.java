package zwz.im.androidapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zwz.im.androidapp.R;
import zwz.im.androidapp.adapter.ConnectRecyclerViewPageAdapter;
import zwz.im.androidapp.adapter.ConnectRecyclerViewPageTopicAdapter;
import zwz.im.androidapp.adapter.EndlessRecyclerOnScrollListener;
import zwz.im.androidapp.model.Fruit;
import zwz.im.androidapp.utils.Constants;

public class ConnectViewPageTopicFragment extends ConnectViewPageBaseFragment {

//    private onRecyclerItemClick mListener;

    @BindView(R.id.recycle_connect)
    RecyclerView mDownRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.text_no_data)
    TextView text_no_data;

    private ConnectRecyclerViewPageTopicAdapter topicAdapter;

    private List<Fruit> dataList = new ArrayList<Fruit>();


    private String fragmentTitle = "我发布";

    public ConnectViewPageTopicFragment(){
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect_recycler_view, null);

        Bundle bundle = getArguments();
        //这里就拿到了之前传递的参数
        fragmentTitle = bundle.getString("fragmentTitle");

        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {

        topicAdapter = new ConnectRecyclerViewPageTopicAdapter(getActivity(),dataList);
//        adapter.setOnRecyclerItemClick(new ConnectRecyclerViewPageAdapter.onRecyclerItemClick() {
//            @Override
//            public void onItemClick(View view, int position) {
////                mListener.onRecyclerItemClick(position);
//                Log.d("Test", "onItemClick: " + position);
//            }
//        });
        mDownRecyclerView.setAdapter(topicAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDownRecyclerView.setLayoutManager(layoutManager);



        // 设置刷新控件颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FFFFCD75"));

        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新数据
                requestLoadData("");
                // 延时1s关闭下拉刷新
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 500);
            }
        });

        // 设置加载更多监听
        mDownRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                topicAdapter.setLoadState(topicAdapter.LOADING);

                // 显示加载到底的提示
                topicAdapter.setLoadState(topicAdapter.LOADING_COMPLETE);

//                if (listdata.size() < 52) {
//                    searchAndShow(StaticMember.USER.getUid());
//                    loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING_COMPLETE);
//                } else {
//                    // 显示加载到底的提示
//                    loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING_END);
//                }
            }
        });

        requestLoadData("");

    }

    public void requestLoadData(final String uid) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == Constants.request_my_topic_list_data) {

                    Log.e("查到的主题", dataList.size() + "个");
                    refreshRecyclerList();

                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                // listdata = HttpTools.getJson(Constants.URL + "mob_declare.php", "uid=" + uid, Constants.BOARD_LIST);
                initData();
                handler.sendEmptyMessage(Constants.request_my_topic_list_data_request);
            }
        }).start();
    }

    private void refreshRecyclerList(){
        topicAdapter.notifyDataSetChanged();

        if(dataList.size() == 0){
            text_no_data.setVisibility(View.VISIBLE);
        }else{
            text_no_data.setVisibility(View.GONE);
        }

        // 显示加载到底的提示
        topicAdapter.setLoadState(topicAdapter.LOADING_COMPLETE);
    }

    private void initData() {

        if(null == dataList){
            dataList = new ArrayList<>();
        }
        dataList.clear();

        for (int i = 0; i < 10; i++) {

            List<Fruit> childList = new ArrayList<>();
            Fruit watermelon = new Fruit("分享", 6, "connect",null);
            childList.add(watermelon);
            Fruit pear = new Fruit("家庭", 6, "connect",null);
            childList.add(pear);
            Fruit strawberry = new Fruit("最近遇到很多烦心事，下面我和大家说说吧", 6, "connect",null);
            childList.add(strawberry);
            Fruit cherry = new Fruit("最长六字分类", 6, "connect",null);
            childList.add(cherry);
            Fruit mango = new Fruit("分享", 6, "connect",null);
            childList.add(mango);

            Fruit apple = new Fruit("我发布的心声内容，最近心情很不好，我也不知道为什么,就是觉得每天过的不开心，学习很累，效果也不好，家里还批评我，心里真的很不舒服。", 5, "connect",childList);
            dataList.add(apple);

        }

    }


    @Override
    public String getFragmentTitle() {
        return fragmentTitle;
    }

//    public interface onRecyclerItemClick{
//        public void onRecyclerItemClick(int message);
//    }
//    public void onAttach(Activity activity){
//        super.onAttach(activity);
//        try {
//            mListener = (onRecyclerItemClick) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + "must implement OnGridViewSelectedListener");
//        }
//    }

}