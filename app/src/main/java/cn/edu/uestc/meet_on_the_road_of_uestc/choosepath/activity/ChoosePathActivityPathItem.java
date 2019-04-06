package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.Path;
import cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.adapter.ChoosePathAdapter;

import static dev.utils.app.ClipboardUtils.getIntent;

public class ChoosePathActivityPathItem  extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private List<Path> mParhList;
    private double latitude;
    private double longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepath_path_manage);
        ImageView choosePathButton=(ImageView) this.findViewById(R.id.choosethispath1);
        recyclerView=(RecyclerView)findViewById(R.id.choosepath_path_item_listview);
        final ChoosePathAdapter choosePathAdapter = new ChoosePathAdapter(getIntent().getDoubleExtra("Latitude",30.7498393974),getIntent().getDoubleExtra("Longtitude",103.9280676842));
       
        Log.e("====================","===================================="+choosePathAdapter);
        Log.e("====================","===================================="+getIntent().getDoubleExtra("Latitude",30.7498393974));
        Log.e("====================","===================================="+getIntent().getDoubleExtra("Longtitude",103.9280676842));

        recyclerView.setAdapter(choosePathAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
        choosePathAdapter.imageViewSetOnclick(new ChoosePathAdapter.ImageViewInterface() {
            @Override
            public void onclick(View view, int position) {
                mParhList=choosePathAdapter.getmParhList();
                Intent intent=new Intent(ChoosePathActivityPathItem.this,NaviActivity.class);
                intent.putExtra("latitude",mParhList.get(position).getPathLatitude());
                intent.putExtra("longtitude",mParhList.get(position).getPathLongtitude());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, final int position) {
                new AlertDialog.Builder(ChoosePathActivityPathItem.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choosePathAdapter.notifyItemRemoved(position);
                            }

                        })
                .show();
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
