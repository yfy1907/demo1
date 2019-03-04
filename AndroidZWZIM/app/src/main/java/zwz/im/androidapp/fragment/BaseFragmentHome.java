package zwz.im.androidapp.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseAndroid;

public abstract class BaseFragmentHome extends Fragment {
    /**
     * Fragment当前状态是否可见
     */
    public boolean isVisible;

    /**
     * inflate布局文件 返回的view
     */
    public View mView;

    /**
     * 简化 findViewById
     *
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T find(int viewId) {
        return (T) mView.findViewById(viewId);
    }

    /**
     * setUserVisibleHint是在onCreateView之前调用的
     * 设置Fragment可见状态
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /**
         * 判断是否可见
         */
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    private void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    private void onInvisible() {
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    public abstract void lazyLoad();


    protected void setTranslucentStatus(Activity activity, int colorId) {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            // 如果亮色，设置状态栏文字为黑色
            window.setStatusBarColor(activity.getResources().getColor(colorId));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }


}