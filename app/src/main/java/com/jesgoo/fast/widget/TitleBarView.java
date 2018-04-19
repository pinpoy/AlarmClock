package com.jesgoo.fast.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jesgoo.fast.R;


/**
 * ProjectName: TitleBarView
 * Description: 自定义控件 titleBar
 * <p>
 * author: JeyZheng
 * version: 4.0
 * created at: 2016/8/15 13:36
 */
@SuppressWarnings("ALL")
public class TitleBarView extends RelativeLayout {
    private View leftBox;
    private View centerBox;
    private View rightBox;
    private View rightBox2;

    private TextView title;
    private ImageView leftImg;
    private TextView leftText;
    private ImageView rightImg;
    private TextView rightText;
    private ImageView rightImg2;
    private TextView rightText2;

    public TitleBarView(Context context) {
        super(context);
        init(context, null);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.titlebar_layout, this);
        // 某些布局属性要加在ViewStub而不是实际的布局上面，才会起作用，比如用android:layout_margin*系列属性，
        // 如果加在TextView登View上面，则不会起作用，需要放在它的ViewStub上面才会起作用。
        // 而ViewStub的属性在inflate()后会都传给相应的布局。
        // NOTE:
        // 1. 所以此处的rightBox，rightBox2被填充完后，仍能产生距离，其实是真实布局中的android:padding*系列属性起作用，
        //    而不是android:layout_margin*系列属性的作用
        // 2. ImageView当没有资源的时候，该ImageView也不会被显示出来，即使设置了padding，即不会占距
        // 3. TextView当没有设置文本的时候，该TextView会显示出来，同时会已计算padding的距离，并且占距
        ViewStub vsLeft = (ViewStub) view.findViewById(R.id.vs_left);
        ViewStub vsCenter = (ViewStub) view.findViewById(R.id.vs_center);
        ViewStub vsRight = (ViewStub) view.findViewById(R.id.vs_right);
        ViewStub vsRight2 = (ViewStub) view.findViewById(R.id.vs_right2);

        // ViewStub只能Inflate一次，之后ViewStub对象会被置为空。
        // 按句话说，某个被ViewStub指定的布局被Inflate后，就不会够再通过ViewStub来控制它了。
        leftBox = vsLeft.inflate();
        centerBox = vsCenter.inflate();
        rightBox = vsRight.inflate();
        rightBox2 = vsRight2.inflate();

        title = (TextView) centerBox.findViewById(R.id.tv_title_center);

        leftImg = (ImageView) leftBox.findViewById(R.id.iv_title_left);
        leftText = (TextView) leftBox.findViewById(R.id.tv_title_left);

        rightImg = (ImageView) rightBox.findViewById(R.id.iv_title_right);
        rightText = (TextView) rightBox.findViewById(R.id.tv_title_right);

        rightImg2 = (ImageView) rightBox2.findViewById(R.id.iv_title_right2);
        rightText2 = (TextView) rightBox2.findViewById(R.id.tv_title_right2);

        if (attrs != null) {
            final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBarStyle);
            // text
            final String leftStr = ta.getString(R.styleable.TitleBarStyle_leftText);

            final String rightStr = ta.getString(R.styleable.TitleBarStyle_rightText);
            final String rightStr2 = ta.getString(R.styleable.TitleBarStyle_rightText2);

            final String titleStr = ta.getString(R.styleable.TitleBarStyle_titleText);

            // text size
            final float leftTxtSize = ta.getDimension(R.styleable.TitleBarStyle_leftTxtSize, -1);
            final float titleSize = ta.getDimension(R.styleable.TitleBarStyle_titleSize, -1);
            final float rightTxtSize = ta.getDimension(R.styleable.TitleBarStyle_rightTxtSize, -1);
            final float rightTxt2Size = ta.getDimension(R.styleable.TitleBarStyle_rightTxt2Size, -1);

            // src
            final int leftSrc = ta.getResourceId(R.styleable.TitleBarStyle_leftSrc, -1);
            final int rightSrc = ta.getResourceId(R.styleable.TitleBarStyle_rightSrc, -1);
            final int rightSrc2 = ta.getResourceId(R.styleable.TitleBarStyle_rightSrc2, -1);

            // color
            final int leftTxtColor = ta.getColor(R.styleable.TitleBarStyle_leftTxtColor, -1);
            final int titleColor = ta.getResourceId(R.styleable.TitleBarStyle_titleColor, -1);
            final int rightTxtColor = ta.getResourceId(R.styleable.TitleBarStyle_rightTxtColor, -1);
            final int rightTxt2Color = ta.getResourceId(R.styleable.TitleBarStyle_rightTxt2Color, -1);

            // text
            if (!TextUtils.isEmpty(leftStr)) {
                setLeftBox(leftStr);
            }

            if (!TextUtils.isEmpty(titleStr)) {
                setTitleView(titleStr);
            }

            if (!TextUtils.isEmpty(rightStr)) {
                setRightBox(rightStr);
            }

            if (!TextUtils.isEmpty(rightStr2)) {
                setRightBox2(rightStr2);
            }

            // text size
            if (leftTxtSize > 0) {
                setLeftTxtSize(leftTxtSize);
            }

            if (titleSize > 0) {
                setTitleSize(titleSize);
            }

            if (rightTxtSize > 0) {
                setRightTxtSize(rightTxtSize);
            }

            if (rightTxt2Size > 0) {
                setRightTxt2Size(rightTxt2Size);
            }

            // resId
            if (leftSrc > 0) {
                setLeftBox(leftSrc);
            }

            if (rightSrc > 0) {
                setRightBox(rightSrc);
            }

            if (rightSrc2 > 0) {
                setRightBox2(rightSrc2);
            }

            // color
            if (leftTxtColor > 0) {
                setLeftTxtColor(leftTxtColor);
            }

            if (titleColor > 0) {
                setTitleColor(titleColor);
            }

            if (rightTxtColor > 0) {
                setRightTxtColor(rightTxtColor);
            }

            if (rightTxt2Color > 0) {
                setRightTxt2Color(rightTxt2Color);
            }

            ta.recycle();
        }
    }

    // -------------- text size ----------------
    public void setLeftTxtSize(float leftTxtSize) {
        leftText.setTextSize(leftTxtSize);
    }

    public void setTitleSize(float titleSize) {
        title.setTextSize(titleSize);
    }

    public void setRightTxtSize(float rightTxtSize) {
        rightText.setTextSize(rightTxtSize);
    }

    public void setRightTxt2Size(float rightTxt2Size) {
        rightText2.setTextSize(rightTxt2Size);
    }

    // -------------- color ----------------
    public void setLeftTxtColor(int leftTxtColor) {
        leftText.setTextColor(ContextCompat.getColorStateList(getContext(), leftTxtColor));
    }

    public void setTitleColor(int titleColor) {


        title.setTextColor(ContextCompat.getColor(getContext(), titleColor));
    }

    public void setRightTxtColor(int rightTxtColor) {
        rightText.setTextColor(ContextCompat.getColorStateList(getContext(), rightTxtColor));
    }

    private void setRightTxt2Color(int rightTxt2Color) {
        rightText2.setTextColor(ContextCompat.getColorStateList(getContext(), rightTxt2Color));
    }

    /**
     * 设置显示相关模块
     *
     * @param LeftVisibility
     * @param centerVisibility
     * @param rightVisibility
     */
    public void setBoxVisibility(int LeftVisibility, int centerVisibility,
                                 int rightVisibility) {
        leftBox.setVisibility(LeftVisibility);
        centerBox.setVisibility(centerVisibility);
        rightBox.setVisibility(rightVisibility);
    }

    public void setLeftBox(int res) {
        leftImg.setImageResource(res);
        leftImg.setVisibility(View.VISIBLE);
        leftText.setVisibility(View.GONE);
    }

    public void setLeftBox(String text) {
        leftText.setText(text);
        leftImg.setVisibility(View.GONE);
        leftText.setVisibility(View.VISIBLE);
    }

    public void setTitleView(int resId) {
        title.setText(resId);
        centerBox.setVisibility(View.VISIBLE);
    }

    public void setTitleView(String titleStr) {
        title.setText(titleStr);
        centerBox.setVisibility(View.VISIBLE);
    }

    public void setRightBox(int res) {
        rightImg.setImageResource(res);
        rightImg.setVisibility(View.VISIBLE);
        rightText.setVisibility(View.GONE);
    }

    public void setRightBox2(int res) {
        rightImg2.setImageResource(res);
        rightImg2.setVisibility(View.VISIBLE);
        rightText2.setVisibility(View.GONE);
    }

    public void setRightBox(String text) {
        rightText.setText(text);
        rightImg.setVisibility(View.GONE);
        rightText.setVisibility(View.VISIBLE);
    }

    public void setRightBox2(String text) {
        rightText2.setText(text);
        rightImg2.setVisibility(View.GONE);
        rightText2.setVisibility(View.VISIBLE);
    }

    // ----------------- visibility -----------------
    public void setLeftBoxVisibility(int visibility) {
        leftBox.setVisibility(visibility);
    }

    public void setCenterBoxVisibility(int visibility) {
        centerBox.setVisibility(visibility);
    }

    public void setRightBoxVisibility(int visibility) {
        rightBox.setVisibility(visibility);
    }

    public void setRightBox2Visibility(int visibility) {
        rightBox2.setVisibility(visibility);
    }

    // ----------------- listener -----------------
    public void setLeftBoxListener(OnClickListener listener) {
        leftBox.setOnClickListener(listener);
    }

    private void showComp(View view, boolean s) {
        if (s) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        } else {
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
            }
        }
    }

    public void showLeftBox(boolean s) {
        showComp(leftBox, s);
    }

    public void selectLeftBox(boolean s) {
        leftBox.setSelected(s);
    }

    public void setLeftBoxEnable(boolean e) {
        leftBox.setEnabled(e);
    }

    public void setCenterBoxListener(OnClickListener listener) {
        centerBox.setOnClickListener(listener);
    }

    public void showCenterBox(boolean s) {
        showComp(centerBox, s);
    }

    public void selectCenterBox(boolean s) {
        centerBox.setSelected(s);
    }

    public void setRightBoxListener(OnClickListener listener) {
        rightBox.setOnClickListener(listener);
    }

    public void showRightBox(boolean s) {
        showComp(rightBox, s);
    }

    public void selectRightBox(boolean s) {
        rightBox.setSelected(s);
    }

    public void setRightBoxEnable(boolean e) {
        rightBox.setEnabled(e);
    }

    public void setRightBox2Listener(OnClickListener listener) {
        rightBox2.setOnClickListener(listener);
    }

    public void selectRightBox2(boolean s) {
        rightBox2.setSelected(s);
    }

    public void showRightBox2(boolean s) {
        showComp(rightBox2, s);
    }

    public void setRightBox2Enable(boolean e) {
        rightBox2.setEnabled(e);
    }

    /**
     * It gets the left text view.
     *
     * @return It returns the left text view.
     */
    public TextView getLeftTextView() {
        return leftText;
    }

    /**
     * It gets the right image view.
     *
     * @return It returns the right image view.
     */
    public ImageView getRightImageView() {
        return rightImg;
    }

    /**
     * It gets the right text view.
     *
     * @return It returns the right text view.
     */
    public TextView getRightTextView() {
        return rightText;
    }
}
