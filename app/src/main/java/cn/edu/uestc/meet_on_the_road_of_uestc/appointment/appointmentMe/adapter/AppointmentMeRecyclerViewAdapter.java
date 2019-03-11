package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;

public class AppointmentMeRecyclerViewAdapter extends RecyclerView.Adapter {
    List<AppointmentInfo> appointmentInfoList;
    int APPOINTMENT_ACCEPT=0;
    int APPOINTMENT_PUBLISH=1;
    int type;
    public AppointmentMeRecyclerViewAdapter(List<AppointmentInfo> appointmentInfoList,int type) {
        this.appointmentInfoList = appointmentInfoList;
        this.type=type;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return appointmentInfoList.size();
    }

    class AppointmentMeAcceptRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView appointmentMeAcceptType;
        TextView appointmentMeAcceptDetail;
        TextView appointmentMeAcceptLocation;
        TextView appointmentMeAcceptNum;
        TextView appointmentMeAcceptSetNum;
        TextView appointmentMeAcceptTime;
        Button appointmentMeAcceptStartChat;
        CircleImageView appointmentMeAcceptImage1;
        TextView appointmentMeAcceptNickname1;
        CircleImageView appointmentMeAcceptImage2;
        TextView appointmentMeAcceptNickname2;
        CircleImageView appointmentMeAcceptImage3;
        TextView appointmentMeAcceptNickname3;
        CircleImageView appointmentMeAcceptImage4;
        TextView appointmentMeAcceptNickname4;
        CircleImageView appointmentMeAcceptImage5;
        TextView appointmentMeAcceptNickname5;
        CircleImageView appointmentMeAcceptImage6;
        TextView appointmentMeAcceptNickname6;
        CircleImageView appointmentMeAcceptImage7;
        TextView appointmentMeAcceptNickname7;
        CircleImageView appointmentMeAcceptImage8;
        TextView appointmentMeAcceptNickname8;
        LinearLayout appointmentMeAcceptLayout1;
        LinearLayout appointmentMeAcceptLayout2;
        LinearLayout appointmentMeAcceptLayout3;
        LinearLayout appointmentMeAcceptLayout4;
        LinearLayout appointmentMeAcceptLayout5;
        LinearLayout appointmentMeAcceptLayout6;
        LinearLayout appointmentMeAcceptLayout7;
        LinearLayout appointmentMeAcceptLayout8;
        public AppointmentMeAcceptRecyclerViewHolder(View itemView) {
            super(itemView);
            appointmentMeAcceptType=itemView.findViewById(R.id.appointment_me_accept_type);
            appointmentMeAcceptDetail=itemView.findViewById(R.id.appointment_me_accept_detail);
            appointmentMeAcceptLocation=itemView.findViewById(R.id.appointment_me_accept_location);
            appointmentMeAcceptNum=itemView.findViewById(R.id.appointment_me_accept_num);
            appointmentMeAcceptSetNum=itemView.findViewById(R.id.appointment_me_accept_set_num);
            appointmentMeAcceptTime=itemView.findViewById(R.id.appointment_me_accept_time);
            appointmentMeAcceptStartChat=itemView.findViewById(R.id.appointment_me_accept_start_chat);
            appointmentMeAcceptImage1=itemView.findViewById(R.id.appointment_me_accept_person1);
            appointmentMeAcceptNickname1=itemView.findViewById(R.id.appointment_me_accept_nickname1);
            appointmentMeAcceptLayout1=itemView.findViewById(R.id.appointment_me_accept_linearLayout1);
            appointmentMeAcceptImage2=itemView.findViewById(R.id.appointment_me_accept_person2);
            appointmentMeAcceptNickname2=itemView.findViewById(R.id.appointment_me_accept_nickname2);
            appointmentMeAcceptLayout2=itemView.findViewById(R.id.appointment_me_accept_linearLayout2);
            appointmentMeAcceptImage3=itemView.findViewById(R.id.appointment_me_accept_person3);
            appointmentMeAcceptNickname3=itemView.findViewById(R.id.appointment_me_accept_nickname3);
            appointmentMeAcceptLayout3=itemView.findViewById(R.id.appointment_me_accept_linearLayout3);
            appointmentMeAcceptImage4=itemView.findViewById(R.id.appointment_me_accept_person4);
            appointmentMeAcceptNickname4=itemView.findViewById(R.id.appointment_me_accept_nickname4);
            appointmentMeAcceptLayout4=itemView.findViewById(R.id.appointment_me_accept_linearLayout4);
            appointmentMeAcceptImage5=itemView.findViewById(R.id.appointment_me_accept_person5);
            appointmentMeAcceptNickname5=itemView.findViewById(R.id.appointment_me_accept_nickname5);
            appointmentMeAcceptLayout5=itemView.findViewById(R.id.appointment_me_accept_linearLayout5);
            appointmentMeAcceptImage6=itemView.findViewById(R.id.appointment_me_accept_person6);
            appointmentMeAcceptNickname6=itemView.findViewById(R.id.appointment_me_accept_nickname6);
            appointmentMeAcceptLayout6=itemView.findViewById(R.id.appointment_me_accept_linearLayout6);
            appointmentMeAcceptImage7=itemView.findViewById(R.id.appointment_me_accept_person7);
            appointmentMeAcceptNickname7=itemView.findViewById(R.id.appointment_me_accept_nickname7);
            appointmentMeAcceptLayout7=itemView.findViewById(R.id.appointment_me_accept_linearLayout7);
            appointmentMeAcceptImage8=itemView.findViewById(R.id.appointment_me_accept_person8);
            appointmentMeAcceptNickname8=itemView.findViewById(R.id.appointment_me_accept_nickname8);
            appointmentMeAcceptLayout8=itemView.findViewById(R.id.appointment_me_accept_linearLayout8);
        }

    }

    class AppointmentMePublishRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView appointmentMePublishType;
        TextView appointmentMePublishDetail;
        TextView appointmentMePublishLocation;
        TextView appointmentMePublishNum;
        TextView appointmentMePublishSetNum;
        TextView appointmentMePublishTime;
        Button appointmentMePublishStartChat;
        CircleImageView appointmentMePublishImage1;
        TextView appointmentMePublishNickname1;
        CircleImageView appointmentMePublishImage2;
        TextView appointmentMePublishNickname2;
        CircleImageView appointmentMePublishImage3;
        TextView appointmentMePublishNickname3;
        CircleImageView appointmentMePublishImage4;
        TextView appointmentMePublishNickname4;
        CircleImageView appointmentMePublishImage5;
        TextView appointmentMePublishNickname5;
        CircleImageView appointmentMePublishImage6;
        TextView appointmentMePublishNickname6;
        CircleImageView appointmentMePublishImage7;
        TextView appointmentMePublishNickname7;
        CircleImageView appointmentMePublishImage8;
        TextView appointmentMePublishNickname8;
        LinearLayout appointmentMePublishLayout1;
        LinearLayout appointmentMePublishLayout2;
        LinearLayout appointmentMePublishLayout3;
        LinearLayout appointmentMePublishLayout4;
        LinearLayout appointmentMePublishLayout5;
        LinearLayout appointmentMePublishLayout6;
        LinearLayout appointmentMePublishLayout7;
        LinearLayout appointmentMePublishLayout8;
        public AppointmentMePublishRecyclerViewHolder(View itemView) {
            super(itemView);
            appointmentMePublishType=itemView.findViewById(R.id.appointment_me_publish_type);
            appointmentMePublishDetail=itemView.findViewById(R.id.appointment_me_publish_detail);
            appointmentMePublishLocation=itemView.findViewById(R.id.appointment_me_publish_location);
            appointmentMePublishNum=itemView.findViewById(R.id.appointment_me_publish_num);
            appointmentMePublishSetNum=itemView.findViewById(R.id.appointment_me_publish_set_num);
            appointmentMePublishTime=itemView.findViewById(R.id.appointment_me_publish_time);
            appointmentMePublishStartChat=itemView.findViewById(R.id.appointment_me_publish_start_chat);
            appointmentMePublishImage1=itemView.findViewById(R.id.appointment_me_publish_person1);
            appointmentMePublishNickname1=itemView.findViewById(R.id.appointment_me_publish_nickname1);
            appointmentMePublishLayout1=itemView.findViewById(R.id.appointment_me_publish_linearLayout1);
            appointmentMePublishImage2=itemView.findViewById(R.id.appointment_me_publish_person2);
            appointmentMePublishNickname2=itemView.findViewById(R.id.appointment_me_publish_nickname2);
            appointmentMePublishLayout2=itemView.findViewById(R.id.appointment_me_publish_linearLayout2);
            appointmentMePublishImage3=itemView.findViewById(R.id.appointment_me_publish_person3);
            appointmentMePublishNickname3=itemView.findViewById(R.id.appointment_me_publish_nickname3);
            appointmentMePublishLayout3=itemView.findViewById(R.id.appointment_me_publish_linearLayout3);
            appointmentMePublishImage4=itemView.findViewById(R.id.appointment_me_publish_person4);
            appointmentMePublishNickname4=itemView.findViewById(R.id.appointment_me_publish_nickname4);
            appointmentMePublishLayout4=itemView.findViewById(R.id.appointment_me_publish_linearLayout4);
            appointmentMePublishImage5=itemView.findViewById(R.id.appointment_me_publish_person5);
            appointmentMePublishNickname5=itemView.findViewById(R.id.appointment_me_publish_nickname5);
            appointmentMePublishLayout5=itemView.findViewById(R.id.appointment_me_publish_linearLayout5);
            appointmentMePublishImage6=itemView.findViewById(R.id.appointment_me_publish_person6);
            appointmentMePublishNickname6=itemView.findViewById(R.id.appointment_me_publish_nickname6);
            appointmentMePublishLayout6=itemView.findViewById(R.id.appointment_me_publish_linearLayout6);
            appointmentMePublishImage7=itemView.findViewById(R.id.appointment_me_publish_person7);
            appointmentMePublishNickname7=itemView.findViewById(R.id.appointment_me_publish_nickname7);
            appointmentMePublishLayout7=itemView.findViewById(R.id.appointment_me_publish_linearLayout7);
            appointmentMePublishImage8=itemView.findViewById(R.id.appointment_me_publish_person8);
            appointmentMePublishNickname8=itemView.findViewById(R.id.appointment_me_publish_nickname8);
            appointmentMePublishLayout8=itemView.findViewById(R.id.appointment_me_publish_linearLayout8);
        }
    }

}
