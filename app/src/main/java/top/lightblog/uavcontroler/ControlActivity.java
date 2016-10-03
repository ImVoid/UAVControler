package top.lightblog.uavcontroler;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import top.lightblog.asynctask.SwitchBlinkTask;
import top.lightblog.helper.*;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener{

    //震动传感器
    private Vibrator vibrator;
    //声明控件
    private Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Screen.setFullScreen(this);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        btnSwitch = (Button)findViewById(R.id.btn_switch);
        btnSwitch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //触发按钮震动反馈
        long [] pattern = {0,25};   // 停止 开启
        vibrator.vibrate(pattern,-1);
        switch (view.getId()){
            case R.id.btn_switch:   new SwitchBlinkTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, btnSwitch);
                System.out.print("abc");
                break;
            default:
                break;
        }
    }

}
