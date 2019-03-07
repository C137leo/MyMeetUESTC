package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.prenster;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.service.RetrofitService;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.view.IVew;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NavPrenster implements IPrenster{
    StuInfo stuInfo;
    DaoSession daoSession=GreenDaoHelper.getDaoSession();
    private String stuID;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance();
    Disposable disposable;
    IVew iVew;
    @Override
    public String getStuId() {
        stuID=daoSession.loadAll(StuInfo.class).get(0).getStuID();
        return stuID;
    }

    @Override
    public void attchView(IVew iVew) {
        this.iVew=iVew;
    }

    @Override
    public StuInfo searchStuInfo(String StuID) {
        final RetrofitService retrofitService=retrofitHelper.getRetrofitService();
        Observable<StuInfo> getStuInfo=retrofitService.getStuInfo(StuID);
        getStuInfo.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StuInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        stuInfo=daoSession.getStuInfoDao().loadAll().get(0);
                    }

                    @Override
                    public void onNext(StuInfo stuInfo) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return stuInfo;
    }

    @Override
    public void startChat(final String stuID) {
        Observable.create(new ObservableOnSubscribe<List<ChatMessage>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ChatMessage>> emitter) throws Exception {
                Conversation conversation = JMessageClient.getSingleConversation(stuID, MyApplication.getJiguangAppkey());
                if ((conversation.getAllMessage()).isEmpty()) {
                    Log.d("userName", stuID);
                    Conversation.createSingleConversation(stuID, MyApplication.getJiguangAppkey());
                } else {
                    List<ChatMessage> chatMessages = new ArrayList<>();
                    for (Message message : conversation.getAllMessage()) {
                        if (message.getDirect() == MessageDirect.send) {
                            Log.d("conversation", conversation.getId());
                            if (message.getContentType() == ContentType.text) {
                                TextContent textContent = (TextContent) message.getContent();
                                ChatMessage chatMessage = new ChatMessage(textContent.getText(), 1);
                                chatMessages.add(chatMessage);
                            }
                        } else if (message.getDirect() == MessageDirect.receive) {
                            if (message.getContentType() == ContentType.text) {
                                TextContent textContent = (TextContent) message.getContent();
                                ChatMessage chatMessage = new ChatMessage(textContent.getText(), 0);
                                chatMessages.add(chatMessage);
                            }
                        }
                    }
                    emitter.onNext(chatMessages);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ChatMessage>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<ChatMessage> chatMessages) {
                        Log.d("accept", String.valueOf(chatMessages.size()));
                        iVew.startChatSuccessful();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }
}
