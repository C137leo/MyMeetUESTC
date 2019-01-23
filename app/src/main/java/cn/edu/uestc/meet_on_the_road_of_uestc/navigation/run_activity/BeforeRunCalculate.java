package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.run_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableElementAt;
import io.reactivex.schedulers.Schedulers;

public class BeforeRunCalculate extends AppCompatActivity {
    TextView calculateTime;
    int showTime=3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_time);
        calculateTime=findViewById(R.id.calculateTime);
        calculateTime();
    }
    public void calculateTime(){
        Observable.interval(0,1, TimeUnit.SECONDS)
                .take(4)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if(showTime==0){
                            calculateTime.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                            calculateTime.setText("跑步开始");
                        }else {
                            calculateTime.setText(String.valueOf(showTime));
                            showTime--;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Intent intent=new Intent(BeforeRunCalculate.this,RunningActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
