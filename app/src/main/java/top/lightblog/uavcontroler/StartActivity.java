package top.lightblog.uavcontroler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import top.lightblog.helper.AnimationCode;
import top.lightblog.helper.Screen;
import top.lightblog.helper.StatusCode;

public class StartActivity extends AppCompatActivity {

    private ImageView imgBody;   //无人机身

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Screen.setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //点击无人机，不播放动画直接进入控制台
        imgBody = (ImageView) findViewById(R.id.img_body);
        imgBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartActivity.this, ControlActivity.class);
                startActivity(i);
                StatusCode.jumpStatus = true;
                finish();
            }
        });

        AnimationCode animationCode = new AnimationCode();
        animationCode.animator_UAV(this);
        animationCode.launchController(this);
    }
}
