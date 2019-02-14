package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.Path;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewPublishAdapter;

public class ChoosePathAdapter extends RecyclerView.Adapter<ChoosePathAdapter.pathViewHolder> {

    private List<Path> mParhList;
    private ImageViewInterface imageViewInterface;

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
        TextView last ;
       ImageView imageView;
        public pathViewHolder(View itemView) {
            super(itemView);

//          pathImage=(ImageView)itemView.findViewById(R.id.path_image);
            pathName = (TextView) itemView.findViewById(R.id.path_name);
            imageView=(ImageView)itemView.findViewById(R.id.choosethispath1);

        }
    }

    public ChoosePathAdapter() {
        super();
       mParhList=new ArrayList<>();
        //下面的数字10代表着路径数量
        for (int i=0;i<10;i++){
                    mParhList.add(new Path("跑完感觉腿粗的路线" +i,   i));
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



