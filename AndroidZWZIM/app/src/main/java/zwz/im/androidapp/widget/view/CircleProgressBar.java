package zwz.im.androidapp.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import zwz.im.androidapp.R;

public class CircleProgressBar extends View {

    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画圆环的画笔背景色
    private Paint mRingPaintBg;
    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 圆环背景颜色
    private int mRingBgColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 50;
    // 当前进度
    private int mProgress;

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    //属性
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TasksCompletedView, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.TasksCompletedView_radius, 80);
        mStrokeWidth = typeArray.getDimension(R.styleable.TasksCompletedView_strokeWidth, 10);
        mCircleColor = typeArray.getColor(R.styleable.TasksCompletedView_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.TasksCompletedView_ringColor, 0xFFFFFFFF);
        mRingBgColor = typeArray.getColor(R.styleable.TasksCompletedView_ringBgColor, 0xFFFFFFFF);

        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    //初始化画笔
    private void initVariable() {
        //内圆
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        //外圆弧背景
        mRingPaintBg = new Paint();
        mRingPaintBg.setAntiAlias(true);
        mRingPaintBg.setColor(mRingBgColor);
        mRingPaintBg.setStyle(Paint.Style.STROKE);
        mRingPaintBg.setStrokeWidth(mStrokeWidth);


        //外圆弧
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);   //只描边，不填充
        mRingPaint.setStrokeWidth(10);
        //mRingPaint.setStrokeCap(Paint.Cap.ROUND);//设置线冒样式，有圆 有方

        //中间字
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(mRingColor);
        mTextPaint.setTextSize(mRadius / 3);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
    }

    //画图
    @Override
    protected void onDraw(Canvas canvas) {
        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        //内圆
        canvas.drawCircle(mXCenter, mYCenter, mRadius + 4, mCirclePaint);

        //外圆弧背景
//        RectF oval1 = new RectF();
//        oval1.left = (mXCenter - mRingRadius);
//        oval1.top = (mYCenter - mRingRadius);
//        oval1.right = mRingRadius * 2 + (mXCenter - mRingRadius);
//        oval1.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
//        canvas.drawArc(oval1, 0, 360, false, mRingPaintBg); //圆弧所在的椭圆对象、圆弧的起始角度、圆弧的角度、是否显示半径连线

        //外圆弧
        if (mProgress > 0) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mRingPaint); //

//            TextPaint textPaint = new TextPaint();
//            textPaint.setARGB(0xFF, 0, 0, 0);
//            textPaint.setTextSize(mRadius / 3);
//            textPaint.setAntiAlias(true);
//            Paint.FontMetrics fm = textPaint.getFontMetrics();
//            mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
//            StaticLayout layout = new StaticLayout("共" + mTotalProgress + "次\n成功" + mProgress + "次", textPaint, 300,
//                    Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, true);
//            canvas.save();
//            canvas.translate(mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4);
//            layout.draw(canvas);
//            canvas.restore();//别忘了restore

            //字体
            String txt1 = "共" + mTotalProgress + "次";
            String txt2 = "成功" + mProgress + "次";
            String[] strings = {txt1, txt2};
            for (int i = 0; i < strings.length; i++) {
                mTxtWidth = mTextPaint.measureText(strings[i] + "", 0, strings[i].length());
                if (i != 0) {
                    canvas.drawText(strings[i] + "", mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4 + 40f, mTextPaint);
                } else {
                    canvas.drawText(strings[i] + "", mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4 - 40f, mTextPaint);
                }
            }
        }
    }

    //设置进度
    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();//重绘
    }
}

