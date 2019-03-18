package cn.edu.uestc.meet_on_the_road_of_uestc.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;

public class StuInfoListConverter implements PropertyConverter<List<StuInfo>,String> {
    @Override
    public List<StuInfo> convertToEntityProperty(String databaseValue) {
        if(databaseValue==null) {
            return null;
        }else{
            JsonParser jsonParser=new JsonParser();
            JsonArray jsonElements=jsonParser.parse(databaseValue).getAsJsonArray();
            Gson gson=new Gson();
            List<StuInfo> stuInfoList=new ArrayList<>();
            for(JsonElement jsonElement:jsonElements){
                StuInfo stuInfo=gson.fromJson(jsonElement,StuInfo.class);
                Log.d("stuInfoConverter",stuInfo.getStuID());
                stuInfoList.add(stuInfo);
            }
            return stuInfoList;
        }
    }

    @Override
    public String convertToDatabaseValue(List<StuInfo> entityProperty) {
        Gson gson=new Gson();
        return gson.toJson(entityProperty);
    }
}
