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
import zwz.im.androidapp.adapter.ConnectRecyclerViewPageReplyAdapter;
import zwz.im.androidapp.adapter.EndlessRecyclerOnScrollListener;
import zwz.im.androidapp.model.Fruit;
import zwz.im.androidapp.utils.Constants;

public class ConnectViewPageReplyFragment extends ConnectViewPageBaseFragment {

//    private onRecyclerItemClick mListener;

    @BindView(R.id.recycle_connect)
    RecyclerView mDownRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.text_no_data)
    TextView text_no_data;

    private ConnectRecyclerViewPageReplyAdapter replyAdapter;

    private List<Fruit> dataList = new ArrayList<Fruit>();


    private String fragmentTitle = "我回复";

    public ConnectViewPageReplyFragment(){
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

        replyAdapter = new ConnectRecyclerViewPageReplyAdapter(getActivity(),dataList);
//        adapter.setOnRecyclerItemClick(new ConnectRecyclerViewPageAdapter.onRecyclerItemClick() {
//            @Override
//            public void onItemClick(View view, int position) {
////                mListener.onRecyclerItemClick(position);
//                Log.d("Test", "onItemClick: " + position);
//            }
//        });
        mDownRecyclerView.setAdapter(replyAdapter);
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
                replyAdapter.setLoadState(replyAdapter.LOADING);

                // 显示加载到底的提示
                replyAdapter.setLoadState(replyAdapter.LOADING_COMPLETE);

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
        replyAdapter.notifyDataSetChanged();

        if(dataList.size() == 0){
            text_no_data.setVisibility(View.VISIBLE);
        }else{
            text_no_data.setVisibility(View.GONE);
        }

        // 显示加载到底的提示
        replyAdapter.setLoadState(replyAdapter.LOADING_COMPLETE);
    }

    private void initData() {

        if(null == dataList){
            dataList = new ArrayList<>();
        }
        dataList.clear();

        for(int i=0; i < 3; i++){

            List<Fruit> childList = new ArrayList<>();

            Fruit orange = new Fruit("心情不好，和我好好聊聊吧，我也遇到过这种事情，感觉他很讨厌，每天都不好好工作，把办公室搞的乌烟瘴气，真不知道，这种人我该如何和他相处。",7, "connect",null);
            childList.add(orange);
            Fruit pineapple = new Fruit("家庭", 7, "connect",null);
            childList.add(pineapple);
            Fruit strawberry = new Fruit("最近遇到很多烦心事，下面我和大家说说吧", 7, "connect",null);
            childList.add(strawberry);
            Fruit cherry = new Fruit("最长六字分类", 7, "connect",null);
            childList.add(cherry);
            Fruit mango = new Fruit("分享", 7, "connect",null);
            childList.add(mango);

            Fruit apple = new Fruit("不要把事情都压在心里，把不开心大声的说出来，压在心里会越来越不开心。", 5, "connect",childList);
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