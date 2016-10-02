package top.lightblog.uavcontroler;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import top.lightblog.asynctask.SwitchBlinkTask;
import top.lightblog.helper.*;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener{

    //声明控件
    private Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Screen.setFullScreen(this);

        btnSwitch = (Button)findViewById(R.id.btn_switch);
        btnSwitch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_switch:   new SwitchBlinkTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, btnSwitch);
                System.out.print("abc");
                break;
            default:
                break;
        }
    }

}
