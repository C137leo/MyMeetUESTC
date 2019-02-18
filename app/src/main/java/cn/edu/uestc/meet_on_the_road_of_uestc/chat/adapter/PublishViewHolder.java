package cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class PublishViewHolder extends RecyclerView.ViewHolder {
    ImageView publishImage;
    TextView publishMessge;
    public PublishViewHolder(View itemView) {
        super(itemView);
        publishImage=itemView.findViewById(R.id.publish_image);
        publishMessge=itemView.findViewById(R.id.publish_message);
    }
}
