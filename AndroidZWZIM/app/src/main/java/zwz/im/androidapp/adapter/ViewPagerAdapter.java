package zwz.im.androidapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;//创建显示页面view集合
    private Context context;//创建上下文对象


    public ViewPagerAdapter(List<View> views,Context context){
        this.context=context;
        this.views=views;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));//view切换时移除当前页面view
    }


    //加载添加view的方法，类似于ListView中的getView（）
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size(); //获取view的数量
    }


    //判断当前view是不是需要的对象
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==o);
    }
}
