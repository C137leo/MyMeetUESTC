package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.activity;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class GradientHelper {
    private static final String TAG = "GradientHelper";
    public int i= -1;
    public List<RGB> gradient=new ArrayList<>();

    int totalDistance;//渐变的范围
    int startColor;//渐变的起始颜色
    int endColor;//渐变的结束颜色

    public GradientHelper(int totalDistance, int startColor, int endColor) {
        this.totalDistance = totalDistance;
        this.startColor = startColor;
        this.endColor = endColor;
}
    /**
     * 获取当前渐变颜色
     * @return 当前颜色的RGB值
     */
    public int getGradient() {
        i++;
        int gradient_R ;
        int gradient_G ;
        int gradient_B ;
        int delta_R = Color.red(startColor) - Color.red(endColor);
        int delta_G = Color.green(startColor) - Color.green(endColor);
        int delta_B = Color.blue(startColor) - Color.blue(endColor);
        if (i <= totalDistance) {
            gradient_R = Color.red(endColor) + delta_R * i / totalDistance;
            gradient_G = Color.green(endColor)  + delta_G * i / totalDistance;
            gradient_B = Color.blue(endColor)  + delta_B * i / totalDistance;
            gradient.add(new RGB(gradient_R,gradient_G,gradient_B));
        } else if (i <= totalDistance*2 +1) {
            RGB rgb_1 = gradient.get(gradient.size() - i % (totalDistance + 1) -1);
            gradient_R = rgb_1.gradient_R;
            gradient_G = rgb_1.gradient_G;
            gradient_B = rgb_1.gradient_B;
        } else {
            RGB rgb_2 = gradient.get(0);
            gradient_R = rgb_2.gradient_R;
            gradient_G = rgb_2.gradient_R;
            gradient_B = rgb_2.gradient_R;
            gradient.clear();
            i = -1;
        }
        return Color.argb(255,gradient_R,gradient_G,gradient_B);
    }

    class RGB{
        int gradient_R ;
        int gradient_G ;
        int gradient_B ;
        public RGB(int gradient_R, int gradient_G, int gradient_B) {
            this.gradient_R = gradient_R;
            this.gradient_G = gradient_G;
            this.gradient_B = gradient_B;
        }
    }

}
