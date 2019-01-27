package cn.edu.uestc.meet_on_the_road_of_uestc.help.prenster;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.model.HelpModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrensterComl implements IPrenster{
    HelpInfo helpInfo;
    Call retrofitService;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    HelpModel helpModel=new HelpModel();
    @Override
    public void getData() {
        retrofitService=retrofitHelper.getRetrofitService(MyApplication.getMyContext()).getGoodsData();
        retrofitService.enqueue(new Callback<HelpInfo>() {
            @Override
            public void onResponse(Call<HelpInfo> call, Response<HelpInfo> response) {
                helpInfo=response.body();
                helpModel.saveGoodsData(helpInfo);
            }

            @Override
            public void onFailure(Call<HelpInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void postData(HelpInfo helpInfo) {
        retrofitService=retrofitHelper.getRetrofitService(MyApplication.getMyContext()).postGoodData(helpInfo);
        retrofitService.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
