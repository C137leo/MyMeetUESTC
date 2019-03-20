package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;

public class AppointmentMeRecyclerViewAdapter extends RecyclerView.Adapter {
    List<AppointmentInfo> appointmentInfoList;
    int APPOINTMENT_ACCEPT=0;
    int APPOINTMENT_PUBLISH=1;
    int type;
    View view;
    AppointmentMeAcceptRecyclerViewHolder appointmentMeAcceptRecyclerViewHolder;
    AppointmentMePublishRecyclerViewHolder appointmentMePublishRecyclerViewHolder;
    ExpandClickListener expandClickListener;
    RotateAnimation acceptExpandAnimation;
    RotateAnimation publishExpandAnimation;
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
        if(type==0){
            Log.d("type","0");
            view= LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.appointment_me_accept_layout,parent,false);
            appointmentMeAcceptRecyclerViewHolder=new AppointmentMeAcceptRecyclerViewHolder(view);
            return appointmentMeAcceptRecyclerViewHolder;
        }else if(type==1){
            Log.d("type","1");
            view=LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.appointment_me_publish_layout,parent,false);
            appointmentMePublishRecyclerViewHolder=new AppointmentMePublishRecyclerViewHolder(view);
            return appointmentMePublishRecyclerViewHolder;
        }
        return null;
    }

    public void updateAllData(List<AppointmentInfo> appointmentInfoList){
        if(appointmentInfoList!=null) {
            Log.d("appointmentInfoListSize",String.valueOf(appointmentInfoList.size()));
            (this.appointmentInfoList).addAll(appointmentInfoList);
            HashSet hashSet = new HashSet();
            hashSet.addAll(appointmentInfoList);
            this.appointmentInfoList.removeAll(this.appointmentInfoList);
            this.appointmentInfoList.addAll(hashSet);
            notifyDataSetChanged();
        }
    }

    public interface ExpandClickListener{
        void acceptExpand();
        void publishExpand();
    }

    public void setExpandListener(final RecyclerView.ViewHolder holder){
        this.expandClickListener=new ExpandClickListener() {
            @Override
            public void acceptExpand() {
                AppointmentMeAcceptRecyclerViewHolder acceptHolder= (AppointmentMeAcceptRecyclerViewHolder) holder;
                expandView(acceptHolder.appointmentAcceptStu,acceptHolder.appointmentAcceptCardView,ValueAnimator.ofInt(0,230));
                if(acceptHolder.appointmentAcceptStu.getVisibility()==View.GONE){
                    acceptExpandAnimation=new RotateAnimation(0,180,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                }else {
                    acceptExpandAnimation=new RotateAnimation(180,0,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                }
                acceptExpandAnimation.setDuration(500);
                acceptHolder.appointmentAcceptExpand.setAnimation(acceptExpandAnimation);
                acceptExpandAnimation.start();

            }

            @Override
            public void publishExpand() {
                AppointmentMePublishRecyclerViewHolder publishViewholder= (AppointmentMePublishRecyclerViewHolder) holder;
                expandView(publishViewholder.appointmentPublishStu,publishViewholder.appointmentPublishCardView,ValueAnimator.ofInt(0,230));
                if(publishViewholder.appointmentPublishStu.getVisibility()==View.GONE){
                    publishExpandAnimation=new RotateAnimation(0,180, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                }else {
                    publishExpandAnimation=new RotateAnimation(180,0,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1f);
                }
                publishExpandAnimation.setDuration(500);
                publishViewholder.appointmentPulishExpand.setAnimation(publishExpandAnimation);
                publishExpandAnimation.start();
            }
        };
    }

    public void expandView(final View relativeLayoutView,final View cardView, ValueAnimator valueAnimator){
        relativeLayoutView.setVisibility(View.VISIBLE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value= (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams=relativeLayoutView.getLayoutParams();
                ViewGroup.LayoutParams cardViewParams=cardView.getLayoutParams();
                Log.d("cardHeight",String.valueOf(cardViewParams.height));
                Log.d("height", String.valueOf(layoutParams.height));
                Log.d("value",String.valueOf(value));
                layoutParams.height=value;
                relativeLayoutView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AppointmentMeAcceptRecyclerViewHolder acceptHolder = null;
        AppointmentMePublishRecyclerViewHolder publishHolder = null;
        if(type==0){
            acceptHolder= (AppointmentMeAcceptRecyclerViewHolder) holder;
            setExpandListener(acceptHolder);
            acceptHolder.appointmentMeAcceptType.setText(appointmentInfoList.get(position).getAppointmentTypeText());
            acceptHolder.appointmentMeAcceptTime.setText(appointmentInfoList.get(position).getAppointmentDate()+" "+appointmentInfoList.get(position).getAppointmentTime());
            acceptHolder.appointmentMeAcceptDetail.setText(appointmentInfoList.get(position).getAppointmentT());
            acceptHolder.appointmentMeAcceptNum.setText(String.valueOf(appointmentInfoList.get(position).getAppointmentStuInfoList().size()));
            acceptHolder.appointmentMeAcceptSetNum.setText(String.valueOf(appointmentInfoList.get(position).getAppointmentNum()));
            acceptHolder.appointmentMeAcceptLocation.setText(appointmentInfoList.get(position).getLocation());
        }else if(type==1){
            publishHolder=(AppointmentMePublishRecyclerViewHolder) holder;
            setExpandListener(publishHolder);
            publishHolder.appointmentMePublishType.setText(appointmentInfoList.get(position).getAppointmentTypeText());
            publishHolder.appointmentMePublishTime.setText(appointmentInfoList.get(position).getAppointmentDate()+" "+appointmentInfoList.get(position).getAppointmentTime());
            publishHolder.appointmentMePublishDetail.setText(appointmentInfoList.get(position).getAppointmentT());
            publishHolder.appointmentMePublishNum.setText(String.valueOf(appointmentInfoList.get(position).getAppointmentStuInfoList().size()));
            publishHolder.appointmentMePublishSetNum.setText(String.valueOf(appointmentInfoList.get(position).getAppointmentNum()));
            publishHolder.appointmentMePublishLocation.setText(appointmentInfoList.get(position).getLocation());
        }
        if(type==0){
            switch (appointmentInfoList.get(position).getAppointmentNum()) {
                case 1:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                    }
                    break;
                case 2:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                    }
                    break;
                case 3:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                    }
                    break;
                case 4:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                    }
                    break;
                case 5:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                    }
                    break;
                case 6:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.GONE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                        case 6:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            acceptHolder.appointmentMeAcceptNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            break;
                    }
                    break;
                case 7:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                        case 6:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            acceptHolder.appointmentMeAcceptNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            break;
                        case 7:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            acceptHolder.appointmentMeAcceptNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            acceptHolder.appointmentMeAcceptNickname7.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(6).getNickName());
                            break;
                    }
                    break;
                case 8:
                    acceptHolder.appointmentMeAcceptLayout1.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout2.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout3.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout4.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout5.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout6.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout7.setVisibility(View.VISIBLE);
                    acceptHolder.appointmentMeAcceptLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                        case 6:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            acceptHolder.appointmentMeAcceptNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            break;
                        case 7:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            acceptHolder.appointmentMeAcceptNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            acceptHolder.appointmentMeAcceptNickname7.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(6).getNickName());
                            break;
                        case 8:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            acceptHolder.appointmentMeAcceptNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            acceptHolder.appointmentMeAcceptNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            acceptHolder.appointmentMeAcceptNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            acceptHolder.appointmentMeAcceptNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            acceptHolder.appointmentMeAcceptNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            acceptHolder.appointmentMeAcceptNickname7.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(6).getNickName());
                            acceptHolder.appointmentMeAcceptNickname8.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(7).getNickName());
                            break;
                    }break;
            }
        }else if(type==1){
            switch (appointmentInfoList.get(position).getAppointmentNum()) {
                case 1:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            acceptHolder.appointmentMeAcceptNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                    }
                    break;
                case 2:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                    }
                    break;
                case 3:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                    }
                    break;
                case 4:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                    }
                    break;
                case 5:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                    }
                    break;
                case 6:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.GONE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                        case 6:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            publishHolder.appointmentMePublishNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            break;
                    }
                    break;
                case 7:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                        case 6:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            publishHolder.appointmentMePublishNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            break;
                        case 7:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            publishHolder.appointmentMePublishNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            publishHolder.appointmentMePublishNickname7.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(6).getNickName());
                            break;
                    }
                    break;
                case 8:
                    publishHolder.appointmentMePublishLayout1.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout2.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout3.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout4.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout5.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout6.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout7.setVisibility(View.VISIBLE);
                    publishHolder.appointmentMePublishLayout8.setVisibility(View.GONE);
                    switch (appointmentInfoList.get(position).getAppointmentStuInfoList().size()) {
                        case 1:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            break;
                        case 2:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            break;
                        case 3:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            break;
                        case 4:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            break;
                        case 5:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            break;
                        case 6:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            publishHolder.appointmentMePublishNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            break;
                        case 7:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            publishHolder.appointmentMePublishNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            publishHolder.appointmentMePublishNickname7.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(6).getNickName());
                            break;
                        case 8:
                            publishHolder.appointmentMePublishNickname1.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(0).getNickName());
                            publishHolder.appointmentMePublishNickname2.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(1).getNickName());
                            publishHolder.appointmentMePublishNickname3.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(2).getNickName());
                            publishHolder.appointmentMePublishNickname4.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(3).getNickName());
                            publishHolder.appointmentMePublishNickname5.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(4).getNickName());
                            publishHolder.appointmentMePublishNickname6.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(5).getNickName());
                            publishHolder.appointmentMePublishNickname7.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(6).getNickName());
                            publishHolder.appointmentMePublishNickname8.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().get(7).getNickName());
                            break;
                    }break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return appointmentInfoList.size();
    }

    class AppointmentMeAcceptRecyclerViewHolder extends RecyclerView.ViewHolder{
        LinearLayout appointmentAcceptStu;
        ImageView appointmentAcceptExpand;
        CardView appointmentAcceptCardView;
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
            appointmentAcceptStu=itemView.findViewById(R.id.appointment_accept_stu);
            appointmentAcceptExpand=itemView.findViewById(R.id.appointment_stu_accept_expand);
            appointmentAcceptCardView=itemView.findViewById(R.id.appointment_accept_cardview);
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
            appointmentMeAcceptNickname1.setSelected(true);
            appointmentMeAcceptNickname2.setSelected(true);
            appointmentMeAcceptNickname3.setSelected(true);
            appointmentMeAcceptNickname4.setSelected(true);
            appointmentMeAcceptNickname5.setSelected(true);
            appointmentMeAcceptNickname6.setSelected(true);
            appointmentMeAcceptNickname7.setSelected(true);
            appointmentMeAcceptNickname8.setSelected(true);
            appointmentAcceptExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandClickListener.acceptExpand();
                }
            });
        }

    }

    class AppointmentMePublishRecyclerViewHolder extends RecyclerView.ViewHolder{
        LinearLayout appointmentPublishStu;
        ImageView appointmentPulishExpand;
        TextView appointmentMePublishType;
        TextView appointmentMePublishDetail;
        TextView appointmentMePublishLocation;
        TextView appointmentMePublishNum;
        TextView appointmentMePublishSetNum;
        TextView appointmentMePublishTime;
        Button appointmentMePublishStartChat;
        CardView appointmentPublishCardView;
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
            appointmentPublishStu=itemView.findViewById(R.id.appointment_publish_stu);
            appointmentPulishExpand=itemView.findViewById(R.id.appointment_stu_publish_expand);
            appointmentPublishCardView=itemView.findViewById(R.id.appointment_publish_cardview);
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
            appointmentMePublishNickname1.setSelected(true);
            appointmentMePublishNickname2.setSelected(true);
            appointmentMePublishNickname3.setSelected(true);
            appointmentMePublishNickname4.setSelected(true);
            appointmentMePublishNickname5.setSelected(true);
            appointmentMePublishNickname6.setSelected(true);
            appointmentMePublishNickname7.setSelected(true);
            appointmentMePublishNickname8.setSelected(true);
            appointmentPulishExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandClickListener.publishExpand();
                }
            });

        }
    }

}
