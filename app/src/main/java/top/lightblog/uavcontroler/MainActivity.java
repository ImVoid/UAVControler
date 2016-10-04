package top.lightblog.uavcontroler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.lightblog.helper.AnimationCode;
import top.lightblog.helper.Screen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Screen.setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimationCode animationCode = new AnimationCode();
        animationCode.animator_UAV(this);
        animationCode.launchController(this);
    }
}
