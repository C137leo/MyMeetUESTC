package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.help.Tip;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class InputTipsListViewAdapter extends BaseAdapter {
    List<Tip> tipList;
    Context mContext;
    public InputTipsListViewAdapter(Context mContext, List<Tip> tipList){
        this.mContext=mContext;
        this.tipList=tipList;
    }
    @Override
    public int getCount() {
        return tipList.size();
    }

    @Override
    public Object getItem(int position) {
        return tipList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView==null){
            holder=new Holder();
            convertView= LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_add_help_inputtips,parent);
            convertView.setTag(holder);
        }else {
            holder=(Holder)convertView.getTag();
        }
        holder.add_help_inputtips=convertView.findViewById(R.id.add_help_inputtips);
        return convertView;
    }

    public void updateListData(List<Tip> tipList){
        this.tipList=tipList;
        notifyDataSetChanged();
    }
    class Holder{
        TextView add_help_inputtips;
    }
}
