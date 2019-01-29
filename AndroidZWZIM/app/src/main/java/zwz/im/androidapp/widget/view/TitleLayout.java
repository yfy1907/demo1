package zwz.im.androidapp.widget.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zwz.im.androidapp.R;

public class TitleLayout extends LinearLayout {

    private ImageView iv_back;
    private TextView title, add;

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_title, this);
        initViews();
    }

    private void initViews() {
        iv_back = (ImageView) findViewById(R.id.back);
        add = (TextView) findViewById(R.id.add);
        title = (TextView) findViewById(R.id.title);
        iv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // ((Activity) getContext()).finish();
            }
        });
    }
}
