package zwz.im.androidapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import zwz.im.androidapp.R;

public class GuidanceAdapter extends PagerAdapter {

    String[] objects;
    Context context;
    SparseArray<View> views = new SparseArray<>();

    public GuidanceAdapter(Context context, String[] objects){
        this.context = context;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.length;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(views.get(position));
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public Object instantiateItem(ViewGroup view, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.view_page_guidance, null);

        String[] txtDescribe = objects[position].split(",");
        TextView txtUp = (TextView) itemView.findViewById(R.id.id_txtUp);
        TextView txtDown = (TextView) itemView.findViewById(R.id.id_txtDown);
        txtUp.setText(txtDescribe[0]);
        txtDown.setText(txtDescribe[1]);
        views.put(position, itemView);

        view.addView(itemView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        return itemView;
    }

    @Override public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
