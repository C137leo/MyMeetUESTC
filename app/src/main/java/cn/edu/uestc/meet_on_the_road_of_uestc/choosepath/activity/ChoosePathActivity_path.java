package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.Path;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.adapter.ChoosePathAdapter;
public class ChoosePathActivity_path extends AppCompatActivity {
    private List<Path> pathList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepath_path);
        initPath();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.path_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ChoosePathAdapter adapter=new ChoosePathAdapter(pathList);
        recyclerView.setAdapter(adapter);

    }
    private  void initPath(){
        for(int i=0;i<2;i++){
            Path path1=new Path("跑完腿粗", R.mipmap.back.png);
            pathList.add(path1);
            Path path2=new Path("跑完腿粗2",R.mipmap.back.png);
            pathList.add(path2);
            Path path3=new Path("跑完腿粗3",R.mipmap.back.png);
            pathList.add(path3);
            Path path4=new Path("跑完腿粗4",R.mipmap.back.png);
            pathList.add(path4);
            Path path5=new Path("跑完腿粗5",R.mipmap.back.png);
            pathList.add(path5);
            Path path6=new Path("跑完腿粗6",R.mipmap.back.png);
            pathList.add(path6);
            Path path7=new Path("跑完腿粗7",R.mipmap.back.png);
            pathList.add(path7);
            Path path8=new Path("跑完腿粗8",R.mipmap.back.png);
            pathList.add(path8);




        }
    }
}
