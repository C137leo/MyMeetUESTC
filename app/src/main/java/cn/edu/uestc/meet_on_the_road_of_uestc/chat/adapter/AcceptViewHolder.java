package cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AcceptViewHolder extends RecyclerView.ViewHolder {
    ImageView acceptImageview;
    TextView acceptMessage;
    public AcceptViewHolder(View itemView) {
        super(itemView);
        acceptImageview=itemView.findViewById(R.id.accept_image);
        acceptMessage=itemView.findViewById(R.id.accept_message);
    }
}
