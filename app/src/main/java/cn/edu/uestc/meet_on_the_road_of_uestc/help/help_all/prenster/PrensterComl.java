package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster;

import android.content.Context;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.model.HelpModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrensterComl implements IPrenster{
    HelpInfo helpInfo;
    Call retrofitService;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    HelpModel helpModel=new HelpModel();
    private Context context;
    public PrensterComl(Context context){
        this.context=context;
    }
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
}
