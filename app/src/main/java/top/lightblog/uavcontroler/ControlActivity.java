package top.lightblog.uavcontroler;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import top.lightblog.helper.*;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener{


    public static boolean SwitchStatus = false;     //开关状态

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
            case R.id.btn_switch:   processBtnSwitch();
                break;
            default:
                break;
        }
    }

    //处理异步消息更新UI
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Status.BLINK_ON:   btnSwitch.setBackgroundResource(R.drawable.switch_on);
                    break;
                case Status.BLINK_OFF:   btnSwitch.setBackgroundResource(R.drawable.switch_off);
                    break;
                default:
                    break;
            }
        }
    };

    //按钮点击事件处理方法
    private void processBtnSwitch(){
        new Thread(new Runnable() {

            Message message = null;
            int lastMessage = Status.BLINK_OFF;

            @Override
            public void run() {
                //如果打开了则关闭
                if(Status.SwitchStatus) {
                    message = new Message();
                    message.what = Status.BLINK_OFF;
                    handler.sendMessage(message);
                    Status.SwitchStatus = false;
                }else { //否则启动
                    Status.SwitchStatus = true;
                    //启动时快速闪烁
                    for (int i = 0; i < 6; ++i) {
                        if(Status.SwitchStatus ) {
                            startBlink(100);
                        }else { //中途被关闭
                            stopBlink();
                            break;
                        }
                    }
                    //开关呼吸灯效果
                    while (Status.SwitchStatus){
                        if(Status.SwitchStatus ) {
                            startBlink(800);
                        }else { //中途被关闭
                            stopBlink();
                            break;
                        }
                    }
                }
            }

            private void startBlink(int millTime){
                message = new Message();
                message.what = lastMessage == Status.BLINK_OFF ? Status.BLINK_ON : Status.BLINK_OFF;
                lastMessage = message.what;
                handler.sendMessage(message);
                try {
                    Thread.sleep(millTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private void stopBlink(){
                message = new Message();
                message.what = Status.BLINK_OFF;
                handler.sendMessage(message);
                Status.SwitchStatus = false;
            }
        }).start();
    }
}