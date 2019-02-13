package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewAcceptAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewPublishAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageViewpagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.AcceptRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageAcceptViewpagerFragment;

public interface IPrenster {

    HelpManageViewpagerAdapter initViewPagerAdapter();
    HelpManageListViewPublishAdapter initHelpManageListViewPublishAdapter();
    void getListViewDataPublish();
    HelpManageListViewAcceptAdapter initHelpManageListViewAcceptAdapter();
    void getRecycleAcceptData();
}
