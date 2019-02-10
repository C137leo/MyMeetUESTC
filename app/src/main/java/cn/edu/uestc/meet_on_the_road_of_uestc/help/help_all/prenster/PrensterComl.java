package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.model.HelpModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import dev.utils.common.AssistUtils;
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
    Observable<ResponseBody> observable;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    HelpModel helpModel=new HelpModel();
    private Context context;
    List<cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo> helpInfoList=new ArrayList<>();
    IView iView;
    JsonParser jsonParser=new JsonParser();
    JsonArray jsonArray;
    Gson gson=new Gson();
    Disposable disposable;
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
                        disposable=d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.d("getResponse","getResponse");
                        List<HelpInfo> helpInfos=new ArrayList<>();
                        try {
                            jsonArray=jsonParser.parse(responseBody.string()).getAsJsonArray();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for(JsonElement jsonElement:jsonArray){
                            HelpInfo helpInfo=gson.fromJson(jsonElement,HelpInfo.class);
                            helpInfos.add(helpInfo);
                            helpModel.saveGoodsData(helpInfo);
                        }
                        iView.updateData(helpInfos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        iView.hideRefershing();
                        disposable.dispose();
                    }
                });
    }
}
