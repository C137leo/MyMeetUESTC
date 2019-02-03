package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
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

    Observable<ResponseBody> observable;
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
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * 临时测试代码
                         **/
                        for(int i=0;i<2;i++){
                            helpInfo=new HelpInfo(1,String.valueOf(i),"ray","he","hello","hhh","hello");
                            helpInfoList.add(helpInfo);
                            helpModel.saveGoodsData(helpInfo);
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
