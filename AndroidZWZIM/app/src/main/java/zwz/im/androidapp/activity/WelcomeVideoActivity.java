package zwz.im.androidapp.activity;

import android.os.Bundle;

import com.ant.liao.GifView;

import zwz.im.androidapp.R;
import zwz.im.androidapp.activity.base.BaseActivity;

public class WelcomeVideoActivity extends BaseActivity {

    private GifView gifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_video);
        gifView = (GifView) findViewById(R.id.gifView);
        gifView.setGifImage(R.drawable.guide_1);
        gifView.setShowDimension(getWindowManager().getDefaultDisplay().getWidth(),getWindowManager().getDefaultDisplay().getHeight());
//        gifView.setGifImageType(GifView.GifImageType.WAIT_FINISH);
        //gifView.setGifImageType(GifView.GifImageType.COVER);
    }

}
