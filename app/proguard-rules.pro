# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#3D 地图 V5.0.0之后：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.**{*;}
-keep   class com.amap.api.trace.**{*;}

#定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#搜索
-keep   class com.amap.api.services.**{*;}
#导航
-keep class com.amap.api.navi.**{*;}
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-dontwarn org.codehaus.**
# Gson specific classes
-keep class sun.misc.Unsafe {*;}
-keep class com.google.gson.stream.** {*;}
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** {*;}
-keep class com.google.gson.** {*;}
#这句非常重要，主要是滤掉自己写的bean包下的所有.class文件不进行混淆编译 
#对Gson解析bean类，进行保护。
-keep class cn.edu.uestc.meet_on_the_road_of_uestc.help.bean.** {*;}
-keep class cn.edu.uestc.meet_on_the_road_of_uestc.login.bean.** {*;}
-keep class cn.edu.uestc.meet_on_the_road_of_uestc.entity.** {*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }
 -keep class android.support.**{*;}
-keepattributes InnerClasses,Signature
-keepattributes *Annotation*
-keep class cn.addapp.pickers.entity.** { *;}
#grennDao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**
# 极光服务
-keep public class cn.jiguang.analytics.android.api.** {
   *;
}
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
