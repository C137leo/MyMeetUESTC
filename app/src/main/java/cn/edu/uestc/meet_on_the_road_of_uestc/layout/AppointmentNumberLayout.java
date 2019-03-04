package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentNumberLayout extends LinearLayout implements View.OnClickListener {
    ImageView person1;
    ImageView person2;
    ImageView person3;
    ImageView person4;
    ImageView person5;
    ImageView person6;
    ImageView person7;
    ImageView person8;
    int number;
    public AppointmentNumberLayout(Context context) {
        super(context);
        initView(context);
    }

    public AppointmentNumberLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.appointment_number_layout,this,true);
        person1=findViewById(R.id.person1);
        person2=findViewById(R.id.person2);
        person3=findViewById(R.id.person3);
        person4=findViewById(R.id.person4);
        person5=findViewById(R.id.person5);
        person6=findViewById(R.id.person6);
        person7=findViewById(R.id.person7);
        person8=findViewById(R.id.person8);
        person8.setOnClickListener(this);
        person7.setOnClickListener(this);
        person6.setOnClickListener(this);
        person5.setOnClickListener(this);
        person4.setOnClickListener(this);
        person3.setOnClickListener(this);
        person2.setOnClickListener(this);
        person1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.person8:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                number=8;
                break;
            case R.id.person7:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                number=7;
                break;
            case R.id.person6:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                number=6;
                break;
            case R.id.person5:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                number=5;
                break;
            case R.id.person4:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                number=4;
                break;
            case R.id.person3:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                number=3;
                break;
            case R.id.person2:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                number=2;
                break;
            case R.id.person1:
                person1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_selected));
                person8.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person7.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person6.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person5.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person4.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person3.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                person2.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
                number=1;
                break;
        }
    }

    public int getNumber() {
        return number;
    }
}
