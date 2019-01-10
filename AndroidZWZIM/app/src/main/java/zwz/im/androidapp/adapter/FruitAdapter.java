package zwz.im.androidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zwz.im.androidapp.R;
import zwz.im.androidapp.model.Fruit;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                //Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.iv_voice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                //Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        //holder.voice.setImageResource(fruit.getImageId());
        //holder.tv_emotion.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView iv_sense, iv_voice1, iv_voice2, iv_video, iv_confide;
        TextView tv_topic, tv_summary, tv_emotion, tv_family;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            iv_sense = view.findViewById(R.id.iv_sense);
            iv_voice1 = view.findViewById(R.id.iv_voice1);
            iv_voice2 = view.findViewById(R.id.iv_voice2);
            iv_video = view.findViewById(R.id.iv_video);
            iv_confide = view.findViewById(R.id.iv_confide);
            tv_topic = view.findViewById(R.id.tv_topic);
            tv_summary = view.findViewById(R.id.tv_summary);
            tv_emotion = view.findViewById(R.id.tv_emotion);
            tv_family = view.findViewById(R.id.tv_family);
        }
    }

}