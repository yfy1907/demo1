package zwz.im.androidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zwz.im.androidapp.R;
import zwz.im.androidapp.model.Fruit;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * RecycleViewAdapter 要素点：
     * <p/>
     * 1，ViewHolder必须继承RecyclerView.ViewHolder
     * 2，RecycleView.Adapter的泛型为自定义ViewHolder
     */

    private List<Fruit> mData;
    private Context context;
    private Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

    public enum Item_Type {
        RECYCLEVIEW_ITEM_TYPE_1,
        RECYCLEVIEW_ITEM_TYPE_2,
        RECYCLEVIEW_ITEM_TYPE_3,
        RECYCLEVIEW_ITEM_TYPE_4,
        RECYCLEVIEW_ITEM_TYPE_5,
        RECYCLEVIEW_ITEM_TYPE_6,
        RECYCLEVIEW_ITEM_TYPE_7,
        RECYCLEVIEW_ITEM_TYPE_8
    }

    public RecyclerAdapter(Context context, List<Fruit> mData) {
        this.mData = mData;
        this.context = context;
    }


    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType :不同ItemView的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_1.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_a, parent, false);
            ViewHolderA viewHolder = new ViewHolderA(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_2.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_b, parent, false);
            ViewHolderB viewHolder = new ViewHolderB(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_3.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_c, parent, false);
            ViewHolderC viewHolder = new ViewHolderC(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_4.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_d, parent, false);
            ViewHolderD viewHolder = new ViewHolderD(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_5.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_e, parent, false);
            ViewHolderE viewHolder = new ViewHolderE(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_6.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_f, parent, false);
            ViewHolderF viewHolder = new ViewHolderF(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_7.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_g, parent, false);
            ViewHolderG viewHolder = new ViewHolderG(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_8.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_item_h, parent, false);
            ViewHolderH viewHolder = new ViewHolderH(mView);
            return viewHolder;
        }

        return null;
    }


    /**
     * 绑定数据：可以直接拿到已经绑定控件的Viewholder对象
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderA) {
            ((ViewHolderA) holder).tv_emotion.setText(mData.get(position).getName());
        } else if (holder instanceof ViewHolderB) {
            ((ViewHolderB) holder).tv_topic.setText(mData.get(position).getName());
        } else if (holder instanceof ViewHolderC) {
            ((ViewHolderC) holder).tv_topic.setText(mData.get(position).getName());
        } else if (holder instanceof ViewHolderD) {
            ((ViewHolderD) holder).tv_topic.setText(mData.get(position).getName());
        } else if (holder instanceof ViewHolderE) {
            ((ViewHolderE) holder).iv_photo.setImageResource(mData.get(position).getType());
            ((ViewHolderE) holder).cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        map.put(position, true);
                    } else {
                        map.remove(position);
                    }
                }
            });
            if (map != null && map.containsKey(position)) {
                ((ViewHolderE) holder).cb_check.setChecked(true);
            } else {
                ((ViewHolderE) holder).cb_check.setChecked(false);
            }
        } else if (holder instanceof ViewHolderF) {
            //((ViewHolderF) holder).tv_topic.setText(mData.get(position).getName());
        } else if (holder instanceof ViewHolderG) {
            //((ViewHolderG) holder).tv_topic.setText(mData.get(position).getName());
        } else if (holder instanceof ViewHolderH) {
            //((ViewHolderH) holder).tv_topic.setText(mData.get(position).getName());
        }
    }


    //返回值赋值给onCreateViewHolder的参数 viewType
    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getType() == 0) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_1.ordinal();
        } else if (mData.get(position).getType() == 1) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_2.ordinal();
        } else if (mData.get(position).getType() == 2) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_3.ordinal();
        } else if (mData.get(position).getType() == 3) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_4.ordinal();
        } else if (mData.get(position).getType() == 5) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_6.ordinal();
        } else if (mData.get(position).getType() == 6) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_7.ordinal();
        } else if (mData.get(position).getType() == 7) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_8.ordinal();
        } else {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_5.ordinal();
        }
//        return -1;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolderA extends RecyclerView.ViewHolder {
        public ImageView iv_sense, iv_voice1, iv_voice2;
        public TextView tv_topic, tv_summary, tv_emotion;

        private ViewHolderA(View itemView) {
            super(itemView);
            tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_summary = itemView.findViewById(R.id.tv_summary);
            iv_sense = itemView.findViewById(R.id.iv_sense);
            tv_emotion = itemView.findViewById(R.id.tv_emotion);
            iv_voice1 = itemView.findViewById(R.id.iv_voice1);
            iv_voice2 = itemView.findViewById(R.id.iv_voice2);
        }
    }

    class ViewHolderB extends RecyclerView.ViewHolder {
        public ImageView iv_sense, iv_video;
        public TextView tv_topic, tv_summary;

        public ViewHolderB(View itemView) {
            super(itemView);
            tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_summary = itemView.findViewById(R.id.tv_summary);
            iv_sense = itemView.findViewById(R.id.iv_sense);
            iv_video = itemView.findViewById(R.id.iv_video);
        }
    }

    class ViewHolderC extends RecyclerView.ViewHolder {
        public ImageView iv_sense, iv_confide;
        public TextView tv_topic, tv_family;

        public ViewHolderC(View itemView) {
            super(itemView);
            tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_family = itemView.findViewById(R.id.tv_family);
            iv_sense = itemView.findViewById(R.id.iv_sense);
            iv_confide = itemView.findViewById(R.id.iv_confide);
        }
    }

    class ViewHolderD extends RecyclerView.ViewHolder {
        public ImageView iv_sense;
        public TextView tv_topic, tv_summary, tv_emotion;

        public ViewHolderD(View itemView) {
            super(itemView);
            tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_summary = itemView.findViewById(R.id.tv_summary);
            iv_sense = itemView.findViewById(R.id.iv_sense);
            tv_emotion = itemView.findViewById(R.id.tv_emotion);
        }
    }

    class ViewHolderE extends RecyclerView.ViewHolder {
        public ImageView iv_photo;
        public CheckBox cb_check;

        public ViewHolderE(View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            cb_check = itemView.findViewById(R.id.cb_check);
        }
    }

    class ViewHolderF extends RecyclerView.ViewHolder {
        public ImageView iv_photo;
        public CheckBox cb_check;

        public ViewHolderF(View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            cb_check = itemView.findViewById(R.id.cb_check);
        }
    }

    class ViewHolderG extends RecyclerView.ViewHolder {
        public ImageView iv_photo;
        public CheckBox cb_check;

        public ViewHolderG(View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            cb_check = itemView.findViewById(R.id.cb_check);
        }
    }

    class ViewHolderH extends RecyclerView.ViewHolder {
        public ImageView iv_photo;
        public CheckBox cb_check;

        public ViewHolderH(View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            cb_check = itemView.findViewById(R.id.cb_check);
        }
    }
}
