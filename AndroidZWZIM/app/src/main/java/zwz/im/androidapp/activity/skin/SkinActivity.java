package zwz.im.androidapp.activity.skin;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;
import zwz.im.androidapp.adapter.RecyclerAdapter;
import zwz.im.androidapp.model.Fruit;

/**
 * 换肤
 */
public class SkinActivity extends BaseActivity{

    private RecyclerView recyler_view;
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_skin);
        setTranslucentStatus(this,R.color.heise); // 顶部状态栏透明

//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        initData();
        initViews();
    }

    private void initViews() {
        recyler_view = findViewById(R.id.skin_recycler_View);
        recyler_view.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyler_view.setAdapter(new RecyclerAdapter(SkinActivity.this, fruitList));
    }

    private void initData() {
        Fruit apple = new Fruit("黄桃", R.drawable.skin_yellow_peach, "skin",null);
        fruitList.add(apple);
        Fruit banana = new Fruit("牛油果", R.drawable.skin_avocado, "skin",null);
        fruitList.add(banana);
        Fruit orange = new Fruit("车厘子", R.drawable.skin_cherry, "skin",null);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("水蜜桃", R.drawable.skin_honey_peach, "skin",null);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("湖水", R.drawable.skin_lake, "skin",null);
        fruitList.add(pear);
        Fruit grape = new Fruit("青草", R.drawable.skin_grass, "skin",null);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("水墨", R.drawable.skin_ink, "skin",null);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("紫罗兰", R.drawable.skin_violet, "skin",null);
        fruitList.add(strawberry);
    }
}
