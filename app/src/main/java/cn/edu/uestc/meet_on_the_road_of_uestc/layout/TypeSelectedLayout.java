package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TypeSelectedLayout extends LinearLayout {

    public TypeSelectedLayout(Context context) {
        super(context);
    }

    public TypeSelectedLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TypeSelectedLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TypeSelectedLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context,attrs,defStyleAttr,defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
