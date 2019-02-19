package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Tip;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class InputTipsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tip> mTipList;

    public String getTip_address(int position) {
        String tip_address=mTipList.get(position).getAddress();
        return tip_address;
    }

    public LatLng getTip_Latlng(int position){
        LatLonPoint tip_latlonpoint=mTipList.get(position).getPoint();
        LatLng tip_latlng=new LatLng(tip_latlonpoint.getLatitude(),tip_latlonpoint.getLongitude());
        return tip_latlng;
    }

    public String getTip_title(int position){
        String tip_title=mTipList.get(position).getName();
        return tip_title;
    }

    private LatLng tip_latlang;
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
            holder.mName=convertView.findViewById(R.id.name);
            holder.mAddress=convertView.findViewById(R.id.address);
        }else{
            holder=(Holder)convertView.getTag();
        }
        holder.mName.setText(mTipList.get(position).getName());
        holder.mAddress.setText(mTipList.get(position).getAddress());
        convertView.setTag(holder);
        return convertView;
    }

    class Holder{
        TextView mName;
        TextView mAddress;
    }
}
