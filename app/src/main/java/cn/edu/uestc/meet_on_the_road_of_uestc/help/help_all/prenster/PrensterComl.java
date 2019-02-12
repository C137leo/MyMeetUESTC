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
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.model.HelpModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PrensterComl implements IPrenster{
    Observable<ResponseBody> observable;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    HelpModel helpModel=new HelpModel();
    private Context context;
    IView iView;
    JsonParser jsonParser=new JsonParser();
    JsonArray jsonArray;
    Gson gson=new Gson();
    Disposable disposable;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
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
                        List<HelpInfo> helpInfoList=new ArrayList<>();
                        Log.d("getResponse","getResponse");
                        try {
                            jsonArray=jsonParser.parse(responseBody.string()).getAsJsonArray();
                        } catch (IOException e) {
                            e.printStackTrace();
                            iView.hideRefershing();
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                            iView.hideRefershing();
                        }
                        if(jsonArray==null){
                            iView.hideRefershing();
                        }else {
                            for (JsonElement jsonElement : jsonArray) {
                                HelpInfo helpInfo = gson.fromJson(jsonElement, HelpInfo.class);
                                helpInfoList.add(helpInfo);
                                helpModel.saveGoodsData(helpInfo);
                            }
                        }
                        getDataFromDatabases();
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

    @Override
    public void getDataFromDatabases() {
        List<HelpInfo> helpInfos=new ArrayList<>();
        helpInfos= daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.IsFinish.eq(0)).list();
        Log.d("size", String.valueOf(helpInfos.size()));
        for(HelpInfo helpInfo:daoSession.getHelpInfoDao().loadAll()){
            Log.d("isFinish",String.valueOf(helpInfo.getIsFinish()));
        }
        Log.d("helpInfosSize", String.valueOf(helpInfos.size()));
        iView.updateData(helpInfos);
    }
}
