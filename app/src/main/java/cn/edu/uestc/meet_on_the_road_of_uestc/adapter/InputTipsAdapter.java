package cn.edu.uestc.meet_on_the_road_of_uestc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.help.Tip;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class InputTipsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tip> mTipList;
    public InputTipsAdapter(Context context, List<Tip> tipList){
        mContext=context;
        mTipList=tipList;
    }
    @Override
    public int getCount() {
        return mTipList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null) {
            holder=new Holder();
            convertView = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_inputtips, null);
            convertView.setTag(holder);
        }else{
            holder=(Holder)convertView.getTag();
        }
        holder.mName=convertView.findViewById(R.id.name);
        holder.mAddress=convertView.findViewById(R.id.address);
        holder.mName.setText(mTipList.get(position).getName());
        holder.mAddress.setText(mTipList.get(position).getAddress());
        Log.d("begin convertview","begin convertview");
        return convertView;
    }

    class Holder{
        TextView mName;
        TextView mAddress;
    }
}
