package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.AppointmentFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.HelpFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.home.HomeFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.MeFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.NavFragment;

/**
 * Happy New Year!
 * Authored by ray
 * 2019.1.1 00:00
 */
public class MainActivity extends CheckPermissionsActivity{
    //获取管理类
    FragmentManager mFragmentManager=getSupportFragmentManager();
    AppointmentFragment appointmentFragment=new AppointmentFragment();
    HelpFragment helpFragment=new HelpFragment();
    HomeFragment homeFragment=new HomeFragment();
    MeFragment meFragment=new MeFragment();
    NavFragment navFragment=new NavFragment();
    RelativeLayout mNav;
    RelativeLayout mHome;
    RelativeLayout mHelp;
    RelativeLayout mAppointment;
    RelativeLayout mMe;
    onClickListener monClickListener=new onClickListener();
    Intent serviceIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        serviceIntent = new Intent();   //强制前台运行
        serviceIntent.setClass(this,LocationForegoundService.class);
        setContentView(R.layout.activity_main);
        //添加事物 默认：首页
        FragmentTransaction mTransaction=mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.container_content,homeFragment);
        mTransaction.commit();  //提交
        initMenuListener();
    }

    //初始化底部菜单按钮监听器
    protected void initMenuListener(){
        mNav=findViewById(R.id.menu_nav);
        mHome=findViewById(R.id.menu_home);
        mHelp=findViewById(R.id.menu_help);
        mAppointment=findViewById(R.id.menu_appointment);
        mMe=findViewById(R.id.menu_me);
        mNav.setOnClickListener(monClickListener);
        mHome.setOnClickListener(monClickListener);
        mHelp.setOnClickListener(monClickListener);
        mAppointment.setOnClickListener(monClickListener);
        mMe.setOnClickListener(monClickListener);
    }
    //匿名内部类重写onClickListener方法
    class onClickListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.menu_nav:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,navFragment)
                            .commit();
                    break;
                case R.id.menu_home:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,homeFragment)
                            .commit();
                    break;
                case R.id.menu_help:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,helpFragment)
                            .commit();
                    break;
                case R.id.menu_appointment:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,appointmentFragment)
                            .commit();
                    break;
                case R.id.menu_me:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,meFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        }
    }
}
