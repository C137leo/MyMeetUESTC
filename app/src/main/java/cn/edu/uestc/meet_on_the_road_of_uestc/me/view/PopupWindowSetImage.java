package cn.edu.uestc.meet_on_the_road_of_uestc.me.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class PopupWindowSetImage extends PopupWindow {

    View contentView;
    public PopupWindowSetImage(Context context){
        super(context);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_selectimage,
                null, false);
        setContentView(contentView);
    }

    @Override
    public View getContentView() {
        return contentView;
    }
}
