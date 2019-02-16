package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.model;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpStatusUpdateToIsProcessing;

public interface IModel {
    void updateStatusInDatabases(String UID, HelpStatusUpdateToIsProcessing helpStatusUpdateToIsProcessing);
}
