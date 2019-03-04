package zwz.im.androidapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import zwz.im.androidapp.R;
import zwz.im.androidapp.model.Fruit;

public class ConnectRecyclerViewPageReplyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final String TAG = "ConnectViewPageAdapter";
    // 普通布局
    private final int TYPE_ITEM = 1;
    // 脚布局
    private final int TYPE_FOOTER = 2;
    // 当前加载状态，默认为加载完成
    private int loadState = 2;
    // 正在加载
    public final int LOADING = 1;
    // 加载完成
    public final int LOADING_COMPLETE = 2;
    // 加载到底
    public final int LOADING_END = 3;

    private Context mContext;
//    private onRecyclerItemClick mOnRecyclerItemClick;

    private List<Fruit> dataList;

    public ConnectRecyclerViewPageReplyAdapter(Context context, List<Fruit> pDataList) {
        mContext = context;
        dataList = pDataList;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为FooterView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 通过判断显示类型，来创建不同的View
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_connect_recycler_item_topic, parent, false);
            RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("WrongConstant")
//                @Override
//                public void onClick(View view) {
////                    Toast.makeText(activity, view.getTag() + "", 1000).show();
//                    if(null != mClickListener){
//                        mClickListener.onItemClick(view);
//                    }
//                }
//            });
            return viewHolder;

        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_refresh_footer, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof RecyclerViewHolder){

            Fruit fruit= dataList.get(position);
            final RecyclerViewHolder holder = (RecyclerViewHolder) viewHolder;
            holder.textview_title.setText(fruit.getName());

            holder.layout_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ViewStub初始状态时不显示时，加载

                    Log.e(TAG," holder.viewStub=="+holder.viewStub.getVisibility());

                    if(holder.viewStub.getVisibility() == View.GONE){
                        if(holder.viewStub instanceof ViewStub){
                            holder.viewStub = ((ViewStub)holder.viewStub).inflate();
                            holder.viewStub.setVisibility(View.VISIBLE);

                            holder.childListView = (ChildListView) holder.viewStub.findViewById(R.id.connect_child_listview);
                            ConnectReplyListViewAdapter replyListViewAdapter = new ConnectReplyListViewAdapter(dataList,mContext);
                            holder.childListView.setAdapter(replyListViewAdapter);
                        }else{
                            holder.viewStub.setVisibility(View.VISIBLE);
                        }
                    }else {
                        holder.viewStub.setVisibility(View.GONE);
                    }

                }
            });


//            if (mOnRecyclerItemClick != null){
//                if(!viewHolder.itemView.hasOnClickListeners()){
//                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            int pos = viewHolder.getPosition();
//                            mOnRecyclerItemClick.onItemClick(v,pos);
//                        }
//                    });
//
//                }
//            }
        }else if (viewHolder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) viewHolder;
            switch (loadState) {
                case LOADING: // 正在加载
                    footViewHolder.pbLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_COMPLETE: // 加载完成
                    footViewHolder.pbLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_END: // 加载到底
                    footViewHolder.pbLoading.setVisibility(View.GONE);
                    footViewHolder.tvLoading.setVisibility(View.GONE);
                    footViewHolder.llEnd.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if(null != dataList){
            return dataList.size() ;
        }else{
            return 0;
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout layout_title;
        public TextView textview_title;
        public View viewStub;
        public ChildListView childListView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            layout_title = itemView.findViewById(R.id.layout_title);
            textview_title = itemView.findViewById(R.id.connect_textview_item_title_topic);
            viewStub = itemView.findViewById(R.id.viewstub_listview);
        }
    }

//    public interface onRecyclerItemClick {
//        void onItemClick(View view, int position);
//    }
//    public void setOnRecyclerItemClick(onRecyclerItemClick mOnRecyclerItemClick) {
//        this.mOnRecyclerItemClick = mOnRecyclerItemClick;
//    }


    /**
     * 底部刷新
     */
    private class FootViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pbLoading;
        TextView tvLoading;
        LinearLayout llEnd;

        FootViewHolder(View itemView) {
            super(itemView);
            pbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
            llEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
        }
    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }


}