package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageViewpagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageAcceptViewpagerActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManagePublishViewpagerActivity;

public class HelpManagePrenster implements IPrenster{
    Context context;
    public HelpManagePrenster(Context context){
        this.context=context;
    }
    @Override
    public HelpManageViewpagerAdapter initViewPagerAdapter() {
        List<View> helpManageViewpagerActivity=new ArrayList();
        helpManageViewpagerActivity.add(LayoutInflater.from(context).inflate(R.layout.activity_my_accept_help,null));
        helpManageViewpagerActivity.add(LayoutInflater.from(context).inflate(R.layout.activity_my_publish_help,null));
        List<String> viewpagerTitle=new ArrayList();
        viewpagerTitle.add("我接受的");
        viewpagerTitle.add("我发布的");
        HelpManageViewpagerAdapter helpManageViewpagerAdapter =new HelpManageViewpagerAdapter(helpManageViewpagerActivity,viewpagerTitle);
        return helpManageViewpagerAdapter;
    }
}
