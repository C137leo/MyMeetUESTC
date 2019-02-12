package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.Path;

public class ChoosePathAdapter extends RecyclerView.Adapter<ChoosePathAdapter.ViewHolder> {
    private List<Path> mParhList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView pathImage;
        TextView pathName;

        public ViewHolder(View itemView) {
            super(itemView);
            pathImage=(ImageView)itemView.findViewById(R.id.path_image);
            pathName=(TextView)itemView.findViewById(R.id.path_name);

        }
    }
    public ChoosePathAdapter(List<Path> pathList){
        mParhList=pathList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.path,parent,false);
            ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Path path=mParhList.get(position);
            holder.pathImage.setImageResource(path.getImageId());
            holder.pathName.setText(path.getName());
    }


    @Override
    public int getItemCount() {
        return mParhList.size();
    }


}
