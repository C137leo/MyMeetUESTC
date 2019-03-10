package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.Path;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.adapter.ChoosePathAdapter;

public class ChoosePathActivityPathItem  extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private List<Path> mParhList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepath_path_manage);
        ImageView choosePathButton=(ImageView) this.findViewById(R.id.choosethispath1);
        recyclerView=(RecyclerView)findViewById(R.id.choosepath_path_item_listview);
        ChoosePathAdapter choosePathAdapter = new ChoosePathAdapter();
        recyclerView.setAdapter(choosePathAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
        choosePathAdapter.imageViewSetOnclick(new ChoosePathAdapter.ImageViewInterface() {
            @Override
            public void onclick(View view, int position) {
                Intent intent=new Intent(ChoosePathActivityPathItem.this,NaviActivity.class);
                startActivity(intent);
            }
        });
        //原来的intent跳转方法
//        choosePathButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    Intent intent=new Intent(ChoosePathActivityPathItem.this,ChoosePathActivity.class);
//                    startActivity(intent);
//            }
//        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    private void initEvent() {
//
//    }

}
