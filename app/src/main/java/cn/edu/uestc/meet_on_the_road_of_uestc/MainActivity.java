package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.view.AppointmentFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.HelpAllFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.home.view.HomeFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.view.MeFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.view.NavFragment;

/**
 * Happy New Year!
 * Authored by ray
 * 2019.1.1 00:00
 */
public class MainActivity extends CheckPermissionsActivity{
    //获取管理类
    FragmentManager mFragmentManager=getSupportFragmentManager();
    AppointmentFragment appointmentFragment=new AppointmentFragment();
    HelpAllFragment helpAllFragment =new HelpAllFragment();
    HomeFragment homeFragment=new HomeFragment();
    MeFragment meFragment=new MeFragment();
    NavFragment navFragment=new NavFragment();
    LinearLayout mNav;
    LinearLayout mHome;
    LinearLayout mHelp;
    LinearLayout mAppointment;
    LinearLayout mMe;
    ImageView navigation;
    ImageView home;
    ImageView help;
    ImageView appointment;
    ImageView me;
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
        navigation=findViewById(R.id.navigation_drawable);
        home=findViewById(R.id.home_drawable);
        help=findViewById(R.id.help_drawable);
        appointment=findViewById(R.id.appointment_drawable);
        me=findViewById(R.id.me_drawable);
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
                    navigation.setImageResource(R.drawable.ic_navigation_click);
                    home.setImageResource(R.drawable.ic_home_bottombar);
                    help.setImageResource(R.drawable.ic_help_bottombar);
                    appointment.setImageResource(R.drawable.ic_appointment_bottombar);
                    me.setImageResource(R.drawable.ic_me_bottombar);
                    break;
                case R.id.menu_home:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,homeFragment)
                            .commit();
                    navigation.setImageResource(R.drawable.ic_navigation_bottombar);
                    home.setImageResource(R.drawable.ic_home_cilck);
                    help.setImageResource(R.drawable.ic_help_bottombar);
                    appointment.setImageResource(R.drawable.ic_appointment_bottombar);
                    me.setImageResource(R.drawable.ic_me_bottombar);
                    break;
                case R.id.menu_help:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content, helpAllFragment)
                            .commit();
                    navigation.setImageResource(R.drawable.ic_navigation_bottombar);
                    home.setImageResource(R.drawable.ic_home_bottombar);
                    help.setImageResource(R.drawable.ic_help_click);
                    appointment.setImageResource(R.drawable.ic_appointment_bottombar);
                    me.setImageResource(R.drawable.ic_me_bottombar);
                    break;
                case R.id.menu_appointment:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,appointmentFragment)
                            .commit();
                    navigation.setImageResource(R.drawable.ic_navigation_bottombar);
                    home.setImageResource(R.drawable.ic_home_bottombar);
                    help.setImageResource(R.drawable.ic_help_bottombar);
                    appointment.setImageResource(R.drawable.ic_appointment_click);
                    me.setImageResource(R.drawable.ic_me_bottombar);
                    break;
                case R.id.menu_me:
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container_content,meFragment)
                            .commit();
                    navigation.setImageResource(R.drawable.ic_navigation_bottombar);
                    home.setImageResource(R.drawable.ic_home_bottombar);
                    help.setImageResource(R.drawable.ic_help_bottombar);
                    appointment.setImageResource(R.drawable.ic_appointment_bottombar);
                    me.setImageResource(R.drawable.ic_me_click);
                    break;
                default:
                    break;
            }
        }
    }
}
