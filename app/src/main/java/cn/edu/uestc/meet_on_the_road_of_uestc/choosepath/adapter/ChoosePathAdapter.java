package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.Path;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.activity.ChoosePathActivityPathItem;

import static dev.utils.app.ClipboardUtils.getIntent;


public class ChoosePathAdapter extends RecyclerView.Adapter<ChoosePathAdapter.pathViewHolder> {

    private List<Path> mParhList;
    private ImageViewInterface imageViewInterface;
    private double latitudeNow;
    private double longtitudeNow;
    private List distance;
    public double getLatitudeNow() {
        return latitudeNow;
    }

    public void setLatitudeNow(double latitudeNow) {
        this.latitudeNow = latitudeNow;
    }

    public double getLongtitudeNow() {
        return longtitudeNow;
    }

    public void setLongtitudeNow(double longtitudeNow) {
        this.longtitudeNow = longtitudeNow;
    }

    public List<Path> getmParhList() {
        return mParhList;
    }

    public void imageViewSetOnclick(ImageViewInterface imageViewInterface){
        this.imageViewInterface=imageViewInterface;
    }
    public interface ImageViewInterface{
        public void onclick( View view,int position);
    }
   static class pathViewHolder extends RecyclerView.ViewHolder {
        //        ImageView pathImage;
        TextView pathName ;
        TextView pathId ;
        TextView lastRunDate ;
        TextView distanceText;
        ImageView imageView;
        public pathViewHolder(View itemView) {
            super(itemView);

//          pathImage=(ImageView)itemView.findViewById(R.id.path_image);
            pathName = (TextView) itemView.findViewById(R.id.path_name);
            imageView=(ImageView)itemView.findViewById(R.id.choosethispath1);
            distanceText=(TextView) itemView.findViewById(R.id.distance);
            lastRunDate=(TextView) itemView.findViewById(R.id.lastRunDate);

        }
    }

    public ChoosePathAdapter(double latitudeNow,double longtitudeNow) {
        super();
        this.latitudeNow=latitudeNow;
        this.longtitudeNow=longtitudeNow;
        mParhList=new ArrayList<>();
        //下面的数字10代表着路径数量
        mParhList.add(new Path("环校南门起点",1,30.7441350000,103.9252640000));
        mParhList.add(new Path("东湖环湖",1,30.7482890000,103.9305910000));
        mParhList.add(new Path("操场",1,30.7491420000,103.9367010000));
        for (int i=3;i<10;i++){
                    mParhList.add(new Path("跑完感觉腿粗的路线" +i,   i,30.75533739247437,  103.93463802298358));
            }
         distance=new ArrayList();
        Log.e("=====","============="+latitudeNow+"======"+longtitudeNow);
        Log.e("=====","============="+getClass().hashCode());
        distance.add(AMapUtils.calculateLineDistance(new LatLng(latitudeNow,longtitudeNow), new LatLng(30.7441350000,103.9252640000)));
        distance.add(AMapUtils.calculateLineDistance(new LatLng(latitudeNow,longtitudeNow), new LatLng(30.7482890000,103.9305910000)));
        distance.add(AMapUtils.calculateLineDistance(new LatLng(latitudeNow,longtitudeNow), new LatLng(30.7491420000,103.9367010000)));
        for (int i=3;i<10;i++){
            distance.add(AMapUtils.calculateLineDistance(new LatLng(latitudeNow,longtitudeNow), new LatLng(30.75533739247437,  103.93463802298358)));
        }
        }

    @NonNull
    @Override
    public pathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_choosepath_path_item_listview, parent, false);
        return new pathViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull pathViewHolder holder, final int position) {
        Path path=mParhList.get(position);
        holder.pathName.setText(path.getPathName());
        CharSequence sequence=String.valueOf("距离目的地还有"+Math.round((Float)distance.get(position))+"米");
        holder.distanceText.setText(sequence);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            if(imageViewInterface!=null){
               imageViewInterface.onclick(v,position);
            }

        }
        });
    }

    @Override
    public int getItemCount() {
        return mParhList.size();
    }
}



