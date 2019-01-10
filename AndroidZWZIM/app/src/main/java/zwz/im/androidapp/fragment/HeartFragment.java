package zwz.im.androidapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import zwz.im.androidapp.R;

public class HeartFragment extends BaseFragmentHome implements View.OnClickListener {

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoaded;

    private ImageView iv_no_pwd, iv_pwd, iv_share, write_again;;
    private Button pwd_code;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("michael-onCreateView", "心室");
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            //mView = inflater.inflate(R.layout.heart, container, false);
            mView = inflater.inflate(R.layout.heart_voice, container, false);
            initView();
            isPrepared = true;
            // 实现懒加载
            lazyLoad();
        }
        //缓存的mView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }

    /**
     * 初始化控件
     */
    private void initView() {
//        pwd_code = mView.findViewById(R.id.pwd_code);
//        iv_pwd = mView.findViewById(R.id.iv_pwd);
//        iv_no_pwd = mView.findViewById(R.id.iv_no_pwd);
//        pwd_code.setOnClickListener(this);
//        iv_pwd.setOnClickListener(this);
//        iv_no_pwd.setOnClickListener(this);

        iv_share = find(R.id.iv_share);
        write_again = find(R.id.write_again);
        iv_share.setOnClickListener(this);
        write_again.setOnClickListener(this);

    }

    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoaded) {
            return;
        }
        //填充各控件的数据
        mHasLoaded = true;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pwd_code:

                break;

            case R.id.iv_pwd:

                break;

            case R.id.iv_no_pwd:

                break;
            case R.id.write_again:
                showDialog("again");
                break;
            case R.id.iv_share:
                showDialog("share");
                break;


        }
    }

    private void showDialog(String type) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        if (type.equals("again")) {
            bottomSheetDialog.setContentView(R.layout.write_again);
            //给布局设置透明背景色
            bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                    .setBackgroundColor(getResources().getColor(android.R.color.transparent));
            bottomSheetDialog.show();
//            et_reason = bottomSheetDialog.findViewById(R.id.et_reason);
//            tv_reason.setText("请您选择踢人的原因：");
        } else {
            bottomSheetDialog.setContentView(R.layout.share);
            //给布局设置透明背景色
            bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                    .setBackgroundColor(getResources().getColor(android.R.color.transparent));
            bottomSheetDialog.show();
        }
    }
}
