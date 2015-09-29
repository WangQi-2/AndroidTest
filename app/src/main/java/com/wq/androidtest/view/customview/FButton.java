package com.wq.androidtest.view.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/9/29.
 */
public class FButton extends Button implements View.OnTouchListener {

    private final int BUTTON_COLOR = getContext().getResources().getColor(R.color.blue3);
    private final int SHADOW_COLOR = getContext().getResources().getColor(R.color.blue2);
    private final int SHADOW_HEIGHT = 20;
    private final int RADIUS = 20;

    //// TODO: 15/9/29 注释不错
    //custom values
    private boolean isShadowEnabled = true;
    private int mButtonColor;
    private int mShadowColor;
    private int mShadowHeight;
    private int mCornerRadius;
    //native values
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;
    //Background drawable
    private Drawable pressedDrawable;
    private Drawable unpressedDrawable;

    boolean isShadowColorDefined = false;

    public FButton(Context context) {
        super(context);
        init();
        setOnTouchListener(this);
    }

    // TODO: 15/9/29 2 constructor
    public FButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        //函数结构
        init();
        parseAttrs(context, attrs);
        setOnTouchListener(this);
    }

    public FButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        parseAttrs(context, attrs);
        setOnTouchListener(this);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        refresh();
    }

    // TODO: 15/9/29 view的所有回调函数
    // TODO: 15/9/29 view touch事件被消费或者不消费,后续事件如何处理
    // TODO: 15/9/29 ontouch事件对事件传播的影响
    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                updateBackground(pressedDrawable);
                //// TODO: 15/9/29 两次调整padding,大小是否有影响
                setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom);
                break;
            case MotionEvent.ACTION_MOVE:
                Rect r = new Rect();
                //// TODO: 15/9/29  为什么不是返回值赋值
                getLocalVisibleRect(r);
                if (!r.contains((int) motionEvent.getX(), (int) motionEvent.getY() + 3 * mShadowHeight) &&
                        !r.contains((int) motionEvent.getX(), (int) motionEvent.getY() - 3 * mShadowHeight)) {
                    updateBackground(unpressedDrawable);
//                    this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);
                    setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
                }
                break;
            // TODO: 15/9/29 motionevent事件有哪些
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
                updateBackground(unpressedDrawable);
                setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
                break;
        }
        return false;
    }

    private void init() {
        //init default values
        isShadowEnabled = true;
        Resources resources = getResources();
        // TODO: 15/9/29 would be null??
        if (resources == null) return;
        mButtonColor = BUTTON_COLOR;
        mShadowColor = SHADOW_COLOR;
        mShadowHeight = SHADOW_HEIGHT;
        mCornerRadius = RADIUS;
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        //Load from custom attributes
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FButton);
        // TODO: 15/9/29 would be null??
        if (typedArray == null) return;

        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.FButton_shadowEnabled:
                    isShadowEnabled = typedArray.getBoolean(index, true);
                    break;
                case R.styleable.FButton_buttonColor:
                    mButtonColor = typedArray.getColor(index, BUTTON_COLOR);
                    break;
                case R.styleable.FButton_shadowColor:
                    mShadowColor = typedArray.getColor(index, SHADOW_COLOR);
                    break;
                case R.styleable.FButton_shadowHeight:
                    //// TODO: 15/9/29 我一直用错了??
                    mShadowHeight = typedArray.getDimensionPixelSize(index, SHADOW_HEIGHT);
                    break;
                case R.styleable.FButton_cornerRadius:
                    mCornerRadius = typedArray.getDimensionPixelSize(index, RADIUS);
                    break;
                case android.R.attr.paddingLeft:
                    mPaddingLeft = typedArray.getDimensionPixelSize(index, 0);
                    break;
                case android.R.attr.paddingTop:
                    mPaddingTop = typedArray.getDimensionPixelSize(index, 0);
                    break;
                case android.R.attr.paddingRight:
                    mPaddingRight = typedArray.getDimensionPixelSize(index, 0);
                    break;
                case android.R.attr.paddingBottom:
                    mPaddingBottom = typedArray.getDimensionPixelSize(index, 0);
                    break;
            }
        }
        //// TODO: 15/9/29  ??
        typedArray.recycle();
    }

    //// FIXME: 15/9/29 not support enable now
    private void refresh() {

        pressedDrawable = createDrawable(mCornerRadius, Color.TRANSPARENT, mButtonColor);
        unpressedDrawable = createDrawable(mCornerRadius, mButtonColor, mShadowColor);
        updateBackground(unpressedDrawable);
        //Set padding
//        this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);

    }

    private LayerDrawable createDrawable(int radius, int topColor, int bottomColor) {

        //todo
        float[] outerRaidus = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape rectShape = new RoundRectShape(outerRaidus, null, null);
        ShapeDrawable topDrawable = new ShapeDrawable(rectShape);
        topDrawable.getPaint().setColor(topColor);
        ShapeDrawable bottomDrawable = new ShapeDrawable(rectShape);
        bottomDrawable.getPaint().setColor(bottomColor);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bottomDrawable, topDrawable});
        layerDrawable.setLayerInset(0, 0, mShadowHeight, 0, 0);
        layerDrawable.setLayerInset(1, 0, 0, 0, mShadowHeight);
        return layerDrawable;
    }

    private void updateBackground(Drawable drawable) {
        if (drawable == null) return;
        if (Build.VERSION.SDK_INT > 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }


}
