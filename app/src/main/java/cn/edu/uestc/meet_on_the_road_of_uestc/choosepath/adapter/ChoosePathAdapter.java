package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.Path;


public class ChoosePathAdapter extends RecyclerView.Adapter<ChoosePathAdapter.pathViewHolder> {
    public List<Path> getmParhList() {
        return mParhList;
    }

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
        mParhList.add(new Path("环校南门起点",1,30.7441350000,103.9252640000));
        mParhList.add(new Path("东湖环湖",1,30.7482890000,103.9305910000));
        mParhList.add(new Path("操场",1,30.7491420000,103.9367010000));
        for (int i=3;i<10;i++){
                    mParhList.add(new Path("跑完感觉腿粗的路线" +i,   i,30.75533739247437,  103.93463802298358));
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



