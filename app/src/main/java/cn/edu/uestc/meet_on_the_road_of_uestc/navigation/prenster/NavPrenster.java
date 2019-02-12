package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.prenster;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.service.RetrofitService;
import io.reactivex.Observable;
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
    @Override
    public String getStuId() {
        stuID=daoSession.loadAll(StuInfo.class).get(0).getStuID();
        return stuID;
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
}
