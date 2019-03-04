package zwz.im.androidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import zwz.im.androidapp.R;
import zwz.im.androidapp.model.Fruit;

public class ConnectReplyListViewAdapter extends BaseAdapter implements ListAdapter {


    private Context context;
    // private LayoutInflater inflater;
    private ViewHolder holder = null;

    private List<Fruit> listData;

    public ConnectReplyListViewAdapter(List<Fruit> mlistData, Context mcontext){
        listData = mlistData;
        context = mcontext;
    }

    public static class ViewHolder {
        public TextView topic_title;
    }

    @Override
    public int getCount() {
        return this.listData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            // convertView = this.inflater.inflate(R.layout.recycle_item_g, parent, false);
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_h, parent, false);
            holder.topic_title = convertView.findViewById(R.id.topic_title);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Fruit fruit = listData.get(position);
        holder.topic_title.setText(fruit.getName());
        return convertView;
    }
}
