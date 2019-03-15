package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public class AppointmentAllRecyclerAdapter extends RecyclerView.Adapter<AppointmentAllRecyclerAdapter.AppointmentAllRecyclerViewHolder> {
    List<AppointmentInfo> appointmentInfoList;
    Context context;
    View view;
    AppointmentAllRecyclerViewHolder appointmentAllRecyclerViewHolder;
    JoinAppointmentButtonClickListener joinAppointmentButtonClickListener;
    public AppointmentAllRecyclerAdapter(Context context, List<AppointmentInfo> appointmentInfoList){
        this.context=context;
        this.appointmentInfoList=appointmentInfoList;
    }
    @NonNull
    @Override
    public AppointmentAllRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.appointment_me_recyclerview_layout,parent,false);
            appointmentAllRecyclerViewHolder=new AppointmentAllRecyclerViewHolder(view);
            view.setTag(appointmentAllRecyclerViewHolder);
        }else {
            appointmentAllRecyclerViewHolder=(AppointmentAllRecyclerViewHolder) view.getTag();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAllRecyclerViewHolder holder, int position) {
        holder.joinAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinAppointmentButtonClickListener.joinAppointmentButtonClickListener();
            }
        });
        holder.appointmentTitle.setText(appointmentInfoList.get(position).getAppointmentT());
        holder.appointmentTime.setText(appointmentInfoList.get(position).getAppointmentTime());
        holder.appointmentPeopleAcceptNum.setText(appointmentInfoList.get(position).getAppointmentStuInfoList().size());
        holder.appointmentPeopleSetNum.setText(appointmentInfoList.get(position).getAppointmentNum());
        holder.appointmentLocation.setText(appointmentInfoList.get(position).getLocation());
        holder.appointmentTypeText.setText(appointmentInfoList.get(position).getAppointmentTypeText());
    }

    @Override
    public int getItemCount() {
        return appointmentInfoList.size();
    }

    public void setJoinAppointmentClick(JoinAppointmentButtonClickListener joinAppointmentClick){
        this.joinAppointmentButtonClickListener=joinAppointmentClick;
    }
    class AppointmentAllRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView appointmentTitle;
        TextView appointmentLocation;
        TextView appointmentPeopleAcceptNum;
        TextView appointmentTime;
        Button joinAppointmentButton;
        TextView appointmentTypeText;
        TextView appointmentPeopleSetNum;
        public AppointmentAllRecyclerViewHolder(View itemView) {
            super(itemView);
            appointmentTitle=itemView.findViewById(R.id.appointment_title);
            appointmentLocation=itemView.findViewById(R.id.appointment_location);
            appointmentPeopleAcceptNum=itemView.findViewById(R.id.appointment_people_accept_num);
            appointmentPeopleSetNum=itemView.findViewById(R.id.appointment_people_set_num);
            appointmentTime=itemView.findViewById(R.id.appointment_time);
            joinAppointmentButton=itemView.findViewById(R.id.appointment_join);
            appointmentTypeText=itemView.findViewById(R.id.appointment_type);
        }
    }
    public void updateAppointmentList(List<AppointmentInfo> appointmentInfoList){
        this.appointmentInfoList=appointmentInfoList;
        notifyDataSetChanged();
    }
    interface JoinAppointmentButtonClickListener{
        void joinAppointmentButtonClickListener();
    }
}