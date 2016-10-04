package top.lightblog.helper;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import top.lightblog.uavcontroler.R;

/**
 * Created by imliu on 2016/9/30.
 */

public class Screen {

    /**
     * 负责设置屏幕为全屏
     * @param activity  需要设置全屏的Activity
     */
    public static void setFullScreen(AppCompatActivity activity){

        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 全屏显示
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 禁止锁屏

        activity.setContentView(R.layout.activity_controller);

        activity.getSupportActionBar().hide(); //隐藏标题栏
    }
}
