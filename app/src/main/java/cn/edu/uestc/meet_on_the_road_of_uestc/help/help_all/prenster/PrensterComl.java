package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.model.HelpModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrensterComl implements IPrenster{
    HelpInfo helpInfo;

    Observable<HelpInfo> observable;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    HelpModel helpModel=new HelpModel();
    private Context context;
    List<HelpInfo> helpInfoList=new ArrayList<>();
    IView iView;
    public PrensterComl(Context context){
        this.context=context;
    }

    @Override
    public void attchView(IView iView) {
        this.iView=iView;
    }

    @Override
    public void getData() {
        observable=retrofitHelper.getRetrofitService(MyApplication.getMyContext()).getGoodsData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HelpInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        /**
                         * 临时测试代码
                         **/
                        for(int i=0;i<2;i++){
                            helpInfo=new HelpInfo(Long.valueOf(100),"2018021407022","祈福名都","肖梓涵","你好","2018",1,"hhhhhhhhh");
                            helpInfoList.add(helpInfo);
                            cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo helpInfo1=new cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo(Long.valueOf(100),"2018021407022","祈福名都","肖梓涵","你好","2018",1,"hhhhhhhhh");
                            helpModel.saveGoodsData(helpInfo1);
                        }
                        iView.updateData(helpInfoList);
                    }

                    @Override
                    public void onNext(HelpInfo helpInfo) {
                        /**
                         * 临时测试代码
                         **/
                        for(int i=0;i<2;i++){
                            helpInfo=new HelpInfo(Long.valueOf(100),"2018021407022","祈福名都","肖梓涵","你好","2018",1,"hhhhhhhhh");
                            helpInfoList.add(helpInfo);
                            cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo helpInfo1=new cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo(Long.valueOf(100),"2018021407022","祈福名都","肖梓涵","你好","2018",1,"hhhhhhhhh");
                            helpModel.saveGoodsData(helpInfo1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        iView.updateData(helpInfoList);
                    }
                });
    }
}
