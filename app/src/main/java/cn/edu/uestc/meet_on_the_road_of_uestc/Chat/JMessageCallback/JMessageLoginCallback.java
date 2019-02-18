package cn.edu.uestc.meet_on_the_road_of_uestc.Chat.JMessageCallback;

import cn.jpush.im.api.BasicCallback;

public class JMessageLoginCallback extends BasicCallback {
    public JMessageLoginCallback(boolean b) {
        super(b);
    }

    @Override
    public boolean isRunInUIThread() {
        return super.isRunInUIThread();
    }

    @Override
    public void gotResult(int i, String s, Object... objects) {
        super.gotResult(i, s, objects);
    }

    @Override
    public void gotResult(int i, String s) {

    }

    public JMessageLoginCallback() {
        super();
    }
}
